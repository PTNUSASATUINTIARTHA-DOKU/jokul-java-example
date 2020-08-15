package com.doku.javaexample.dto.va;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto {
    private Integer id;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String city;
    private String postalCode;
    private String virtualAccountNumber;
    private String invoiceNumber;
    private String status;
    private String howToPayPage;
    private String howToPayApi;
    private String expiredDate;
}
