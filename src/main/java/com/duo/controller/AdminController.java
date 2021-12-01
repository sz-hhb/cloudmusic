package com.duo.controller;

import com.duo.entity.Admin;
import com.duo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String adminLogin(@RequestBody Admin admin) {
        Admin adminInfo = adminService.login(admin);
        if (adminInfo != null) {
            return "success";
        }
        return "error";
    }
}
