package com.doku.javaexample.controller;

import com.doku.java.library.dto.va.notify.request.NotifyRequestBody;
import com.doku.java.library.dto.va.notify.request.NotifyRequestHeader;
import com.doku.java.library.dto.va.notify.response.NotifyResponseBody;
import com.doku.javaexample.services.va.NotificationServices;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping("/demo/java-library/notify")
@Slf4j
public class NofitifcationController {
    @Autowired
    NotificationServices notificationServices;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotifyResponseBody> getData(@RequestHeader(value = "Client-Id") String clientId,
                                                     @RequestHeader(value = "Signature") String signature,
                                                     @RequestHeader(value = "Request-Id") String requestId,
                                                     @RequestHeader(value = "Request-Timestamp") String requestTimeStamp,
                                                     @RequestBody String bodyRequest) throws  NoSuchAlgorithmException, InvalidKeyException {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        NotifyRequestHeader notifyRequestHeader = new NotifyRequestHeader();
        notifyRequestHeader.setClientId(clientId);
        notifyRequestHeader.setRequestId(requestId);
        notifyRequestHeader.setSignature(signature);
        notifyRequestHeader.setRequestTimeStamp(requestTimeStamp);

        NotifyRequestBody notifyRequestBody = gson.fromJson(bodyRequest,NotifyRequestBody.class);
        log.info("Nilai Request "+bodyRequest);
        ResponseEntity<NotifyResponseBody> notifyResponseBody = notificationServices.notify(notifyRequestBody,notifyRequestHeader,bodyRequest);


        return notifyResponseBody;
    }
}
