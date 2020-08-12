package com.doku.javaexample.repository.cc;

import com.doku.javaexample.entity.SetupConfigurationCc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SetupConfigurationCcRepository extends JpaRepository<SetupConfigurationCc, Integer> {
    @Query("SELECT sc FROM SetupConfigurationCc sc")
    SetupConfigurationCc findOne();
}
