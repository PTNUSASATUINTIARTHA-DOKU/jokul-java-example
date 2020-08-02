package com.doku.javaexample.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto {
    private Integer id;
    private String virtualAccountNumber;
    private String invoiceNumber;
    private String status;
    private String howToPayPage;
    private String howToPayApi;
    private String expiredDate;
}
