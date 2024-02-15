//package com.darmokhval.springtest.controller;
//
//import com.darmokhval.springtest.dto.CarDTO;
//import com.darmokhval.springtest.service.CarService;
//import com.darmokhval.springtest.util.View;
//import com.fasterxml.jackson.annotation.JsonView;
//import jakarta.validation.Valid;
//import org.bson.types.ObjectId;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController()
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
//    public ResponseEntity<List<CarDTO>> getCars() {
//        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
//    }
//
//    @GetMapping("/cars/{id}")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<CarDTO> getCar(@PathVariable ObjectId id) {
//        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
//    }
//
//    @PostMapping("/cars")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<CarDTO> saveCar(@RequestBody @Valid CarDTO carDTO) {
//        return new ResponseEntity<>(carService.saveCar(carDTO), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/cars/{id}")
//    @JsonView(View.HighAccess.class)
//    public ResponseEntity<CarDTO> deleteCar(@PathVariable ObjectId id) {
//        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
//    }
//
//}
