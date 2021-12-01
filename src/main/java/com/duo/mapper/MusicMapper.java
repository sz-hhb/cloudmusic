package com.duo.mapper;

import com.duo.entity.Music;

import java.util.List;

public interface MusicMapper {
    int deleteByPrimaryKey(Integer musicid);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(Integer musicid);

    List<Music> selectAllMusic();

    List<Music> selectByTypeid(Integer typeid);

    List<Music> selectByHot();

    List<Music> selectBySinger(Integer singerid);

    Music selectByName(Music music);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);
}