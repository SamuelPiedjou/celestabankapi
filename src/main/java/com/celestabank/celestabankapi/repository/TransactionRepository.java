package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select  t from Transaction t where t.account.accountId=:id")
    public List<Transaction> getAllMyAccTransactions(@Param("id") long id);

}
