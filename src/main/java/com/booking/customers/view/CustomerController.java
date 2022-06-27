package com.booking.customers.view;

import com.booking.customers.CustomerService;
import com.booking.customers.repository.Customer;
import com.booking.exceptions.PhoneNumberAlreadyExists;
import com.booking.exceptions.UsernameAlreadyExistException;
import com.booking.handlers.models.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Customer")
@RestController
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(path = "/signup",consumes= {"application/json"})
    @ApiOperation(value = "Create a Customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Registration Success!!"),
            @ApiResponse(code = 401, message = "Unauthorized Customer", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "Duplicate Username", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Something failed in the server", response = ErrorResponse.class)
    })
    public Customer register(@Valid @RequestBody Customer customer) throws UsernameAlreadyExistException, PhoneNumberAlreadyExists {
        return customerService.createCustomerAccount(customer);
    }
}
