package com.booking.exceptions;

public class PhoneNumberAlreadyExists extends Exception{
    public PhoneNumberAlreadyExists() {
        super("An account already exists with same phone number, Please check again!!");
    }
}
