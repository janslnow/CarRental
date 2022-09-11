package com.demo.rental.service;

import com.demo.rental.dao.join.CarAndRentalInfo;
import com.demo.rental.dao.mapper.CarMapper;
import com.demo.rental.model.vo.CarReservedDateVO;
import com.demo.rental.model.vo.CarVO;
import com.demo.rental.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    @Mock
    CarMapper carMapper;

    @InjectMocks
    CarService carService;

    private List<CarAndRentalInfo> builderQueryResult() {

        List<CarAndRentalInfo> queryResult = new ArrayList<>();

        CarAndRentalInfo carAndRentalInfo1 = new CarAndRentalInfo();
        carAndRentalInfo1.setCarId(1);
        carAndRentalInfo1.setCarModel("car model 1");
        carAndRentalInfo1.setRentalStartDate(null);
        carAndRentalInfo1.setRentalEndDate(null);

        Date startDate2 = DateUtil.parseDate("2022-09-11", DateUtil.DEFAULT_DATE_STYLE);
        Date endDate2 = DateUtil.parseDate("2022-09-12", DateUtil.DEFAULT_DATE_STYLE);
        CarAndRentalInfo carAndRentalInfo2 = new CarAndRentalInfo();
        carAndRentalInfo2.setCarId(2);
        carAndRentalInfo2.setCarModel("car model 2");
        carAndRentalInfo2.setRentalStartDate(startDate2);
        carAndRentalInfo2.setRentalEndDate(endDate2);

        Date startDate3 = DateUtil.parseDate("2022-09-13", DateUtil.DEFAULT_DATE_STYLE);
        Date endDate3 = DateUtil.parseDate("2022-09-14", DateUtil.DEFAULT_DATE_STYLE);
        CarAndRentalInfo carAndRentalInfo3 = new CarAndRentalInfo();
        carAndRentalInfo3.setCarId(2);
        carAndRentalInfo3.setCarModel("car model 2");
        carAndRentalInfo3.setRentalStartDate(startDate3);
        carAndRentalInfo3.setRentalEndDate(endDate3);

        queryResult.add(carAndRentalInfo1);
        queryResult.add(carAndRentalInfo2);
        queryResult.add(carAndRentalInfo3);

        return queryResult;

    }

    private List<CarVO> builderExpected() {

        List<CarVO> expected = new ArrayList<>();

        CarVO car1 = new CarVO();
        car1.setCarId(1);
        car1.setCarModel("car model 1");
        car1.setReservedDatePeriod(new ArrayList<>());

        CarVO car2 = new CarVO();
        car2.setCarId(2);
        car2.setCarModel("car model 2");
        List<CarReservedDateVO> expectedCarReserveDateVOList = new ArrayList<>();
        CarReservedDateVO carReservedDateVO1 = new CarReservedDateVO();
        carReservedDateVO1.setRentalStartDate("2022-09-11");
        carReservedDateVO1.setRentalEndDate("2022-09-12");

        CarReservedDateVO carReservedDateVO2 = new CarReservedDateVO();
        carReservedDateVO2.setRentalStartDate("2022-09-13");
        carReservedDateVO2.setRentalEndDate("2022-09-14");

        expectedCarReserveDateVOList.add(carReservedDateVO1);
        expectedCarReserveDateVOList.add(carReservedDateVO2);
        car2.setReservedDatePeriod(expectedCarReserveDateVOList);

        expected.add(car1);
        expected.add(car2);

        return expected;
    }

    @Test
    void getNoneReservePeriod() {
        MockitoAnnotations.openMocks(this);

        List<CarAndRentalInfo> queryResult = builderQueryResult();
        Mockito.doReturn(queryResult).when(carMapper).selectCarAndRentalInfo();

        List<CarVO> expected = builderExpected();
        List<CarVO> actual = carService.getAllCarInfo();

        Assertions.assertEquals(expected, actual);
    }
}