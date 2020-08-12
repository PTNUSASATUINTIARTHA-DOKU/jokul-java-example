package com.doku.javaexample.services.va;

import com.doku.java.library.dto.va.payment.response.PaymentCodeResponseDto;
import com.doku.javaexample.entity.Transaction;
import com.doku.javaexample.repository.va.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction create(PaymentCodeResponseDto paymentCodeResponseDto) {
        Transaction transaction = new Transaction();
        transaction.setInvoiceNumber(paymentCodeResponseDto.getOrder().getInvoiceNumber());
        transaction.setVirtualAccountNumber(paymentCodeResponseDto.getVirtualAccountInfo().getVirtualAccountNumber());
        transaction.setExpiredDate(paymentCodeResponseDto.getVirtualAccountInfo().getExpiredDate());
        transaction.setHowToPayApi(paymentCodeResponseDto.getVirtualAccountInfo().getHowToPayApi());
        transaction.setHowToPayPage(paymentCodeResponseDto.getVirtualAccountInfo().getHowToPayPage());
        transaction.setStatus("pending");
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