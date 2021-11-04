package com.testdriven.SpringtestdrivenDevelopment.service;

import com.testdriven.SpringtestdrivenDevelopment.controller.CarNotFoundException;
import com.testdriven.SpringtestdrivenDevelopment.model.Car;
import com.testdriven.SpringtestdrivenDevelopment.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCarDetails() throws Exception{
        given(carRepository.findByName("pulse")).willReturn(Optional.of(new Car("pulse","hatchback")));

        Car car = carService.getCarDetails("pulse");
        assertNotNull(car);
        assertEquals("pulse",car.getName());
        assertEquals("hatchback",car.getType());
    }

    @Test
    public void getCar_NotFound_test(){
        given(carRepository.findByName("pulse")).willThrow(new CarNotFoundException());

        assertThrows(CarNotFoundException.class,()->carService.getCarDetails("pulse"));
    }
}
