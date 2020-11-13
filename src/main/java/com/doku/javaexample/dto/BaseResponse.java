package com.doku.javaexample.dto;

import enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T>{
    private String responseCode;
    private String responseMessage;
    private String error;
    private T data;

    public BaseResponse<T> generateResponse(ResponseCode responseCode) {
        this.setResponseCode(responseCode.code());
        this.setResponseMessage(responseCode.message());
        return this;
    }

    public BaseResponse<T> generateResponse(String code, String message) {
        this.setResponseCode(code);
        this.setResponseMessage(message);
        return this;
    }

    public BaseResponse<T> generateResponse(ResponseCode responseCode, T data) {
        this.setResponseCode(responseCode.code());
        this.setResponseMessage(responseCode.message());
        this.setData(data);
        return this;
    }
}
