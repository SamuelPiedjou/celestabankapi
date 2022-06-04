package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {

    @Query("select n from Beneficiary n where n.account.accountId=:id")
    public Set<Beneficiary> listAllBeneficiaries(@Param("id") long id);
}
