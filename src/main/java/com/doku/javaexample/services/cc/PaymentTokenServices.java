package com.doku.javaexample.services.cc;

import com.doku.java.library.dto.cc.request.ClientRequestDto;
import com.doku.java.library.dto.cc.request.CustomerRequestDto;
import com.doku.java.library.dto.cc.request.LineItemRequestDto;
import com.doku.java.library.dto.cc.request.OrderRequestDto;
import com.doku.java.library.dto.cc.request.PaymentTokenRequestDto;
import com.doku.java.library.pojo.SetupConfiguration;
import com.doku.java.library.service.cc.CCService;
import com.doku.javaexample.dto.cc.PaymentTokenRequestCusDto;
import com.doku.javaexample.entity.SetupConfigurationCc;
import com.doku.javaexample.entity.TransactionCc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentTokenServices {

    @Autowired
    SetupConfigurationCcServices setupConfigurationCcServices;

    @Autowired
    TransactionCcServices transactionCcServices;

    public TransactionCc requestToken(PaymentTokenRequestCusDto paymentTokenRequestCusDto) throws IOException {
        CCService ccService = new CCService();

        SetupConfigurationCc setupConfigurationEntity = setupConfigurationCcServices.findOne();

        SetupConfiguration setupConfigurationLibrary = SetupConfiguration
                .builder()
                .clientId(setupConfigurationEntity.getClientId())
                .key(setupConfigurationEntity.getSharedKey())
                .environment("http://app-sit.doku.com/")
                .setupServerLocation()
                .build();

        List<LineItemRequestDto> lineItemRequestDtoList = new ArrayList<>();
        lineItemRequestDtoList.add(LineItemRequestDto.builder()
                .name(paymentTokenRequestCusDto
                        .getOrder()
                        .getLineItems()
                        .get(0)
                        .getName())
                .price(paymentTokenRequestCusDto.getOrder()
                        .getLineItems()
                        .get(0)
                        .getPrice())
                .quantity(paymentTokenRequestCusDto
                        .getOrder()
                        .getLineItems()
                        .get(0)
                        .getQuantity())
                .build());


        PaymentTokenRequestDto paymentTokenRequestDto = PaymentTokenRequestDto.builder()
                .client(ClientRequestDto.builder()
                        .id(setupConfigurationEntity
                                .getClientId()).build())
                .order(OrderRequestDto.builder()
                        .amount(paymentTokenRequestCusDto
                                .getOrder()
                                .getAmount())
                        .callbackUrl(paymentTokenRequestCusDto
                                .getOrder()
                                .getCallbackUrl())
                        .createdDate(paymentTokenRequestCusDto
                                .getOrder()
                                .getCreatedDate())
                        .currency(paymentTokenRequestCusDto
                                .getOrder()
                                .getCurrency())
                        .invoiceNumber(paymentTokenRequestCusDto
                                .getOrder()
                                .getInvoiceNumber())
                        .lineItems(lineItemRequestDtoList)
                        .sessionId(paymentTokenRequestCusDto
                                .getOrder()
                                .getSessionId())
                        .build())
                .customer(CustomerRequestDto.builder()
                        .id(paymentTokenRequestCusDto
                                .getCustomer()
                                .getId())
                        .address(paymentTokenRequestCusDto
                                .getCustomer()
                                .getAddress())
                        .country(paymentTokenRequestCusDto
                                .getCustomer()
                                .getCountry())
                        .email(paymentTokenRequestCusDto
                                .getCustomer()
                                .getEmail())
                        .phone(paymentTokenRequestCusDto
                                .getCustomer()
                                .getPhone())
                        .name(paymentTokenRequestCusDto
                                .getCustomer()
                                .getName())
                        .build())
                .sharedKey(setupConfigurationLibrary.getKey())
                .build();

        return transactionCcServices.create(ccService.generateToken(setupConfigurationLibrary, paymentTokenRequestDto));
    }


}
