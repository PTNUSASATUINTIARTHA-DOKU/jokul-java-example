package com.doku.javaexample.controller;

import com.doku.javaexample.dto.cc.PaymentTokenRequestCusDto;
import com.doku.javaexample.entity.TransactionCc;
import com.doku.javaexample.services.cc.PaymentTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/demo/java-library/payment-token")
public class PaymentTokenCcController {
    @Autowired
    PaymentTokenServices paymentTokenServices;

    @GetMapping()
    public String getPaycode() {
        return "payment-token-cc";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionCc> generate(@RequestBody PaymentTokenRequestCusDto paymentTokenRequestDto) throws IOException {
        return ResponseEntity.ok(paymentTokenServices.requestToken(paymentTokenRequestDto));
    }

    @GetMapping("/callback")
    public String callback() {
        return "callback-page";
    }
}
