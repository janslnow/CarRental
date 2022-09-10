package com.demo.rental.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CarVO {

    @ApiModelProperty("car id")
    private Integer carId;

    @ApiModelProperty("car model")
    private String carModel;

    @ApiModelProperty("the reserved period about this car")
    private List<CarReservedDateVO> reservedDatePeriod;

}
