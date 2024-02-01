package com.darmokhval.springtest.mapper;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.entity.Car;

public class CarMapper {

    public static Car convertToEntity(CarDTO carDTO) {
        return new Car(carDTO.getModel(), carDTO.getProducer(), carDTO.getPower());
    }
    public static CarDTO convertToDTO(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getProducer(), car.getPower());
    }
}
