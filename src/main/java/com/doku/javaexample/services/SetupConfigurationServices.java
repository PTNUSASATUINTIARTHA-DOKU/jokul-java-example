package com.doku.javaexample.services;

import com.doku.javaexample.entity.SetupConfiguration;
import com.doku.javaexample.repository.SetupConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupConfigurationServices {

    @Autowired
    SetupConfigurationRepository setupConfigurationRepository;

    public SetupConfiguration create(SetupConfiguration setupConfiguration){
        return setupConfigurationRepository.save(setupConfiguration);
    }

    public SetupConfiguration findOne(){
        return setupConfigurationRepository.findOne();
    }

}
