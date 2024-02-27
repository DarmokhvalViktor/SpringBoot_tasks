package com.darmokhval.springtest.controller;

import com.darmokhval.springtest.dto.TuningDTO;
import com.darmokhval.springtest.service.TuningService;
import lombok.RequiredArgsConstructor;
import org.example.rest.CarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TuningController {
    private final TuningService tuningService;

    @PostMapping("/tune")
    public ResponseEntity<String> tuneManufacturedCarsBy(@RequestBody TuningDTO tuningDTO) {
        tuningService.tuneManufacturedCarsBy(tuningDTO);
        return ResponseEntity.ok("Cars were successfully tuned");
    }

    @GetMapping("/tune")
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(tuningService.getCars());
    }
}
