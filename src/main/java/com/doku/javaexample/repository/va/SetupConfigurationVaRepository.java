package com.doku.javaexample.repository.va;

import com.doku.javaexample.entity.SetupConfigurationVa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SetupConfigurationVaRepository extends JpaRepository<SetupConfigurationVa, Integer> {
    @Query("SELECT sc FROM SetupConfigurationVa sc")
    SetupConfigurationVa findOne();
}
