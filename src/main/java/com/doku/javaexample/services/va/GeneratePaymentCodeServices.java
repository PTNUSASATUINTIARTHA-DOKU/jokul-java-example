package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.payment.request.*;
import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.doku.java.library.service.va.VaServices;
import com.doku.javaexample.dto.va.PaymentCodeInboundDto;
import com.doku.javaexample.entity.SetupConfigurationVa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;


@Service
public class GeneratePaymentCodeServices {

    @Autowired
    SetupConfigurationVaServices setupConfigurationServices;

    @Autowired
    TransactionServices transactionServices;

    public PaymentCodeResponseDto generate(PaymentCodeInboundDto paymentCodeInboundDto) throws IOException {
        VaServices vaServices = new VaServices();

        SetupConfigurationVa setupConfigurationEntity = setupConfigurationServices.findOne();

        SetupConfiguration setupConfigurationLibrary = SetupConfiguration
                .builder()
                .clientId(setupConfigurationEntity.getClientId())
                .merchantName(setupConfigurationEntity.getMerchantName())
                .sharedKey(setupConfigurationEntity.getSharedKey())

                .environment("http://app-sit.doku.com/")
//                .environment(setupConfigurationEntity.getEnvironment())
                .setupServerLocation()
                .build();

        PaymentCodeRequestDto paymentCodeRequestDtoLib = PaymentCodeRequestDto.builder()
                .customer(CustomerRequestDto.builder()
                        .email(paymentCodeInboundDto.getEmail())
                        .name(paymentCodeInboundDto.getCustomerName())
                        .build())
                .client(ClientRequestDto.builder()
                        .id(setupConfigurationEntity.getClientId())
                        .build())
                .order(OrderRequestDto.builder()
                        .invoiceNumber(randomString())
                        .amount(paymentCodeInboundDto.getAmount())
                        .build())
                .virtualAccountInfo(VirtualAccountInfoRequestDto.builder()
                        .expiredTime(paymentCodeInboundDto.getExpiredTime())
                        .reusableStatus(paymentCodeInboundDto.getReusableStatus())
                        .info1(paymentCodeInboundDto.getInfo1())
                        .info2(paymentCodeInboundDto.getInfo2())
                        .info3(paymentCodeInboundDto.getInfo3())
                        .build())
                .sharedKey(setupConfigurationEntity.getSharedKey())
                .generateWords()
                .build();

        PaymentCodeResponseDto paymentCodeResponseDto = generatePaycode(paymentCodeInboundDto, vaServices, setupConfigurationLibrary, paymentCodeRequestDtoLib);
        if (null==paymentCodeResponseDto.getError()){
            transactionServices.create(paymentCodeResponseDto,paymentCodeInboundDto);
        }
        return paymentCodeResponseDto;
    }



    private PaymentCodeResponseDto generatePaycode(PaymentCodeInboundDto paymentCodeInboundDto, VaServices vaServices, SetupConfiguration setupConfiguratioLibrary, PaymentCodeRequestDto paymentCodeRequestDtoLib) throws IOException {
        PaymentCodeResponseDto paymentCodeResponseDto = new PaymentCodeResponseDto();
        if ("mandiri".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateMandiriVa(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        } else if ("mandiri-syariah".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateMandiriSyariahVa(setupConfiguratioLibrary, paymentCodeRequestDtoLib);
        }
        return paymentCodeResponseDto;
    }

    private final String randomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }


}
