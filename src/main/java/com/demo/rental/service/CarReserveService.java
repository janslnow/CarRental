package com.demo.rental.service;

import com.demo.rental.model.ro.ReserveRO;
import com.demo.rental.model.vo.CarVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarReserveService {


    public List<CarVO> getAllReserveRecordByUserId(String userId) {


        return null;
    }

    public void reserveCar(String userId,
                           ReserveRO reserveRO) {


    }

}
