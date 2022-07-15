package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMrepository extends JpaRepository<ATM, Long> {
}
