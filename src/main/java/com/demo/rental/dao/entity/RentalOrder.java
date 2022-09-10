package com.demo.rental.dao.entity;

import java.util.Date;

public class RentalOrder {
    private Integer rentalOrderId;

    private Integer userId;

    private Integer carId;

    private Long rentalStartTime;

    private Long rentalEndTime;

    private Date createTime;

    private Date updateTime;

    public Integer getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(Integer rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Long getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(Long rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public Long getRentalEndTime() {
        return rentalEndTime;
    }

    public void setRentalEndTime(Long rentalEndTime) {
        this.rentalEndTime = rentalEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}