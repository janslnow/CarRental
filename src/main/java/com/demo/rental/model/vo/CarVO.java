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

    @ApiModelProperty("the reserved period about this car. If rental end date of a period less than or equal current data, the period don't show")
    private List<CarReservedDateVO> reservedDatePeriod;

}
