package com.darmokhval.springtest.mapper;

import com.darmokhval.springtest.entity.Car;
import org.darmokhval.springtest.rest.model.CarDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(Car car);
    Car fromDto(CarDto carDto);

    void updateCar(@MappingTarget Car target, CarDto source);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchCar(@MappingTarget Car target, CarDto source);
}
