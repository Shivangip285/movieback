package com.booking.handlers;

import com.booking.exceptions.*;
import com.booking.handlers.models.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final ArrayList<String> emptyDetails = new ArrayList<>();

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public final ResponseEntity<ErrorResponse> handleException(InvalidDefinitionException ex) {
        if (ex.getCause() instanceof EnumValidationException)
            return handleEnumValidationException((EnumValidationException) ex.getCause());

        ErrorResponse errorResponse = new ErrorResponse("Something has gone wrong", singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnumValidationException.class)
    public ResponseEntity<ErrorResponse> handleEnumValidationException(EnumValidationException ex) {
        ErrorResponse error = new ErrorResponse("Validation Failed", singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> handleEmptyResultException(EmptyResultDataAccessException e) {
        ErrorResponse error = new ErrorResponse("Record not found", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidCurrentPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOldPasswordException(InvalidCurrentPasswordException e) {
        ErrorResponse error = new ErrorResponse("Invalid password", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        ErrorResponse error = new ErrorResponse("Incorrect format", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NewPasswordMatchedPreviousPasswordsException.class)
    public ResponseEntity<ErrorResponse> handleNewPasswordMatchedPreviousPasswordsException(NewPasswordMatchedPreviousPasswordsException e) {
        ErrorResponse error = new ErrorResponse("Duplicate new password", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException() {
        ErrorResponse error = new ErrorResponse("Something went wrong", emptyDetails);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistException e) {
        ErrorResponse error = new ErrorResponse("Username Already Exists", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PhoneNumberAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExistsException(PhoneNumberAlreadyExists e) {
        ErrorResponse error = new ErrorResponse("Phone Number Already Exists", new ArrayList<>() {{
            add(e.getMessage());
        }});
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
