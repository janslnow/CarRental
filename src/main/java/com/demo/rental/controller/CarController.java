package com.demo.rental.controller;

import com.demo.rental.model.HttpResult;
import com.demo.rental.model.vo.CarVO;
import com.demo.rental.service.CarService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "car info api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @GetMapping("/v1")
    public HttpResult<List<CarVO>> getAllCarInfo() {
        return HttpResult.success(carService.getAllCarInfo());
    }

}
