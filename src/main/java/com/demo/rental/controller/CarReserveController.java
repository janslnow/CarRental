package com.demo.rental.controller;

import com.demo.rental.common.UserContext;
import com.demo.rental.model.HttpResult;
import com.demo.rental.model.ro.ReserveRO;
import com.demo.rental.model.vo.OrderVO;
import com.demo.rental.service.CarReserveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reserve-orders")
public class CarReserveController {

    private final CarReserveService carReserveService;

    @GetMapping("/")
    public HttpResult<List<OrderVO>> getUserReserveRecord() {
        return HttpResult.success(carReserveService.getAllReserveOrderByUserId(UserContext.getUserId()));
    }

    @PostMapping("/")
    public HttpResult<Integer> reserveCar(@RequestBody ReserveRO reserveRO) {
        return HttpResult.success(carReserveService.reserveCar(UserContext.getUserId(), reserveRO));
    }

}
