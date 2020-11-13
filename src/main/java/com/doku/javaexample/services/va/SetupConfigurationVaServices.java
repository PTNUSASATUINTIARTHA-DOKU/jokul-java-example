package com.doku.javaexample.services.va;

import com.doku.javaexample.entity.SetupConfigurationVa;
import com.doku.javaexample.repository.va.SetupConfigurationVaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupConfigurationVaServices {

    @Autowired
    SetupConfigurationVaRepository setupConfigurationRepository;

    public SetupConfigurationVa create(SetupConfigurationVa setupConfiguration){
        SetupConfigurationVa setupConfigurationVa = findOne();
        if(null!=findOne()){
            setupConfiguration.setId(setupConfigurationVa.getId());
        }
        return setupConfigurationRepository.save(setupConfiguration);
    }

    public SetupConfigurationVa findOne(){
        return setupConfigurationRepository.findOne();
    }

}
