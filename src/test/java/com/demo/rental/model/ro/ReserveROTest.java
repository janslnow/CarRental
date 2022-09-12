package com.demo.rental.model.ro;

import com.demo.rental.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReserveROTest {

    @Test
    void validateCaseNullCarId() {

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(null);
        reserveRO.setRentalStartDate("2022-01-01");
        reserveRO.setRentalEndDate("2022-01-01");

        Assertions.assertThrows(BusinessException.class, reserveRO::validate);

        ReserveRO reserveRO1 = new ReserveRO();
        reserveRO1.setCarId(1);
        reserveRO1.setRentalStartDate(null);
        reserveRO1.setRentalEndDate("2022-01-01");

        Assertions.assertThrows(BusinessException.class, reserveRO1::validate);

        ReserveRO reserveRO2 = new ReserveRO();
        reserveRO2.setCarId(null);
        reserveRO2.setRentalStartDate("");
        reserveRO2.setRentalEndDate("2022-01-01");

        Assertions.assertThrows(BusinessException.class, reserveRO2::validate);

        ReserveRO reserveRO3 = new ReserveRO();
        reserveRO3.setCarId(null);
        reserveRO3.setRentalStartDate("2022-01-01");
        reserveRO3.setRentalEndDate(null);

        Assertions.assertThrows(BusinessException.class, reserveRO3::validate);

        ReserveRO reserveRO4 = new ReserveRO();
        reserveRO4.setCarId(null);
        reserveRO4.setRentalStartDate("2022-01-01");
        reserveRO4.setRentalEndDate("");

        Assertions.assertThrows(BusinessException.class, reserveRO4::validate);

    }

    @Test
    void validateCaseStartDateBeforeCurrentDate() {

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(1);
        reserveRO.setRentalStartDate("2022-09-12");
        reserveRO.setRentalEndDate("2022-09-13");

        Assertions.assertThrows(BusinessException.class, reserveRO::validate);

        ReserveRO reserveRO1 = new ReserveRO();
        reserveRO1.setCarId(1);
        reserveRO1.setRentalStartDate("2022-09-11");
        reserveRO1.setRentalEndDate("2022-09-13");

        Assertions.assertThrows(BusinessException.class, reserveRO1::validate);
    }

    @Test
    void validateCaseStartDateAfterEndDate() {

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(1);
        reserveRO.setRentalStartDate("2022-09-13");
        reserveRO.setRentalEndDate("2022-09-13");

        reserveRO.validate();

        ReserveRO reserveRO1 = new ReserveRO();
        reserveRO1.setCarId(1);
        reserveRO1.setRentalStartDate("2022-09-14");
        reserveRO1.setRentalEndDate("2022-09-13");

        Assertions.assertThrows(BusinessException.class, reserveRO1::validate);
    }


    @Test
    void validateCaseNormal() {

        ReserveRO reserveRO = new ReserveRO();
        reserveRO.setCarId(1);
        reserveRO.setRentalStartDate("2022-09-13");
        reserveRO.setRentalEndDate("2022-09-14");

        reserveRO.validate();
    }
}