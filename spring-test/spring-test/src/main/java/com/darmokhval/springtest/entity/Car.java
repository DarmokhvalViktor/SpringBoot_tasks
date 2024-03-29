package com.darmokhval.springtest.entity;

import com.darmokhval.springtest.dto.CarDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;
    private String producer;
    private int power;

    public Car() {
    }

    public Car(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public static Car convertToEntity(CarDTO carDTO) {
        return new Car(carDTO.getModel(), carDTO.getProducer(), carDTO.getPower());
    }
}
