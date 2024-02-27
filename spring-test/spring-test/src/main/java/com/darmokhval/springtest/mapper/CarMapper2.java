//package com.darmokhval.springtest.mapper;
//
//import com.darmokhval.springtest.dto.CarDTO;
//import com.darmokhval.springtest.entity.Car;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CarMapper {
//
//    public Car convertToEntity(CarDTO carDTO) {
//        return new Car(carDTO.getModel(), carDTO.getProducer(), carDTO.getPower());
//    }
//    public CarDTO convertToDTO(Car car) {
//        return new CarDTO(car.getId(), car.getModel(), car.getProducer(), car.getPower());
//    }
//}
