package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.entity.Car;
import com.darmokhval.springtest.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public List<CarDTO> getCars() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()) {
            return null;
        }
        return cars.stream().map(CarDTO::convertToDTO).toList();
    }

    public CarDTO getCar(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(CarDTO::convertToDTO).orElse(null);
    }

    public CarDTO saveCar(CarDTO carDTO) {
        Car car = Car.convertToEntity(carDTO);
        return CarDTO.convertToDTO(carRepository.save(car));
    }

    public CarDTO deleteCar(Integer id) {
        CarDTO deletedCar = getCar(id);
        carRepository.deleteById(id);
        return deletedCar;
    }

    public List<CarDTO> getCarsByPower(Integer power) {
        List<Car> carsByPower = carRepository.findCarsByPower(power);
        if(carsByPower.isEmpty()) {
            return null;
        }
        return carsByPower.stream().map(CarDTO::convertToDTO).toList();
    }


    public List<CarDTO> getCarsByProducer(String producer) {
        List<Car> carsByProducer = carRepository.findCarsByProducer(producer);
        if(carsByProducer.isEmpty()) {
            return null;
        }
        return carsByProducer.stream().map(CarDTO::convertToDTO).toList();
    }
}
