package com.booking.customers.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.Date;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerTest {

    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void should_not_allow_customer_name_to_be_blank() {
        final Customer customer = new Customer(1L,"", "8019623788","Skyfox123","skyfox@gmail.com","Skyfox@123","city",new Date(21-12-2022));
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations.iterator().next().getMessage(), is("Customer name must be provided"));
    }

    @Test
    public void should_allow_phone_number_only_10_digits() {
        final Customer customer = new Customer(1L,"customer name", "992399","Skyfox@123","skyfox@gmail.com","Skyfox@123","city",new Date(12-20-2022));
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations.iterator().next().getMessage(), is("Phone number must have exactly 10 digits"));
    }

    @Test
    public void should_not_allow_blank_phone_number() {
        final Customer customer = new Customer(1L,"customer name", "","Skyfox@123","skyfox@gmail.com","Skyfox@123","city",new Date(12-20-2022));
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations.iterator().next().getMessage(), is("Phone number must be provided"));
    }
    @Test
    public void should_not_allow_incorrect_format_password() {
        final Customer customer = new Customer(1L,"customer name", "8019623788","skyfx123","skyfox@gmail.com","password","city",new Date(12-20-2022));
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations.iterator().next().getMessage(), is("Password must contain at least 1 uppercase characters.,Password must contain at least 1 digit characters.,Password must contain at least 1 special characters."));
    }
    @Test
    public void should_not_allow_incorrect_format_email() {
        final Customer customer = new Customer(1L,"customer name", "8019623788","skyfx123","skyfox","Skyfox@123","city",new Date(12-20-2022));
        final Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations.iterator().next().getMessage(), is("Invalid email"));
    }
}
