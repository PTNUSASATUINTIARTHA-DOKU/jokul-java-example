package com.doku.javaexample.controller;

import com.doku.javaexample.dto.va.TransactionDto;
import com.doku.javaexample.entity.Transaction;
import com.doku.javaexample.services.va.TransactionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;

    @GetMapping()
    public String getTransacationforView() {
        return "transaction";
    }

    @GetMapping(value = "get-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> getData(@RequestParam("paymentCode") String vaNumber) {
        Transaction transactionEntity = transactionServices.findByVANumber(vaNumber);
        TransactionDto transactionDto = new TransactionDto();
        if (null!=transactionEntity){
            BeanUtils.copyProperties(transactionEntity, transactionDto);
        }
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping(value = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllTransaction() {
        return "transaction-all";
    }

    @GetMapping(value = "get-all-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getAllData() {
        List<Transaction> transactionList = transactionServices.findAll();
        return ResponseEntity.ok(transactionList);
    }
}
