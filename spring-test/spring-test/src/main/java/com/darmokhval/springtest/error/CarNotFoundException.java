package com.darmokhval.springtest.error;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Integer id) {
        super(String.format("Car with id %d was not found", id));
    }
}
