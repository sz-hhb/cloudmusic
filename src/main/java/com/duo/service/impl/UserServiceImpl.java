package com.duo.service.impl;

import com.duo.entity.Message;
import com.duo.entity.User;
import com.duo.mapper.MessageMapper;
import com.duo.mapper.UserMapper;
import com.duo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    public List<User> getAllUserInfo() {
        return userMapper.selectAllUserInfo();
    }

    public String updateById(User user) {
        User userInfo = userMapper.selectByName(user);
        if (!userInfo.getUid().equals(user.getUid())) {
            return "error";
        } else {
            user.setUpdateTime(new Date());
            userMapper.updateByPrimaryKey(user);
            return "success";
        }
    }

    public List<User> selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int insertUser(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }

    public int deleteUser(User user) {
        List<Message> messages = messageMapper.selectByUid(user.getUid());
        for (int i = 0; i < messages.size(); i++) {
            messageMapper.deleteByPrimaryKey(messages.get(i).getMessageid());
        }
        return userMapper.deleteByPrimaryKey(user.getUid());
    }

    public int deletesUser(Integer[] ids) {
        for (Integer id : ids) {
            List<Message> messages = messageMapper.selectByUid(id);
            for (int i = 0; i < messages.size(); i++) {
                messageMapper.deleteByPrimaryKey(messages.get(i).getMessageid());
            }
            userMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }

    public User login(User user) {
        return userMapper.selectByNameAndPwd(user);
    }

    public String register(User user) {
        User userInfo = userMapper.selectByName(user);
        User userInfo1 = userMapper.selectByEmail(user);
        User userInfo2 = userMapper.selectByPhone(user);
        if (userInfo != null) {
            return "error1";
        } else if (userInfo1 != null) {
            return "error2";
        } else if (userInfo2 != null) {
            return "error3";
        } else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(1);
            userMapper.insert(user);
            return "success";
        }
    }

    public User findUserByName(User user) {
        return userMapper.selectByName(user);
    }

    public String updatePassword(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
        return "success";
    }
}
