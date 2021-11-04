package com.testdriven.SpringtestdrivenDevelopment.repository;

import com.testdriven.SpringtestdrivenDevelopment.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {
    public Optional<Car> findByName(String name);
}
