package com.demo.rental.dao.join;

import lombok.Data;

import java.util.Date;

@Data
public class CarAndRentalInfo {

    private Integer carId;

    private String carModel;

    private Date rentalStartDate;

    private Date rentalEndDate;

}
