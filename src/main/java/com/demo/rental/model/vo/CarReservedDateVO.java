package com.demo.rental.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CarReservedDateVO {

    @ApiModelProperty("the day when use start to use car")
    private String rentalStartDate;

    @ApiModelProperty("the day when user need to return car")
    private String rentalEndDate;

}
