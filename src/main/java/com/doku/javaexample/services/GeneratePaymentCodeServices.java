package com.doku.javaexample.services;

import com.doku.javaexample.dto.GeneratePaymentCodeDto;
import com.doku.javaexample.entity.SetupConfiguration;
import com.doku.javaexample.entity.Transaction;
import com.doku.sdk.dto.payment.request.*;
import com.doku.sdk.dto.payment.response.PaymentCodeResponseDto;
import com.doku.sdk.service.GeneratePaycodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class GeneratePaymentCodeServices {

    @Autowired
    SetupConfigurationServices setupConfigurationServices;

    @Autowired
    TransactionServices transactionServices;

    public Transaction generate(GeneratePaymentCodeDto generatePaymentCodeDto) throws IOException {
        GeneratePaycodeServices generatePaycodeServices = new GeneratePaycodeServices();

        SetupConfiguration setupConfigurationEntity = setupConfigurationServices.findOne();

        com.doku.sdk.pojo.SetupConfiguration setupConfigurationLibrary = com.doku.sdk.pojo.SetupConfiguration
                .builder()
                .clientId(setupConfigurationEntity.getClientId())
                .merchantName(setupConfigurationEntity.getMerchantName())
                .sharedKey(setupConfigurationEntity.getSharedKey())
                .serverLocation(new SetupServices().getServerUrl(setupConfigurationEntity.getServerLocation()))
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

        return transactionServices.create(generatePaycode(generatePaymentCodeDto, generatePaycodeServices, setupConfigurationLibrary, paymentCodeRequestDtoLib));
    }

    private PaymentCodeResponseDto generatePaycode(GeneratePaymentCodeDto generatePaymentCodeDto, GeneratePaycodeServices generatePaycodeServices, com.doku.sdk.pojo.SetupConfiguration setupConfiguratioLibrary, PaymentCodeRequestDto paymentCodeRequestDtoLib) throws IOException {
        PaymentCodeResponseDto paymentCodeResponseDto = new PaymentCodeResponseDto();
        if ("mandiri".equals(generatePaymentCodeDto.getChannel())) {
            paymentCodeResponseDto = generatePaycodeServices.generateMandiriPaymentCode(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        } else if ("mandiri-syariah".equals(generatePaymentCodeDto.getChannel())) {
            paymentCodeResponseDto = generatePaycodeServices.generateMandiriSyariahPaymentCode(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        } else if ("bri-syariah".equals(generatePaymentCodeDto.getChannel())) {
            paymentCodeResponseDto = generatePaycodeServices.generateMandiriSyariahPaymentCode(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        }

        return paymentCodeResponseDto;
    }


}
