package com.doku.javaexample.controller;

import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.javaexample.dto.va.PaymentCodeInboundDto;
import com.doku.javaexample.services.va.GeneratePaymentCodeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/demo/java-library")
public class GeneratePaymentCodeController {
    final
    GeneratePaymentCodeServices generatePaymentCodeServices;

    public GeneratePaymentCodeController(GeneratePaymentCodeServices generatePaymentCodeServices) {
        this.generatePaymentCodeServices = generatePaymentCodeServices;
    }


    @GetMapping()
    public String getPaycode() {
        return "generate-payment-code";
    }

    @PostMapping()
    public ResponseEntity<PaymentResponseDto> generate(@RequestBody PaymentCodeInboundDto paymentCodeInboundDto) throws IOException {
        PaymentResponseDto paymentCodeResponseDto = generatePaymentCodeServices.generate(paymentCodeInboundDto);
        return ResponseEntity.ok(paymentCodeResponseDto);
    }
}
