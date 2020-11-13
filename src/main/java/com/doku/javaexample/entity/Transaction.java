package com.doku.javaexample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String province;
    private String postalCode;
    private String virtualAccountNumber;
    private String invoiceNumber;
    private String status;
    private String howToPayPage;
    private String howToPayApi;
    private String expiredDate;
}
