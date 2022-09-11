package com.demo.rental.service;

import com.demo.rental.dao.entity.Car;
import com.demo.rental.dao.entity.RentalOrder;
import com.demo.rental.dao.mapper.CarMapper;
import com.demo.rental.dao.mapper.RentalOrderMapper;
import com.demo.rental.exception.BusinessException;
import com.demo.rental.model.ro.ReserveRO;
import com.demo.rental.model.vo.OrderVO;
import com.demo.rental.util.DateUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarReserveServiceTest {

    @Mock
    RentalOrderMapper rentalOrderMapper;

    @Mock
    CarMapper carMapper;

    @InjectMocks
    CarReserveService carReserveService;


    private List<RentalOrder> builderMockedQueryResult() {

        List<RentalOrder> rentalOrderList = new ArrayList<>();

        Date rentalStartDate1 = DateUtil.parseDate("2022-01-01", DateUtil.DEFAULT_DATE_STYLE);
        Date rentalEndDate1 = DateUtil.parseDate("2022-01-02", DateUtil.DEFAULT_DATE_STYLE);
        Date createTime1 = DateUtil.parseDate("2022-01-01 12:00:00", DateUtil.DEFAULT_TIME_STYLE);
        Date updateTime1 = DateUtil.parseDate("2022-01-01 12:00:00", DateUtil.DEFAULT_TIME_STYLE);
        RentalOrder rentalOrder1 = new RentalOrder();
        rentalOrder1.setRentalOrderId(1);
        rentalOrder1.setUserId(1);
        rentalOrder1.setCarId(1);
        rentalOrder1.setCarModel("test car model 1");
        rentalOrder1.setRentalStartDate(rentalStartDate1);
        rentalOrder1.setRentalEndDate(rentalEndDate1);
        rentalOrder1.setCreateTime(createTime1);
        rentalOrder1.setUpdateTime(updateTime1);

        Date rentalStartDate2 = DateUtil.parseDate("2022-01-01", DateUtil.DEFAULT_DATE_STYLE);
        Date rentalEndDate2 = DateUtil.parseDate("2022-01-02", DateUtil.DEFAULT_DATE_STYLE);
        Date createTime2 = DateUtil.parseDate("2022-01-01 12:00:00", DateUtil.DEFAULT_TIME_STYLE);
        Date updateTime2 = DateUtil.parseDate("2022-01-01 12:00:00", DateUtil.DEFAULT_TIME_STYLE);
        RentalOrder rentalOrder2 = new RentalOrder();
        rentalOrder2.setRentalOrderId(2);
        rentalOrder2.setUserId(1);
        rentalOrder2.setCarId(2);
        rentalOrder2.setCarModel("test car model 2");
        rentalOrder2.setRentalStartDate(rentalStartDate2);
        rentalOrder2.setRentalEndDate(rentalEndDate2);
        rentalOrder2.setCreateTime(createTime2);
        rentalOrder2.setUpdateTime(updateTime2);

        rentalOrderList.add(rentalOrder1);
        rentalOrderList.add(rentalOrder2);

        return rentalOrderList;
    }

    public List<OrderVO> builderExpectedOfGetOrder() {

        List<OrderVO> expected = new ArrayList<>();

        OrderVO orderVO1 = new OrderVO();
        orderVO1.setOrderId(1);
        orderVO1.setCarId(1);
        orderVO1.setCarModel("test car model 1");
        orderVO1.setRentalStartDate("2022-01-01");
        orderVO1.setRentalEndDate("2022-01-02");
        orderVO1.setReservedTime("2022-01-01 12:00:00");

        OrderVO orderVO2 = new OrderVO();
        orderVO2.setOrderId(2);
        orderVO2.setCarId(2);
        orderVO2.setCarModel("test car model 2");
        orderVO2.setRentalStartDate("2022-01-01");
        orderVO2.setRentalEndDate("2022-01-02");
        orderVO2.setReservedTime("2022-01-01 12:00:00");

        expected.add(orderVO1);
        expected.add(orderVO2);

        return expected;
    }

    @Test
    void getAllReserveOrderByUserId() {
        MockitoAnnotations.openMocks(this);

        List<RentalOrder> queryResult = builderMockedQueryResult();
        Mockito.doReturn(queryResult).when(rentalOrderMapper).selectByUserId(1);

        List<OrderVO> actual = carReserveService.getAllReserveOrderByUserId(1);
        List<OrderVO> expected = builderExpectedOfGetOrder();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void reserveCarCaseCarNotExist() {
        MockitoAnnotations.openMocks(this);

        Car car = new Car();
        car.setCarId(1);
        car.setCarModel("test car model 1");

        Mockito.doReturn(car).when(carMapper).selectByPrimaryKey(1);
        Mockito.doReturn(null).when(carMapper).selectByPrimaryKey(Mockito.argThat(i -> i != 1));

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(2);
        Assertions.assertThrows(BusinessException.class, () -> carReserveService.reserveCar(2, reserveRO));

    }

    @Test
    void reserveCarCasePeriodConflict() {
        MockitoAnnotations.openMocks(this);

        Car car = new Car();
        car.setCarId(1);
        car.setCarModel("test car model 1");

        Mockito.doReturn(car).when(carMapper).selectByPrimaryKey(1);
        Mockito.doReturn(null).when(carMapper).selectByPrimaryKey(Mockito.argThat(i -> i != 1));

        Mockito.doReturn(1).when(rentalOrderMapper).selectRepeatDateForUpdate("2022-01-01", "2022-01-02");

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(1);
        reserveRO.setRentalStartDate("2022-01-01");
        reserveRO.setRentalEndDate("2022-01-02");
        Assertions.assertThrows(BusinessException.class, () -> carReserveService.reserveCar(2, reserveRO));
    }

    @Test
    void reserveCarCaseNormal() {
        MockitoAnnotations.openMocks(this);

        Car car = new Car();
        car.setCarId(1);
        car.setCarModel("test car model 1");

        Mockito.doReturn(car).when(carMapper).selectByPrimaryKey(1);
        Mockito.doReturn(null).when(carMapper).selectByPrimaryKey(Mockito.argThat(i -> i != 1));

        Mockito.doReturn(0).when(rentalOrderMapper).selectRepeatDateForUpdate("2022-01-01", "2022-01-02");

        RentalOrder rentalOrder = new RentalOrder();
        rentalOrder.setCarId(1);
        rentalOrder.setRentalStartDate(DateUtil.parseDate("2022-01-01", DateUtil.DEFAULT_DATE_STYLE));
        rentalOrder.setRentalEndDate(DateUtil.parseDate("2022-01-02", DateUtil.DEFAULT_DATE_STYLE));
        rentalOrder.setUserId(2);
        Mockito.doReturn(1).when(rentalOrderMapper).insertSelective(rentalOrder);
        Mockito.doThrow(new RuntimeException()).when(rentalOrderMapper).insertSelective(Mockito.argThat(order ->
                !order.getCarId().equals(rentalOrder.getCarId())
                        || !order.getUserId().equals(rentalOrder.getUserId())
                        || !order.getRentalStartDate().equals(rentalOrder.getRentalStartDate())
                        || !order.getRentalEndDate().equals(rentalOrder.getRentalEndDate())));

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(1);
        reserveRO.setRentalStartDate("2022-01-01");
        reserveRO.setRentalEndDate("2022-01-02");
        carReserveService.reserveCar(2, reserveRO);
    }
}