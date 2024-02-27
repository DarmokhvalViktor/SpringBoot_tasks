package com.darmokhval.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarTunedEvent {
    private Long id;
    private String producer;
    private String model;
    private Long power;
}
