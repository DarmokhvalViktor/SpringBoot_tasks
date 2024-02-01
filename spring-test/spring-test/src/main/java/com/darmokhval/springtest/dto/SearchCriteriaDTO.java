package com.darmokhval.springtest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteriaDTO {
    @NotBlank(message = "Field model must not be blank")
    private String model;
    @NotBlank(message = "Field producer must not be blank")
    private String producer;
    @NotNull(message = "Field power must not be null")
    @Min(value = 1, message = "Minimum value - 0")
    @Max(value = 1000, message = "Maximum value - 1000")
    private Integer power;
}
