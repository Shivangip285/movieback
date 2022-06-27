package com.booking.customers.repository;

import com.booking.customers.CustomerService;
import com.booking.users.PasswordLogger;
import com.booking.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByPhoneNumber(String phoneNumber);
}
