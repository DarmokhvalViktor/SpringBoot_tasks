package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.dto.SearchCriteriaDTO;
import com.darmokhval.springtest.entity.Car;
import com.darmokhval.springtest.error.CarNotFoundException;
import com.darmokhval.springtest.error.CarsNotFoundException;
import com.darmokhval.springtest.mapper.CarMapper;
import com.darmokhval.springtest.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    public List<CarDTO> getCars() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return cars.stream().map(carMapper::convertToDTO).toList();
    }

    public CarDTO getCar(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(carMapper::convertToDTO).orElseThrow(() ->new CarNotFoundException(id));
    }

    @Transactional
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carMapper.convertToEntity(carDTO);
        return carMapper.convertToDTO(carRepository.save(car));
    }

    @Transactional
    public CarDTO deleteCar(Integer id) {
        CarDTO deletedCar = getCar(id);
        carRepository.deleteById(id);
        return deletedCar;
    }

    public List<CarDTO> getCarsByPower(Integer power) {
        List<Car> carsByPower = carRepository.findCarsByPower(power);
        if(carsByPower.isEmpty()) {
            throw new CarsNotFoundException(power);
        }
        return carsByPower.stream().map(carMapper::convertToDTO).toList();
    }


    public List<CarDTO> getCarsByProducer(String producer) {
        List<Car> carsByProducer = carRepository.findCarsByProducer(producer);
        if(carsByProducer.isEmpty()) {
            throw new CarsNotFoundException(producer);
        }
        return carsByProducer.stream().map(carMapper::convertToDTO).toList();
    }

    public List<CarDTO> findAllByParams(SearchCriteriaDTO searchCriteriaDTO) {
        Car car = new Car();
        car.setModel(searchCriteriaDTO.getModel());
        car.setPower(searchCriteriaDTO.getPower());
        car.setProducer(searchCriteriaDTO.getProducer());
        List<Car> carsList = carRepository.findAll(Example.of(car));
        if(carsList.isEmpty()) {
            throw new CarsNotFoundException(searchCriteriaDTO);
        }
        return carsList.stream().map(carMapper::convertToDTO).toList();
    }
}
