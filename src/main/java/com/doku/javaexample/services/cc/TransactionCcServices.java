package com.doku.javaexample.services.cc;

import com.doku.java.library.dto.cc.response.PaymentTokenResponseDto;
import com.doku.javaexample.entity.TransactionCc;
import com.doku.javaexample.repository.cc.TransactionCcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCcServices {

    @Autowired
    TransactionCcRepository transactionCcRepository;

    public TransactionCc create(PaymentTokenResponseDto paymentTokenResponseDto){
        TransactionCc transactionCc = new TransactionCc();
        transactionCc.setInvoiceNumber(paymentTokenResponseDto.getOrder().getInvoiceNumber());
        transactionCc.setAmount(paymentTokenResponseDto.getOrder().getAmount());
        transactionCc.setStatus("pending");
        transactionCc.setUrlPaymentPage(paymentTokenResponseDto.getCreditCardPaymentPage().getUrl());
        transactionCc.setInvoiceNumber(paymentTokenResponseDto.getOrder().getInvoiceNumber());
        return transactionCcRepository.save(transactionCc);
    }
}
