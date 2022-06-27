package com.booking.exceptions;

public class UsernameAlreadyExistException extends Exception{
    public UsernameAlreadyExistException() {
        super("An account already exists with the same username, Please check again!!");
    }
}
