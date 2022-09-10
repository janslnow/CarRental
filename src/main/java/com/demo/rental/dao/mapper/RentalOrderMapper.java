package com.demo.rental.dao.mapper;

import com.demo.rental.dao.entity.RentalOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalOrderMapper {
    int deleteByPrimaryKey(Integer rentalOrderId);

    int insert(RentalOrder record);

    int insertSelective(RentalOrder record);

    RentalOrder selectByPrimaryKey(Integer rentalOrderId);

    int updateByPrimaryKeySelective(RentalOrder record);

    int updateByPrimaryKey(RentalOrder record);
}