package com.doku.javaexample.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneratePaymentCodeDto {
    private String invoiceNumber;
    private Long amount;
    private Integer expiredTime;
    private Boolean reusableStatus;
    private String info1;
    private String info2;
    private String info3;
    private String customerName;
    private String email;
    private String channel;
}
