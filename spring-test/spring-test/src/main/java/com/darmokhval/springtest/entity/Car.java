package com.darmokhval.springtest.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Setter
public class Car {
    @MongoId
    private ObjectId id;

    @NotBlank(message = "Field model must not be blank")
    private String model;
    @NotBlank(message = "Field producer must not be blank")
    private String producer;
    @NotNull(message = "Field power must not be null")
    @Min(value = 1, message = "Minimum value - 1")
    @Max(value = 1000, message = "Maximum value - 1000")
    private Integer power;

    public Car() {
    }

    public Car(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public Car(String model, String producer, Integer power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }
}
