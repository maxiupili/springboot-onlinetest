package com.ghj.springboot.controller;

import com.ghj.springboot.model.User;
import com.ghj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {


    //自动注入userService
    @Autowired
    UserService userService;


    //登录功能
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("usertype") String usertype,
                        Map<String,Object> map, HttpSession session){

        User loginUser = userService.login(username,password,usertype);



        if (loginUser != null){
            session.setAttribute("loginUsername",loginUser.getName());
            session.setAttribute("loginUsersUsername",loginUser.getUsername());
            session.setAttribute("loginUsertype",loginUser.getUsertype());
            session.setAttribute("loginUserId",loginUser.getId());


            if (usertype.equals("root")) {
                //跳转到管理员主界面
                return "redirect:/main.html";
            } else if (usertype.equals("student")){
                //跳转到学生用户界面
                return "redirect:/studentMain.html";
            } else {
                //跳转到教师用户的界面
                return "redirect:/teacherMain.html";
            }

        } else {
            map.put("msg","用户名或密码或权限错误！");
            return "login";
        }
    }

    //登出
    @GetMapping("/logOut")
    public String LogOut(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUsername");
        request.getSession().removeAttribute("loginUsersUsername");
        request.getSession().removeAttribute("loginUsertype");
        request.getSession().removeAttribute("loginUserId");
        return "login";
    }
}
