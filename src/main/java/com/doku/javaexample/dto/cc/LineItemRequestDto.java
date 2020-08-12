
package com.doku.javaexample.dto.cc;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LineItemRequestDto {
    private String name;
    private Integer quantity;
    private Long price;
}
