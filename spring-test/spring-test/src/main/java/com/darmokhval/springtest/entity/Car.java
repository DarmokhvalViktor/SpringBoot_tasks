package com.darmokhval.springtest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Field model must not be blank")
    private String model;
    @NotBlank(message = "Field producer must not be blank")
    private String producer;
    @NotNull(message = "Field power must not be null")
    @Min(value = 1, message = "Minimum value - 1")
    @Max(value = 1000, message = "Maximum value - 1000")
    private Integer power;
    private String photoPath;

    public Car() {
    }

    public Car(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }
    public Car(String model, String producer, Integer power, String photoPath) {
        this.model = model;
        this.producer = producer;
        this.power = power;
        this.photoPath = photoPath;
    }
}
