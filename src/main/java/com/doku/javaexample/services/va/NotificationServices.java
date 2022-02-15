package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.notify.request.NotifyRequestBody;
import com.doku.java.library.dto.va.notify.request.NotifyRequestHeader;
import com.doku.java.library.dto.va.notify.response.*;
import com.doku.java.library.service.va.GenerateSignature;
import com.doku.java.library.service.va.SignatureComponentDTO;
import com.doku.javaexample.repository.va.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class NotificationServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServices transactionServices;

    @Autowired
    SetupConfigurationVaServices setupConfigurationServices;

    @Value("${target-path}")
    String targetPath;
    @Value("${sharedKey}")
    String sharedKey;

    public ResponseEntity<NotifyResponseBody> notify(NotifyRequestBody notifyRequestBody, NotifyRequestHeader notifyRequestHeader, String rawBody) throws NoSuchAlgorithmException, InvalidKeyException{

        log.info("notify Request : "+notifyRequestBody);
        NotifyResponseBody notifyResponseBody = NotifyResponseBody.builder().order(
                OrderResponseDto.builder().
                        amount(notifyRequestBody.getOrder().getAmount()).
                        invoiceNumber(notifyRequestBody.getOrder().getInvoiceNumber())
                        .build())
                .virtualAccountInfo(
                        VirtualAccountInfoResponseDto.builder().
                                virtualAccountNumber(notifyRequestBody.getVirtualAccountInfo().getVirtualAccountNumber())
                                .build()).build();

        String signatureGenerated = generateSignature(notifyRequestHeader,rawBody);
        if (!notifyRequestHeader.getSignature().equals(signatureGenerated)) {
            //logic merchant

            log.info("==== Signature Not match ====");
            log.info("signature from DOku "+notifyRequestHeader.getSignature());
            log.info("Signature generated "+signatureGenerated);

            return new ResponseEntity<>(notifyResponseBody, HttpStatus.BAD_REQUEST);

            }

        return new ResponseEntity<>(notifyResponseBody, HttpStatus.OK);
    }

    private String generateSignature(NotifyRequestHeader notifyRequestHeader,String rawBody) throws NoSuchAlgorithmException, InvalidKeyException {

        SignatureComponentDTO signatureComponentDTO = SignatureComponentDTO.builder()
                .clientId(notifyRequestHeader.getClientId())
                .requestId(notifyRequestHeader.getRequestId())
                .timestamp(notifyRequestHeader.getRequestTimeStamp())
                .requestTarget(targetPath)
                .secretKey(sharedKey)
                .messageBody(rawBody)
                .build();

        GenerateSignature generateSignature = new GenerateSignature();
        return  generateSignature.createSignatureRequest(signatureComponentDTO);

    }


}
