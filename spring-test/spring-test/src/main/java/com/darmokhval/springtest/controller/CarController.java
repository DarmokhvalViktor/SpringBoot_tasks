package com.darmokhval.springtest.controller;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public ResponseEntity<List<CarDTO>> getCars() {
        List<CarDTO> carsDTO = carService.getCars();
        return carsDTO != null ? new ResponseEntity<>(carsDTO, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer id) {
        CarDTO car = carService.getCar(id);
        return car != null ? new ResponseEntity<>(car, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<CarDTO> saveCar(@RequestBody CarDTO carDTO) {
        CarDTO savedCar = carService.saveCar(carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Integer id) {
        CarDTO deletedCar = carService.deleteCar(id);
        return deletedCar != null ? new ResponseEntity<>(deletedCar, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/power/{power}")
    public ResponseEntity<List<CarDTO>> getCarsByPower(@PathVariable Integer power) {
        List<CarDTO> carsByPower = carService.getCarsByPower(power);
        return carsByPower != null ? new ResponseEntity<>(carsByPower, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/producer/{producer}")
    public ResponseEntity<List<CarDTO>> getCarsByProducer(@PathVariable String producer) {
        List<CarDTO> carsByProducer = carService.getCarsByProducer(producer);
        return carsByProducer != null ? new ResponseEntity<>(carsByProducer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
