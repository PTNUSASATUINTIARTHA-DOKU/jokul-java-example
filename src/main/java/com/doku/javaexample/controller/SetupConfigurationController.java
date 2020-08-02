package com.doku.javaexample.controller;

import com.doku.javaexample.dto.SetupConfigurationDto;
import com.doku.javaexample.entity.SetupConfiguration;
import com.doku.javaexample.services.SetupConfigurationServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setup")
public class SetupConfigurationController {
    @Autowired
    SetupConfigurationServices setupConfigurationServices;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SetupConfigurationDto> setConfiguration(@RequestBody SetupConfigurationDto setupConfigurationDto) {

        SetupConfiguration setupConfigurationEntity = new com.doku.javaexample.entity.SetupConfiguration();
        BeanUtils.copyProperties(setupConfigurationDto, setupConfigurationEntity);
        setupConfigurationEntity = setupConfigurationServices.create(setupConfigurationEntity);
        BeanUtils.copyProperties(setupConfigurationEntity, setupConfigurationDto);

        return ResponseEntity.ok(setupConfigurationDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SetupConfigurationDto> getDataConfiguration() {
        SetupConfigurationDto setupConfigurationDto = new SetupConfigurationDto();
        SetupConfiguration setupConfigurationEntity = setupConfigurationServices.findOne();
        if (null!=setupConfigurationEntity){
            BeanUtils.copyProperties(setupConfigurationEntity, setupConfigurationDto);
        }
        return ResponseEntity.ok(setupConfigurationDto);
    }
}
