package com.demo.rental.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CarVO {

    private Integer carId;

    private String carName;

    private List<CarReserveTimeVO> reservedTimePeriod;
}
