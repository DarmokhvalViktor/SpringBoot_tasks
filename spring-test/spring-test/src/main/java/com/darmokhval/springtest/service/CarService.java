package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.entity.Car;
import com.darmokhval.springtest.error.CarNotFoundException;
import com.darmokhval.springtest.error.CarsNotFoundException;
import com.darmokhval.springtest.mapper.CarMapper;
import com.darmokhval.springtest.repository.CarMongoRepository;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarMongoRepository carMongoRepository;
    private final CarMapper carMapper;
    private String uploadDirectory;
    private final MailService mailService;
    @Value("${spring.mail.username}")
    private String mailTo;

    public CarService(CarMongoRepository carMongoRepository, CarMapper carMapper, MailService mailService) {
        this.carMongoRepository = carMongoRepository;
        this.carMapper = carMapper;
        this.mailService = mailService;
    }


    public List<CarDTO> getCars() {
        List<Car> cars = carMongoRepository.findAll();
        if(cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return cars.stream().map(carMapper::convertToDTO).toList();
    }

    public CarDTO getCar(ObjectId id) {
        Optional<Car> car = carMongoRepository.findById(id);
        return car.map(carMapper::convertToDTO).orElseThrow(() ->new CarNotFoundException(id));
    }

    @Transactional
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carMapper.convertToEntity(carDTO);
        Car savedCar = carMongoRepository.save(car);
        sendCreatedCarMail(car);
        return carMapper.convertToDTO(savedCar);
    }

    private void sendCreatedCarMail(Car car) {
        mailService.sendEmail(mailTo, "New car created successfully", "Car model %s and producer %s with power %d was created."
                .formatted(car.getModel(), car.getProducer(), car.getPower()));
    }

    @Transactional
    public CarDTO deleteCar(ObjectId id) {
        CarDTO deletedCar = getCar(id);
        carMongoRepository.deleteById(id);
        mailService.sendEmail(mailTo, "Car deleted successfully", "Car with id %s, producer %s, model %s, power %d deleted successfully"
                .formatted(deletedCar.getId(), deletedCar.getProducer(), deletedCar.getModel(), deletedCar.getPower()));
        return deletedCar;
    }
}
