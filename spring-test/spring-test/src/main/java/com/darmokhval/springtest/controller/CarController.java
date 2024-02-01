package com.darmokhval.springtest.controller;

import com.darmokhval.springtest.dto.CarDTO;
import com.darmokhval.springtest.dto.SearchCriteriaDTO;
import com.darmokhval.springtest.service.CarService;
import com.darmokhval.springtest.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    @JsonView(View.LowAccess.class)
    public ResponseEntity<List<CarDTO>> getCars() {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.HighAccess.class)
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer id) {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CarDTO> saveCar(@RequestBody @Valid CarDTO carDTO) {
        return new ResponseEntity<>(carService.saveCar(carDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Integer id) {
        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }
    @GetMapping("/power/{power}")
    @JsonView(View.MediumAccess.class)
    public ResponseEntity<List<CarDTO>> getCarsByPower(@PathVariable Integer power) {
        return new ResponseEntity<>(carService.getCarsByPower(power), HttpStatus.OK);
    }
    @GetMapping("/producer/{producer}")
    @JsonView(View.MediumAccess.class)
    public ResponseEntity<List<CarDTO>> getCarsByProducer(@PathVariable String producer) {
        return new ResponseEntity<>(carService.getCarsByProducer(producer), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CarDTO>> searchCars(@RequestBody @Valid SearchCriteriaDTO searchCriteria) {
        return new ResponseEntity<>(carService.findAllByParams(searchCriteria), HttpStatus.OK);
    }

}
