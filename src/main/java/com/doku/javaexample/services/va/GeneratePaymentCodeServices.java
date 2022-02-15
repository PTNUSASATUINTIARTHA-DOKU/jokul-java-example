package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.payment.request.CustomerRequestDto;
import com.doku.java.library.dto.va.payment.request.OrderRequestDto;
import com.doku.java.library.dto.va.payment.request.PaymentRequestDto;
import com.doku.java.library.dto.va.payment.request.VirtualAccountInfoRequestDto;
import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.doku.java.library.service.va.VaServices;
import com.doku.javaexample.dto.va.Bank;
import com.doku.javaexample.dto.va.PaymentCodeInboundDto;
import com.doku.javaexample.entity.SetupConfigurationVa;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;


@Service
public class GeneratePaymentCodeServices {

    final
    SetupConfigurationVaServices setupConfigurationServices;

    final
    TransactionServices transactionServices;


    @Value("${env.value}")
    String env;

    public GeneratePaymentCodeServices(SetupConfigurationVaServices setupConfigurationServices, TransactionServices transactionServices) {
        this.setupConfigurationServices = setupConfigurationServices;
        this.transactionServices = transactionServices;
    }

    public PaymentResponseDto generate(PaymentCodeInboundDto paymentCodeInboundDto) throws IOException {

        SetupConfigurationVa setupConfigurationEntity = setupConfigurationServices.findOne();

        SetupConfiguration setupConfigurationLibrary = SetupConfiguration
                .builder()
                .clientId(setupConfigurationEntity.getClientId())
                .key(setupConfigurationEntity.getSharedKey())
                .environment(setupConfigurationEntity.getEnvironment())
                .setupServerLocation()
                .build();

        Bank bank = new Bank();
        bank.setAmount(100000);
        bank.setBankId("1");
        bank.setType("bank");

        PaymentRequestDto paymentCodeRequestDtoLib = PaymentRequestDto.builder()
                .customer(CustomerRequestDto.builder()
                        .email(paymentCodeInboundDto.getEmail())
                        .name(paymentCodeInboundDto.getCustomerName())
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
                .setAdditionalInfo("settlement", Arrays.asList(bank))
                .build();

        PaymentResponseDto paymentCodeResponseDto = generatePaycode(paymentCodeInboundDto, setupConfigurationLibrary, paymentCodeRequestDtoLib);

        return paymentCodeResponseDto;
    }


    private PaymentResponseDto generatePaycode(PaymentCodeInboundDto paymentCodeInboundDto, SetupConfiguration setupConfiguratioLibrary, PaymentRequestDto paymentRequestDto) throws IOException {
        VaServices vaServices = new VaServices();
        PaymentResponseDto paymentCodeResponseDto = new PaymentResponseDto();
        if ("doku-va".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateDokuVa(setupConfiguratioLibrary, paymentRequestDto);
        } else if ("bca-va".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateBcaVa(setupConfiguratioLibrary, paymentRequestDto);
        } else if ("mandiri-va".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateMandiriVa(setupConfiguratioLibrary, paymentRequestDto);
        } else if ("bsm-va".equals(paymentCodeInboundDto.getChannel())) {
            paymentCodeResponseDto = vaServices.generateBsmVa(setupConfiguratioLibrary, paymentRequestDto);
        } else if ("permata-va".equals(paymentCodeInboundDto.getChannel())) {
        paymentCodeResponseDto = vaServices.generatePermataVa(setupConfiguratioLibrary, paymentRequestDto);
    }

        return paymentCodeResponseDto;
    }

    private final String randomString() {
        return RandomStringUtils.randomAlphanumeric(7);
    }
}
