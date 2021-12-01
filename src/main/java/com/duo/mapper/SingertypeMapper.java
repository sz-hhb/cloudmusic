package com.duo.mapper;

import com.duo.entity.Singertype;

import java.util.List;

public interface SingertypeMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(Singertype record);

    int insertSelective(Singertype record);

    Singertype selectByPrimaryKey(Integer typeid);

    List<Singertype> selectBySingerTypeName(String singertypename);

    List<Singertype> getSingerTypeList();

    int updateByPrimaryKeySelective(Singertype record);

    int updateByPrimaryKey(Singertype record);
}