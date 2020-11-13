package com.doku.javaexample.dto.va;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentCodeInboundDto {
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String invoiceNumber;
    private String amount;
    private Integer expiredTime;
    private Boolean reusableStatus;
    private String info1;
    private String info2;
    private String info3;
    private String channel;
}
