package com.booking.exceptions;

public class NewPasswordMatchedPreviousPasswordsException extends Exception{
    public NewPasswordMatchedPreviousPasswordsException(){
        super("New password cannot be the same as the previous three passwords");
    }
}
