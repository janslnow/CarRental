package com.demo.rental.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderVO {

    @ApiModelProperty("order id.")
    private Integer orderId;

    @ApiModelProperty("carId of the order")
    private Integer carId;

    @ApiModelProperty("carModel of the order")
    private String carModel;

    @ApiModelProperty("the day when use start to use car")
    private String rentalStartDate;

    @ApiModelProperty("the day when user need to return car")
    private String rentalEndDate;

    @ApiModelProperty("the time when user reserve car")
    private String reservedTime;

}
