
package com.doku.javaexample.dto.cc;

import com.doku.java.library.dto.cc.request.OrderRequestDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentTokenRequestCusDto {
    private CustomerRequestDto customer;
    private OrderRequestDto order;
}
