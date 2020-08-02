package com.doku.javaexample.repository;

import com.doku.javaexample.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT trx FROM Transaction trx where trx.virtualAccountNumber =:virtualAccountNumber")
    Transaction findByVaNumber(@Param("virtualAccountNumber") String virtualAccountNumber);


    @Transactional
    @Modifying
    @Query("UPDATE Transaction trx SET trx.status='success' WHERE trx.virtualAccountNumber =:vaNumber")
    void updateByVaNumber( @Param("vaNumber") String vaNumber);
}
