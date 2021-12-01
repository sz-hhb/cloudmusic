package com.duo.service;

import com.duo.entity.User;

import java.util.List;

public interface UserService {

    //查询所有用户记录
    List<User> getAllUserInfo();

    //更新用户
    String updateById(User user);

    //根据Id查询用户
    List<User> selectUserById(Integer id);

    //添加用户
    int insertUser(User user);

    int deleteUser(User user);

    int deletesUser(Integer[] ids);

    User login(User user);

    String register(User user);

    User findUserByName(User user);

    String updatePassword(User user);
}
