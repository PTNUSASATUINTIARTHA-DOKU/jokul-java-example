package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.java.library.service.va.VaServices;
import com.doku.javaexample.dto.GeneratePaymentCodeDto;
import com.doku.javaexample.entity.SetupConfigurationVa;
import com.doku.javaexample.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class GeneratePaymentCodeServices {

    @Autowired
    SetupConfigurationVaServices setupConfigurationServices;

    @Autowired
    TransactionServices transactionServices;

    public Transaction generate(GeneratePaymentCodeDto generatePaymentCodeDto) throws IOException {
        VaServices vaServices = new VaServices();

        SetupConfigurationVa setupConfigurationEntity = setupConfigurationServices.findOne();

        com.doku.java.library.pojo.SetupConfiguration setupConfigurationLibrary = com.doku.java.library.pojo.SetupConfiguration
                .builder()
                .clientId(setupConfigurationEntity.getClientId())
                .merchantName(setupConfigurationEntity.getMerchantName())
                .sharedKey(setupConfigurationEntity.getSharedKey())
                .environment("http://app-sit.doku.com/")
                .setupServerLocation()
                .build();

        PaymentCodeRequestDto paymentCodeRequestDtoLib = PaymentCodeRequestDto.builder()
                .customer(CustomerRequestDto.builder()
                        .email(generatePaymentCodeDto.getEmail())
                        .name(generatePaymentCodeDto.getCustomerName())
                        .build())
                .client(ClientRequestDto.builder()
                        .id(setupConfigurationEntity.getClientId())
                        .build())
                .order(OrderRequestDto.builder()
                        .invoiceNumber(generatePaymentCodeDto.getInvoiceNumber())
                        .amount(generatePaymentCodeDto.getAmount())
                        .build())
                .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                        .expiredTime(generatePaymentCodeDto.getExpiredTime())
                        .reusableStatus(generatePaymentCodeDto.getReusableStatus())
                        .info1(generatePaymentCodeDto.getInfo1())
                        .info2(generatePaymentCodeDto.getInfo2())
                        .info3(generatePaymentCodeDto.getInfo3())
                        .build())
                .sharedKey(setupConfigurationEntity.getSharedKey())
                .generateWords()
                .build();

        return transactionServices.create(generatePaycode(generatePaymentCodeDto, vaServices, setupConfigurationLibrary, paymentCodeRequestDtoLib));
    }

    private PaymentCodeResponseDto generatePaycode(GeneratePaymentCodeDto generatePaymentCodeDto, VaServices vaServices, com.doku.java.library.pojo.SetupConfiguration setupConfiguratioLibrary, PaymentCodeRequestDto paymentCodeRequestDtoLib) throws IOException {
        PaymentCodeResponseDto paymentCodeResponseDto = new PaymentCodeResponseDto();
        if ("mandiri".equals(generatePaymentCodeDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateMandiriVa(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        } else if ("mandiri-syariah".equals(generatePaymentCodeDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateMandiriSyariahVa(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        }

        return paymentCodeResponseDto;
    }


}
