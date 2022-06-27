package com.booking.customers;

import com.booking.customers.repository.Customer;
import com.booking.customers.repository.CustomerRepository;
import com.booking.exceptions.PhoneNumberAlreadyExists;
import com.booking.exceptions.UsernameAlreadyExistException;
import com.booking.users.User;
import com.booking.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final UserRepository userRepository;


    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Customer createCustomerAccount(Customer customer) throws PhoneNumberAlreadyExists, UsernameAlreadyExistException {
        if(validateCustomerDetails(customer)){
            User user = new User();
            user.setUsername(customer.getUsername());
            user.setPassword(customer.getPassword());

            userRepository.save(user);
            return customerRepository.save(customer);
        }
        return customer;
    }

    public boolean validateCustomerDetails(Customer customer) throws UsernameAlreadyExistException, PhoneNumberAlreadyExists {
        if(usernameExists(customer.getUsername())) {
            throw new UsernameAlreadyExistException();
        }
        if(phoneNumberExists(customer.getPhoneNumber())){
            throw new PhoneNumberAlreadyExists();
        }
        return true;
    }

    private boolean usernameExists(final String username) {
        return customerRepository.findByUsername(username) != null;
    }

    private boolean phoneNumberExists(final String phoneNumber){
        return customerRepository.findByPhoneNumber(phoneNumber) != null;
    }
}
