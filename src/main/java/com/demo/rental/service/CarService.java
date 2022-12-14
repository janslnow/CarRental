package com.demo.rental.service;

import com.demo.rental.dao.join.CarAndRentalInfo;
import com.demo.rental.dao.mapper.CarMapper;
import com.demo.rental.model.vo.CarReservedDateVO;
import com.demo.rental.model.vo.CarVO;
import com.demo.rental.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private final CarMapper carMapper;

    public List<CarVO> getAllCarInfo() {

        List<CarAndRentalInfo> carAndRentalInfoList = carMapper.selectCarAndRentalInfo();

        Map<Integer, List<CarAndRentalInfo>> groupByCarId =
                carAndRentalInfoList
                        .stream()
                        .collect(Collectors.groupingBy(CarAndRentalInfo::getCarId));

        List<CarVO> result = new ArrayList<>();
        for (Map.Entry<Integer, List<CarAndRentalInfo>> entry : groupByCarId.entrySet()) {
            result.add(transferToCarVO(entry));
        }

        result.sort(Comparator.comparing(CarVO::getCarId));

        return result;
    }

    private CarVO transferToCarVO(Map.Entry<Integer, List<CarAndRentalInfo>> entry) {

        Integer carId = entry.getKey();
        List<CarAndRentalInfo> reservedCarDateList = entry.getValue();
        String carModel = reservedCarDateList.get(0).getCarModel();
        List<CarReservedDateVO> carReservedDateVOList = transferToCarReservedDateVO(reservedCarDateList);

        CarVO carVO = new CarVO();
        carVO.setCarId(carId);
        carVO.setCarModel(carModel);
        carVO.setReservedDatePeriod(carReservedDateVOList);

        return carVO;
    }

    private List<CarReservedDateVO> transferToCarReservedDateVO(List<CarAndRentalInfo> reservedCarDateList) {

        return reservedCarDateList
                .stream()
                .filter(carAndRentalInfo -> carAndRentalInfo.getRentalStartDate() != null && carAndRentalInfo.getRentalEndDate() != null)
                .map(carAndRentalInfo -> {

                    CarReservedDateVO carReservedDateVO = new CarReservedDateVO();
                    carReservedDateVO.setRentalStartDate(DateUtil.formatDate(carAndRentalInfo.getRentalStartDate(), DateUtil.DEFAULT_DATE_STYLE));
                    carReservedDateVO.setRentalEndDate(DateUtil.formatDate(carAndRentalInfo.getRentalEndDate(), DateUtil.DEFAULT_DATE_STYLE));

                    return carReservedDateVO;
                })
                .sorted(Comparator.comparing(CarReservedDateVO::getRentalStartDate))
                .collect(Collectors.toList());

    }

}
