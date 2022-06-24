package com.booking.exceptions;

public class InvalidCurrentPasswordException extends Exception{
    public InvalidCurrentPasswordException(){
        super("Please enter correct current password");
    }
}
