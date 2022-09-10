package com.demo.rental.dao.mapper;

import com.demo.rental.dao.entity.Car;
import com.demo.rental.dao.join.CarAndRentalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<CarAndRentalInfo> selectCarAndRentalInfo();
}