package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM CUSTOMERS WHERE PHONE_NUMBER = ?1", nativeQuery = true)
    Customer findCustomerByPhoneNumber(String phone_number);
}
