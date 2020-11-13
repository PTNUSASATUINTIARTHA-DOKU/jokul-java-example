package com.doku.javaexample.services.cc;

import com.doku.javaexample.entity.SetupConfigurationCc;
import com.doku.javaexample.repository.cc.SetupConfigurationCcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupConfigurationCcServices {

    @Autowired
    SetupConfigurationCcRepository setupConfigurationCcRepository;

    public SetupConfigurationCc create(SetupConfigurationCc setupConfiguration) {
        return setupConfigurationCcRepository.save(setupConfiguration);
    }

    public SetupConfigurationCc findOne() {
        return setupConfigurationCcRepository.findOne();
    }
}
