package com.demo.rental.model.vo;

import lombok.Data;

@Data
public class OrderVO {

    private Integer orderId;

    private Integer carId;

    private String carModel;

    private String startDate;

    private String endDate;

    private String reserveTime;

}
