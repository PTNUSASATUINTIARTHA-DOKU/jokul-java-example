
package com.doku.javaexample.dto.cc;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerRequestDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String country;

}
