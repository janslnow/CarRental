package com.demo.rental.model.ro;

import com.demo.rental.exception.BusinessException;
import com.demo.rental.util.DateUtil;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;

@Data
public class ReserveRO {

    private Integer carId;

    private String rentalStartTime;

    private String rentalEndTime;

    public void validate() {

        if (carId == null) {
            throw new BusinessException();
        }

        try {
            Date current = new Date();
            Date rentalStartTimeObject = DateUtil.parseDate(rentalStartTime);
            Date rentalEndTimeObject = DateUtil.parseDate(rentalEndTime);

            if (current.after(rentalStartTimeObject)) {
                // 开始时间必须大于当前时间;
            }

            if (rentalStartTimeObject.after(rentalEndTimeObject)) {
                // 结束时间必须大于开始时间;
            }

        } catch (ParseException e) {
            throw new BusinessException();
        }
    }
}
