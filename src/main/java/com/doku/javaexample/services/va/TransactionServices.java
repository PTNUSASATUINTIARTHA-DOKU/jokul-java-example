package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.payment.response.PaymentResponseDto;
import com.doku.javaexample.dto.va.PaymentCodeInboundDto;
import com.doku.javaexample.entity.Transaction;
import com.doku.javaexample.repository.va.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction create(PaymentResponseDto paymentResponseDto, PaymentCodeInboundDto paymentCodeInboundDto) {
        Transaction transaction = new Transaction();
        transaction.setInvoiceNumber(paymentResponseDto.getOrder().getInvoiceNumber());
        transaction.setVirtualAccountNumber(paymentResponseDto.getVirtualAccountInfo().getVirtualAccountNumber());
        transaction.setExpiredDate(paymentResponseDto.getVirtualAccountInfo().getExpiredDate());
        transaction.setHowToPayApi(paymentResponseDto.getVirtualAccountInfo().getHowToPayApi());
        transaction.setHowToPayPage(paymentResponseDto.getVirtualAccountInfo().getHowToPayPage());
        transaction.setStatus("pending");
        transaction.setAddress(paymentCodeInboundDto.getAddress());
        transaction.setProvince(paymentCodeInboundDto.getProvince());
        transaction.setCountry(paymentCodeInboundDto.getCountry());
        transaction.setPhoneNumber(paymentCodeInboundDto.getPhoneNumber());
        transaction.setCustomerName(paymentCodeInboundDto.getCustomerName());
        transaction.setPostalCode(paymentCodeInboundDto.getPostalCode());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findByVANumber(String vaNumber) {
        return transactionRepository.findByVaNumber(vaNumber);
    }
    public void updateByVANumber(String vaNumber){
        transactionRepository.updateByVaNumber(vaNumber);
    }

}
