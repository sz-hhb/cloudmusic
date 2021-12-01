package com.duo.mapper;

import com.duo.entity.Video;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer videoid);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoid);

    List<Video> getVideoList();

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    Video selectByName(Video video);
}