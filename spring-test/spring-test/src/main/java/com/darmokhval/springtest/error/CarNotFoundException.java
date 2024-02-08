package com.darmokhval.springtest.error;

import org.bson.types.ObjectId;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(ObjectId id) {
        super(String.format("Car with id %s was not found", id));
    }
}
