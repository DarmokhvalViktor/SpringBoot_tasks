package com.darmokhval.springtest.controller;

import com.darmokhval.springtest.service.CarService;
import lombok.RequiredArgsConstructor;
import org.darmokhval.springtest.rest.controller.CarsApi;
import org.darmokhval.springtest.rest.model.CarDto;
import org.darmokhval.springtest.rest.model.DeleteCar200ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarControllerImpl implements CarsApi {
    private final CarService carService;

    @Override
    public ResponseEntity<CarDto> createCar(CarDto carDto) {
        return new ResponseEntity<>(carService.saveCar(carDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CarDto> getCar(Long id) {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CarDto>> getCars(String producer) {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

//    public ResponseEntity<List<CarDto>> getCars() {
//        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<CarDto> modifyCar(Long id, CarDto carDto) {
        return new ResponseEntity<>(carService.updateCar(id, carDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarDto> modifyCarPartially(Long id, CarDto carDto) {
        return new ResponseEntity<>(carService.patchCar(id, carDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteCar200ResponseDto> deleteCar(Long id) {
        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }
}
