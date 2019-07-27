package com.ghj.springboot.controller;

import com.ghj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    //去注册界面
    @GetMapping("/register")
    public String toRegisterPage() {
        return "user/registerUser";
    }

    //post注册用户功能
    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("checkpassword") String checkpassword,
                          @RequestParam("usertype") String usertype,
                          Map<String,Object> map){


        if (!password.equals(checkpassword)) {
            map.put("msg","两次填写密码不一致！");
            return "user/registerUser";
        } else {
            //try尝试在用户表中插入数据，插入失败时catch错误并报错返回添加界面，成功则到用户列表界面
            try {
                userService.addUser(name, username, password, usertype);
            } catch (Exception e) {
                map.put("msg","用户名重复，请选择其他用户名！");
                return "user/registerUser";
            }
            map.put("msg","注册成功，请登录！");
            //重定向到用户列表页面
            return "login";
        }

    }
}
