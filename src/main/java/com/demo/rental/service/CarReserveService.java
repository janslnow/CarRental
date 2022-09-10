package com.demo.rental.service;

import com.demo.rental.dao.entity.RentalOrder;
import com.demo.rental.exception.BusinessException;
import com.demo.rental.model.ro.ReserveRO;
import com.demo.rental.model.vo.OrderVO;
import com.demo.rental.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarReserveService {

    private final RentalOrderMapper rentalOrderMapper;

    public List<OrderVO> getAllReserveOrderByUserId(Integer userId) {

        List<RentalOrder> rentalOrderList = rentalOrderMapper.selectByUserId(userId);

        List<OrderVO> result = new ArrayList<>();
        for (RentalOrder rentalOrder : rentalOrderList) {

            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(rentalOrder.getRentalOrderId());
            orderVO.setCarId(rentalOrder.getCarId());
            orderVO.setCarModel(rentalOrder.getCarModel());
            orderVO.setRentalStartDate(DateUtil.formatDate(rentalOrder.getRentalStartDate(), DateUtil.DEFAULT_DATE_STYLE));
            orderVO.setRentalEndDate(DateUtil.formatDate(rentalOrder.getRentalEndDate(), DateUtil.DEFAULT_DATE_STYLE));
            orderVO.setReservedTime(DateUtil.formatDate(rentalOrder.getCreateTime(), DateUtil.DEFAULT_TIME_STYLE));

            result.add(orderVO);
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer reserveCar(Integer userId,
                           ReserveRO reserveRO) {

        int repeatDateCount = rentalOrderMapper.selectRepeatDateForUpdate(reserveRO.getRentalStartDate(), reserveRO.getRentalEndDate());

        if (repeatDateCount > 0) {
            throw new BusinessException("conflict with reserved period");
        } else {

            RentalOrder rentalOrder = new RentalOrder();
            rentalOrder.setCarId(reserveRO.getCarId());
            rentalOrder.setRentalStartDate(DateUtil.parseDate(reserveRO.getRentalStartDate(), DateUtil.DEFAULT_DATE_STYLE));
            rentalOrder.setRentalEndDate(DateUtil.parseDate(reserveRO.getRentalEndDate(), DateUtil.DEFAULT_DATE_STYLE));
            rentalOrder.setUserId(userId);

            return rentalOrderMapper.insertSelective(rentalOrder);
        }

    }

}
