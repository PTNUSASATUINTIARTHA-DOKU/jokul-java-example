package com.doku.javaexample.controller;

import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.javaexample.dto.va.PaymentCodeInboundDto;
import com.doku.javaexample.services.va.GeneratePaymentCodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class GeneratePaymentCodeController {
    @Autowired
    GeneratePaymentCodeServices generatePaymentCodeServices;


    @GetMapping()
    public String getPaycode() {
        return "generate-payment-code";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentCodeResponseDto> generate(@RequestBody PaymentCodeInboundDto generatePaymentCodeDto) throws IOException {
        PaymentCodeResponseDto paymentCodeResponseDto = generatePaymentCodeServices.generate(generatePaymentCodeDto);
        if (null != paymentCodeResponseDto.getError()) {
            return ResponseEntity.status(paymentCodeResponseDto.getError().getStatusCode()).body(paymentCodeResponseDto);
        } else {
            return ResponseEntity.ok(paymentCodeResponseDto);
        }
    }
}
