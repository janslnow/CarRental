package com.demo.rental.model.ro;

import com.demo.rental.exception.BusinessException;
import com.demo.rental.util.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class ReserveRO {

    private Integer carId;

    private String rentalStartDate;

    private String rentalEndDate;

    public void validate() {

        if (carId == null) {
            throw new BusinessException();
        }

        Date current = new Date();
        Date rentalStartTimeObject = DateUtil.parseDate(rentalStartDate, DateUtil.DEFAULT_DATE_STYLE);
        Date rentalEndTimeObject = DateUtil.parseDate(rentalEndDate, DateUtil.DEFAULT_DATE_STYLE);

        if (current.after(rentalStartTimeObject)) {
            // 开始时间必须大于当前时间;
        }

        if (rentalStartTimeObject.after(rentalEndTimeObject)) {
            // 结束时间必须大于开始时间;
        }

    }
}
