package com.demo.rental.dao.mapper;

import com.demo.rental.dao.entity.Car;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}