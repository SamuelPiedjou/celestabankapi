package com.celestabank.celestabankapi.repository;

import com.celestabank.celestabankapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer findCustomerByEmailId(String email);

}
