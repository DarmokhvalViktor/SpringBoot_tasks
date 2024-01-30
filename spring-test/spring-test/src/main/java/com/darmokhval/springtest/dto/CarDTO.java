package com.darmokhval.springtest.dto;

import com.darmokhval.springtest.entity.Car;
import lombok.Data;

@Data
public class CarDTO {
    private int id;
    private String model;
    private String producer;
    private int power;

    public CarDTO() {
    }

    public CarDTO(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public CarDTO(int id, String model, String producer, int power) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public static CarDTO convertToDTO(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getProducer(), car.getPower());
    }
}
