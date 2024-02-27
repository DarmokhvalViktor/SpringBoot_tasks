//package com.darmokhval.springtest.controller;
//
//import com.darmokhval.springtest.service.CarService;
//import com.darmokhval.springtest.util.View;
//import com.fasterxml.jackson.annotation.JsonView;
//import jakarta.validation.Valid;
//import org.darmokhval.springtest.rest.model.CarDto;
//import org.darmokhval.springtest.rest.model.DeleteCar200ResponseDto;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class CarController {
//
//    private final CarService carService;
//
//
//    public CarController(CarService carService) {
//        this.carService = carService;
//    }
//
//
//    @GetMapping("/cars")
//    @JsonView(View.LowAccess.class)
//    public ResponseEntity<List<CarDto>> getCars() {
//        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
//    }
//
//    @GetMapping("/cars/{id}")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
//        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
//    }
//
//    @PostMapping("/cars")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<CarDto> saveCar(@RequestBody @Valid CarDto carDto) {
//        return new ResponseEntity<>(carService.saveCar(carDto), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/cars/{id}")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<DeleteCar200ResponseDto> deleteCar(@PathVariable Long id) {
//        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
//    }
//
//}
