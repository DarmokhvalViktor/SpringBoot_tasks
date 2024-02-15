//package com.darmokhval.springtest.controller;
//
//import com.darmokhval.springtest.dto.ErrorDTO;
//import com.darmokhval.springtest.error.CarNotFoundException;
//import com.darmokhval.springtest.error.CarsNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class ErrorHandler {
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<ErrorDTO> handleMethodArgNotValid(MethodArgumentNotValidException exception) {
//        String details = exception.getFieldError().getDefaultMessage();
//        return new ResponseEntity<>(ErrorDTO.builder()
//                .timestamp(System.currentTimeMillis())
//                .details(details)
//                .build(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler({CarNotFoundException.class})
//    public ResponseEntity<ErrorDTO> handleCarNotFoundException(CarNotFoundException exception) {
//        String details = exception.getMessage();
//        return new ResponseEntity<>(ErrorDTO.builder()
//                .timestamp(System.currentTimeMillis())
//                .details(details)
//                .build(), HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler({CarsNotFoundException.class})
//    public ResponseEntity<ErrorDTO> handleCarsNotFoundException(CarsNotFoundException exception) {
//        String details = exception.getMessage();
//        return new ResponseEntity<>(ErrorDTO.builder()
//                .timestamp(System.currentTimeMillis())
//                .details(details)
//                .build(), HttpStatus.NOT_FOUND);
//    }
//}
