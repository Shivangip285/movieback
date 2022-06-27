package com.booking.customers;

import com.booking.bookings.BookingService;
import com.booking.bookings.repository.Booking;
import com.booking.bookings.repository.BookingRepository;
import com.booking.customers.repository.Customer;
import com.booking.customers.repository.CustomerRepository;
import com.booking.exceptions.NoSeatAvailableException;
import com.booking.exceptions.PhoneNumberAlreadyExists;
import com.booking.exceptions.UsernameAlreadyExistException;
import com.booking.shows.respository.Show;
import com.booking.shows.respository.ShowRepository;
import com.booking.slots.repository.Slot;
import com.booking.users.User;
import com.booking.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    private Customer customer;

    @BeforeEach
    public void beforeEach() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository,userRepository);
    }

    @Test
    public void should_save_customer() throws UsernameAlreadyExistException, PhoneNumberAlreadyExists {
        customer = new Customer(1L, "customer name", "9922123999", "skyfox123", "skyfox@gmail.com", "password", "city", new Date(12 - 20 - 2022));
        User user = new User("customer name","Skyfox@123");
        Customer mockCustomerRegistration = mock(Customer.class);
        when(customerRepository.save(customer)).thenReturn(mockCustomerRegistration);

        Customer actualCustomerRegistration = customerService.createCustomerAccount(customer);

        verify(customerRepository).save(customer);
        assertThat(actualCustomerRegistration, is(equalTo(mockCustomerRegistration)));
    }

}

