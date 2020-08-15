package com.doku.javaexample.controller;

import com.doku.javaexample.dto.va.SetupConfigurationDto;
import com.doku.javaexample.entity.SetupConfigurationVa;
import com.doku.javaexample.services.va.SetupConfigurationVaServices;
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
@RequestMapping("/setup-va")
public class SetupConfigurationController {
    @Autowired
    SetupConfigurationVaServices setupConfigurationServices;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SetupConfigurationDto> setConfiguration(@RequestBody SetupConfigurationDto setupConfigurationDto) {

        SetupConfigurationVa setupConfigurationEntity = new SetupConfigurationVa();
        BeanUtils.copyProperties(setupConfigurationDto, setupConfigurationEntity);
        setupConfigurationEntity = setupConfigurationServices.create(setupConfigurationEntity);
        BeanUtils.copyProperties(setupConfigurationEntity, setupConfigurationDto);

        return ResponseEntity.ok(setupConfigurationDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SetupConfigurationDto> getDataConfiguration() {
        SetupConfigurationDto setupConfigurationDto = new SetupConfigurationDto();
        SetupConfigurationVa setupConfigurationEntity = setupConfigurationServices.findOne();
        if (null!=setupConfigurationEntity){
            BeanUtils.copyProperties(setupConfigurationEntity, setupConfigurationDto);
        }
        return ResponseEntity.ok(setupConfigurationDto);
    }
}
