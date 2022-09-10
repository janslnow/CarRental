package com.demo.rental.model.ro;

import com.demo.rental.exception.BusinessException;
import com.demo.rental.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReserveRO {

    @ApiModelProperty(value = "the reserved car id", required = true)
    private Integer carId;

    @ApiModelProperty(value = "start day", required = true)
    private String rentalStartDate;

    @ApiModelProperty(value = "end day", required = true)
    private String rentalEndDate;

    public void validate() {

        if (carId == null) {
            throw new BusinessException("param:carId don't set");
        }

        Date current = new Date();
        Date rentalStartTimeObject = DateUtil.parseDate(rentalStartDate, DateUtil.DEFAULT_DATE_STYLE);
        Date rentalEndTimeObject = DateUtil.parseDate(rentalEndDate, DateUtil.DEFAULT_DATE_STYLE);

        if (current.getTime() > rentalStartTimeObject.getTime()) {
            throw new BusinessException("rental start date can't less than current date");
        }

        if (rentalStartTimeObject.getTime() > rentalEndTimeObject.getTime()) {
            throw new BusinessException("rental end date can't less than rental start date");
        }

    }
}
