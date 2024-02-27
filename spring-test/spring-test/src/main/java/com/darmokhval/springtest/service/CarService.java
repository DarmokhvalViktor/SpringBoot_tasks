package com.darmokhval.springtest.service;

import com.darmokhval.springtest.entity.Car;
import com.darmokhval.springtest.error.CarNotFoundException;
import com.darmokhval.springtest.error.CarsNotFoundException;
import com.darmokhval.springtest.mapper.CarMapper;
import com.darmokhval.springtest.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.darmokhval.springtest.rest.model.CarDto;
import org.darmokhval.springtest.rest.model.DeleteCar200ResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private String uploadDirectory;
    private final MailService mailService;
    @Value("${spring.mail.username}")
    private String mailTo;

    public CarService(CarRepository carRepository, CarMapper carMapper, MailService mailService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.mailService = mailService;
    }


    public List<CarDto> getCars() {
        List<Car> cars = carRepository.findAll();
        if(cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return cars.stream().map(carMapper::toDto).toList();
    }

    public List<CarDto> getCars(String producer) {
        List<Car> cars = carRepository.findCarsByProducer(producer);
//        bad practice
//        if(cars.isEmpty()) {
//            throw new CarsNotFoundException();
//        }
        return cars.stream().map(carMapper::toDto).toList();
    }

    public CarDto getCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(carMapper::toDto).orElseThrow(() ->new CarNotFoundException(id));
    }

    @Transactional
    public CarDto saveCar(CarDto carDto) {
        Car car = carMapper.fromDto(carDto);
        Car savedCar = carRepository.save(car);
        sendCreatedCarMail(car);
        return carMapper.toDto(savedCar);
    }

    @Transactional
    public CarDto updateCar(Long targetCarId, CarDto source) {
        Car targetCar = carRepository.findById(targetCarId).orElseThrow();
        carMapper.updateCar(targetCar, source);
        Car modifiedCar = carRepository.save(targetCar);
        return carMapper.toDto(modifiedCar);
    }

    @Transactional
    public CarDto patchCar(Long targetCarId, CarDto source) {
        Car targetCar = carRepository.findById(targetCarId).orElseThrow();
        carMapper.patchCar(targetCar, source);
        Car modifiedCar = carRepository.save(targetCar);
        return carMapper.toDto(modifiedCar);
    }

    private void sendCreatedCarMail(Car car) {
        mailService.sendEmail(mailTo, "New car created successfully", "Car model %s and producer %s with power %d was created."
                .formatted(car.getModel(), car.getProducer(), car.getPower()));
    }

    @Transactional
    public DeleteCar200ResponseDto deleteCar(Long id) {
        CarDto deletedCar = getCar(id);
        carRepository.deleteById(id);
        mailService.sendEmail(mailTo, "Car deleted successfully", "Car with id %s, producer %s, model %s, power %d deleted successfully"
                .formatted(deletedCar.getId(), deletedCar.getProducer(), deletedCar.getModel(), deletedCar.getPower()));
        DeleteCar200ResponseDto car200ResponseDto = new DeleteCar200ResponseDto();
        car200ResponseDto.setDeletedId(id);
        return car200ResponseDto;
    }
}
