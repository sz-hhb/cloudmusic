package com.duo.mapper;

import com.duo.entity.Singer;

import java.util.List;

public interface SingerMapper {
    int deleteByPrimaryKey(Integer singerid);

    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Integer singerid);

    List<Singer> selectBySingerName(String singername);

    List<Singer> getSingerList();

    List<Singer> selectByType(Integer typeid);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKey(Singer record);

    List<Singer> findSingerOrderByHot();

    Singer selectByName(Singer singer);
}