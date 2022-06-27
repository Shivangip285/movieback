package com.booking.customers;

import com.booking.customers.repository.Customer;
import com.booking.exceptions.PhoneNumberAlreadyExists;
import com.booking.exceptions.UsernameAlreadyExistException;

public interface ICustomerService {

    Customer createCustomerAccount(Customer customer) throws PhoneNumberAlreadyExists, UsernameAlreadyExistException;
}
