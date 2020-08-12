package com.doku.javaexample.controller;

import com.doku.java.library.dto.va.notify.request.NotifyRequestDto;
import com.doku.java.library.dto.va.notify.response.NotifyResponseDto;
import com.doku.javaexample.services.va.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/notify")
public class NofitifcationController {
    @Autowired
    NotificationServices notificationServices;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotifyResponseDto> getData(@RequestBody NotifyRequestDto notifyRequestDto) {
        return ResponseEntity.ok(notificationServices.notify(notifyRequestDto));
    }
}
