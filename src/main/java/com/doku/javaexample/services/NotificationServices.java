package com.doku.javaexample.services;

import com.doku.sdk.dto.notify.request.NotifyRequestDto;
import com.doku.sdk.dto.notify.response.*;
import com.doku.javaexample.entity.SetupConfiguration;
import com.doku.javaexample.repository.TransactionRepository;
import com.doku.sdk.builder.EncryptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServices transactionServices;

    @Autowired
    SetupConfigurationServices setupConfigurationServices;

    public NotifyResponseDto notify(NotifyRequestDto notifyRequestDto) {

        NotifyResponseDto notifyResponseDto = null;
        if (notifyRequestDto.getSecurity().getCheckSum().equals(compareWords(notifyRequestDto))) {
            String virtualAccountNumber = notifyRequestDto.getVirtualAccountInfo().getVirtualAccountNumber();
            if (null != transactionServices.findByVANumber(virtualAccountNumber)) {
                transactionServices.updateByVANumber(virtualAccountNumber);
                notifyResponseDto = NotifyResponseDto.builder()
                        .client(ClientResponseDto.builder()
                                .id(notifyRequestDto.getClient().getId()).build())
                        .order(OrderResponseDto.builder()
                                .amount(notifyRequestDto.getOrder().getAmount())
                                .invoiceNumber(notifyRequestDto.getOrder().getInvoiceNumber()).build())
                        .virtualAccountInfo(VirtualAccountInfoResponseDto.builder()
                                .virtualAccountNumber(notifyRequestDto.getVirtualAccountInfo()
                                        .getVirtualAccountNumber()).build())
                        .security(SecurityResponseDto.builder().build())
                        .build();
            }
        }

        return notifyResponseDto;
    }

    private String compareWords(NotifyRequestDto notifyRequestDto) {
        SetupConfiguration setupConfigurationEntity = setupConfigurationServices.findOne();

        String componentWords =
                notifyRequestDto.getClient().getId() +
                        notifyRequestDto.getOrder().getAmount() +
                        notifyRequestDto.getOrder().getInvoiceNumber() +
                        notifyRequestDto.getVirtualAccountInfo().getVirtualAccountNumber() +
                        notifyRequestDto.getVirtualAccountPayment().getChannelCode() +
                        notifyRequestDto.getVirtualAccountPayment().getDate() +
                        notifyRequestDto.getVirtualAccountPayment().getReferenceNumber() +
                        notifyRequestDto.getVirtualAccountPayment().getSystraceNumber() +
                        setupConfigurationEntity.getSharedKey();

        return EncryptBuilder.builder().sha256(componentWords).build().getSha256();
    }


}
