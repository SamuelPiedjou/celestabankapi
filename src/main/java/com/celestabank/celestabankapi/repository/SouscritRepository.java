package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.Souscrit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface SouscritRepository extends JpaRepository<Souscrit, Integer> {
    @Query("select n from Souscrit n where  n.account.accountId=:id")
    public Set<Souscrit> findByAccount(@Param("id") long id);
}
