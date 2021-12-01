package com.duo.mapper;

import com.duo.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByPrimaryKey(Integer uid);

    List<User> selectAllUserInfo();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByNameAndPwd(User user);

    User selectByName(User user);

    User selectByEmail(User user);

    User selectByPhone(User user);
}