package com.demo.rental.controller;

import com.demo.rental.common.UserContext;
import com.demo.rental.model.HttpResult;
import com.demo.rental.model.ro.ReserveRO;
import com.demo.rental.model.vo.OrderVO;
import com.demo.rental.service.CarReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "car reserve api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reserved-orders")
public class CarReserveController {

    private final CarReserveService carReserveService;

    @GetMapping("/")
    @ApiOperation("get all reserved order")
    public HttpResult<List<OrderVO>> getUserReserveOrder() {
        return HttpResult.success(carReserveService.getAllReserveOrderByUserId(UserContext.getUserId()));
    }

    @PostMapping("/")
    @ApiOperation("reserve a car")
    public HttpResult<Integer> reserveCar(@RequestBody ReserveRO reserveRO) {
        reserveRO.validate();

        return HttpResult.success(carReserveService.reserveCar(UserContext.getUserId(), reserveRO));
    }

}
