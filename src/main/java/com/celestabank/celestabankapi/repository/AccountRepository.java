package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.accountType = 0 and a.customer.UserId=:id")
    public List<Account> viewSavingAcc(@Param("id") long id);
    @Query("select a from Account a where a.accountType = 1 and a.customer.UserId=:id")
    public List<Account> viewTermAcc(@Param("id") long id);
}
