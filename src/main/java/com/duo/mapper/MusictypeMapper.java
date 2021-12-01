package com.duo.mapper;

import com.duo.entity.Musictype;

import java.util.List;

public interface MusictypeMapper {
    int deleteByPrimaryKey(Integer musictypeid);

//    int deletesByPrimaryKey(Integer musictypeid);

    int insert(Musictype record);

    int insertSelective(Musictype record);

    Musictype selectByPrimaryKey(Integer musictypeid);

    List<Musictype> selectAllMusicType();

    List<Musictype> selectByMusicTypeName(String musictypename);

    int updateByPrimaryKeySelective(Musictype record);

    int updateByPrimaryKey(Musictype record);
}