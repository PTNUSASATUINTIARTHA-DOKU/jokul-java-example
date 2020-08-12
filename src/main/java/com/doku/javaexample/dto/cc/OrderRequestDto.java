
package com.doku.javaexample.dto.cc;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderRequestDto {
    private Long amount;
    private String invoiceNumber;
    private String currency;
    private String callbackUrl;
    private List<LineItemRequestDto> lineItems;
    private String createdDate;
    private String sessionId;

}
