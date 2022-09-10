package com.demo.rental.controller;

import com.demo.rental.model.HttpResult;
import com.demo.rental.model.vo.CarVO;
import com.demo.rental.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping("/{carId}")
    public HttpResult<CarVO> getCarInfo(@PathVariable Integer carId) {

        if (carId == null) {
            return HttpResult.success(carService.getCarInfo(carId));
        } else {
            throw new RuntimeException();
        }

    }

    @GetMapping("/")
    public HttpResult<List<CarVO>> getAllCarInfo() {
        return HttpResult.success(carService.getAllCarInfo());
    }

}
