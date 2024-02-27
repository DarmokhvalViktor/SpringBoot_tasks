package com.darmokhval.springtest.error;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long id) {
        super(String.format("Car with id %d was not found", id));
    }
}
