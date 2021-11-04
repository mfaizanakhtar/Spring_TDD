package com.testdriven.SpringtestdrivenDevelopment.controller;

import com.testdriven.SpringtestdrivenDevelopment.model.Car;
import com.testdriven.SpringtestdrivenDevelopment.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/cars/{name}")
    public ResponseEntity<Car> getDetails(@PathVariable String name){
        Car car = carService.getCarDetails(name);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }
}
