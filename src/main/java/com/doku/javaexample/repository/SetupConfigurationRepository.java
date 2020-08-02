package com.doku.javaexample.repository;

import com.doku.javaexample.entity.SetupConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SetupConfigurationRepository extends JpaRepository<SetupConfiguration, Integer> {
    @Query("SELECT sc FROM SetupConfiguration sc")
    SetupConfiguration findOne();
}
