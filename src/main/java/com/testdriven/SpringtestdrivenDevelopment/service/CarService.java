package com.testdriven.SpringtestdrivenDevelopment.service;

import com.testdriven.SpringtestdrivenDevelopment.controller.CarNotFoundException;
import com.testdriven.SpringtestdrivenDevelopment.model.Car;
import com.testdriven.SpringtestdrivenDevelopment.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CarService {

    @Autowired
    CarRepository carRepository;

    public Car getCarDetails(String name){
        Car car = null;
        Optional<Car> optionalCar = carRepository.findByName(name);
        if(optionalCar.isPresent()){
            car = optionalCar.get();
        }
        else{
            throw new CarNotFoundException();
        }
        return car;
    }
}
