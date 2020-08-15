package com.doku.javaexample.dto.va;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SetupConfigurationDto {
    private Integer id;
    private String merchantName;
    private String clientId;
    private String sharedKey;
    private String environment;
}
