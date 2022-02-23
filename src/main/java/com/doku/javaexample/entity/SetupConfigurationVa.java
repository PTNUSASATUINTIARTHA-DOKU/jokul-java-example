package com.doku.javaexample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class SetupConfigurationVa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String merchantName;
    private String clientId;
    private String sharedKey;
    private String environment;
}
