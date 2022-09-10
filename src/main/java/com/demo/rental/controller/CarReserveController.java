package com.demo.rental.controller;

import com.demo.rental.model.HttpResult;
import com.demo.rental.model.ro.RentalReserveRO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reserve-record")
public class CarReserveController {

    @GetMapping("/")
    public HttpResult<List<?>> getUserReserveRecord() {

        return null;
    }

    @PostMapping("/")
    public HttpResult<?> reserveCar(@RequestBody RentalReserveRO rentalReserveRO) {

        return null;
    }

    @Delete("/{recordId}")
    public void cancelRental(@PathVariable Integer recordId) {

    }

}
