package com.darmokhval.springtest.repository;

import com.darmokhval.springtest.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarsByPower(Integer power);
    List<Car> findCarsByProducer(String producer);
}
