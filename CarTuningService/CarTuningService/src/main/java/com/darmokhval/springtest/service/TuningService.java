package com.darmokhval.springtest.service;

import com.darmokhval.springtest.dto.CarTunedEvent;
import com.darmokhval.springtest.dto.TuningDTO;
import lombok.RequiredArgsConstructor;
import org.example.rest.CarApi;
import org.example.rest.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TuningService {

    private final CarApi carApi;
    private final CarTunedEventProducer carTunedEventProducer;

    public void tuneManufacturedCarsBy(TuningDTO tuningDTO) {
        carApi.getCars().stream()
                .filter(car -> car.getProducer().equalsIgnoreCase(tuningDTO.getProducer()))
                .forEach(car -> {
                    car.setPower(car.getPower() + 33);
                    carApi.modifyCar(car.getId(), car);
                    carTunedEventProducer.carTunedCreatedEvent(new CarTunedEvent(car.getId(), car.getProducer(), car.getModel(), car.getPower()));
                });
    }

    public List<CarDto> getCars() {
        return carApi.getCars();
    }
}
