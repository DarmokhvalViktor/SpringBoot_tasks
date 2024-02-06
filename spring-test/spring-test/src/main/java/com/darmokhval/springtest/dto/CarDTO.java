package com.darmokhval.springtest.dto;

import com.darmokhval.springtest.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CarDTO {
    @JsonView(View.HighAccess.class)
    private int id;

    @NotBlank(message = "Field model must not be blank")
    @JsonView(View.LowAccess.class)
    private String model;

    @NotBlank(message = "Field producer must not be blank")
    @JsonView(View.LowAccess.class)
    private String producer;

    @NotNull(message = "Field power must not be null")
    @Min(value = 1, message = "Minimum value - 1")
    @Max(value = 1000, message = "Maximum value - 1000")
    @JsonView(View.MediumAccess.class)
    private Integer power;

    private MultipartFile photo;
    @JsonView(View.LowAccess.class)
    private String photoPath;

    public CarDTO(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public CarDTO(int id, String model, String producer, int power) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    public CarDTO(String model, String producer, Integer power, MultipartFile photo) {
        this.model = model;
        this.producer = producer;
        this.power = power;
        this.photo = photo;
    }

    public CarDTO(Integer id, String model, String producer, Integer power, String photoPath) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.power = power;
        this.photoPath = photoPath;
    }
}
