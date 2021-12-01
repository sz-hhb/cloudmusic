package com.duo.controller;

import com.duo.entity.User;
import com.duo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody//返回JSON数据
    public List<User> getAllUserInfo() {
        return userService.getAllUserInfo();
    }

    //通过Id更新用户
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    //通过Id查找用户
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public List<User> selectUserById(@RequestBody User user) {
        return userService.selectUserById(user.getUid());
    }

    //添加用户
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(@RequestBody User user) {
        int result = userService.insertUser(user);
        return "success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestBody User user) {
        int result = userService.deleteUser(user);
        return "success";
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    public String deletesUser(@RequestBody Integer[] ids) {
        int result = userService.deletesUser(ids);
        if (result == 1) {
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(@RequestBody User user) {
        User userInfo = userService.login(user);
        if (userInfo.getStatus() == 1) {
            return "success";
        } else if (userInfo.getStatus() == 0) {
            return "stop";
        }
        return "error";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    @ResponseBody
    public User findUserByName(@RequestBody User user) {
        return userService.findUserByName(user);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }


}
