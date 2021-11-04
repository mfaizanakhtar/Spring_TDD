package com.testdriven.SpringtestdrivenDevelopment.controller;

import com.testdriven.SpringtestdrivenDevelopment.model.Car;
import com.testdriven.SpringtestdrivenDevelopment.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers= CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    @Test
    public void getCarDetailsTest() throws Exception{
        given(carService.getCarDetails(Mockito.anyString())).willReturn(new Car("Scala","Sedan"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/scala"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("name").value("Scala"))
                .andExpect(jsonPath("type").value("Sedan"));
    }

    @Test
    public void Car_NotFound_HttpStatus() throws Exception{
        given(carService.getCarDetails(Mockito.anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/car/scala"))
                .andExpect(status().isNotFound());
    }

}
