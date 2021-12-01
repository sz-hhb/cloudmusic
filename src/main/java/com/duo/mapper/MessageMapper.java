package com.duo.mapper;

import com.duo.entity.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectByUid(Integer uid);

    List<Message> selectByVid(Integer vid);

}