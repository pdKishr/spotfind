package com.mvp.spotfind.Exceptionpack;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object> > handlerAtDto(MethodArgumentNotValidException exp){
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors   = new HashMap<>();

        BindingResult result = exp.getBindingResult();
        for(FieldError err: result.getFieldErrors()){
            errors.put(err.getField() , err.getDefaultMessage());
        }

        response.put("status","error");
        response.put("errors" , errors);
        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handlerAtDatabaseLevel(DataIntegrityViolationException exp){
        if (exp.getMessage().contains("email")) {
            Map<String,String> map = new HashMap<>();
            map.put("error " , "email already exists");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }

        if (exp.getMessage().contains("mobile_number")) {
            Map<String,String> map = new HashMap<>();
            map.put("error " , "mobile number already exists");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }

        Map<String,String> map = new HashMap<>();
        map.put("error " , exp.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFound(UserNotFoundException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error " , ex.getMessage());
        return new ResponseEntity<>(map ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingFullException.class)
    public ResponseEntity<Map<String,String>> handleBookingFull(BookingFullException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error " , ex.getMessage());
        return new ResponseEntity<>(map ,HttpStatus.NOT_FOUND);
    }


}
