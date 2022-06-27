package com.booking.customers.repository;

import com.booking.customers.validations.ValidEmail;
import com.booking.users.ValidPassword;
import com.booking.utilities.serializers.date.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @ApiModelProperty(name = "id", value = "The customer id", example = "0", position = 1)
    private Long id;

    @Column(nullable = false)
    @JsonProperty
    @NotBlank(message = "Customer name must be provided")
    @ApiModelProperty(name = "customer name", value = "Name of customer", required = true, example = "Customer name", position = 2)
    private String name;

    @Column(name = "phone_number", nullable = false,unique = true)
    @JsonProperty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must have exactly 10 digits")
    @NotBlank(message = "Phone number must be provided")
    @ApiModelProperty(name = "phone number", value = "Phone number of the customer", required = true, example = "9933221100", position = 3)
    private String phoneNumber;

    @Column(name = "username", nullable = false,unique = true)
    @JsonProperty
    @NotBlank(message = "Username must be provided")
    @ApiModelProperty(name = "username", value = "username of the customer", required = true, example = "skyfox123", position = 4)
    private String username;

    @Column(name = "email", nullable = false)
    @JsonProperty
    @NotBlank(message = "Email must be provided")
    @ValidEmail
    @ApiModelProperty(name = "email", value = "email of the customer", required = true, example = "skyfox@gmail.com", position = 5)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty
    @NotBlank(message = "Password must be provided")
    @ValidPassword
    @ApiModelProperty(name = "password", value = "Password of the customer", required = true, example = "Skyfox@123", position = 6)
    private String password;

    @Column(name = "city", nullable = false)
    @JsonProperty
    @NotBlank(message = "City must be provided")
    @ApiModelProperty(name = "city", value = "City of the customer", required = true, example = "Chennai", position = 7)
    private String city;

    @Column(name = "dob", nullable = false,columnDefinition = "DATE")
    @JsonProperty
    @JsonSerialize(using = DateSerializer.class)
    @NotNull(message = "Date of birth must be provided")
    @ApiModelProperty(name = "dob", value = "Date of birth of the customer", dataType = "java.lang.String", required = true, example = "12-07-1999", position = 8)
    private Date dateOfBirth;

    public Customer(Long id, String name, String phoneNumber, String username, String email, String password, String city, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }
}
