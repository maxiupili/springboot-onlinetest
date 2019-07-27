package com.ghj.springboot.controller;

import com.ghj.springboot.model.User;
import com.ghj.springboot.service.SimulationTestAnswerService;
import com.ghj.springboot.service.SimulationTestScoreService;
import com.ghj.springboot.service.UserFaultService;
import com.ghj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SimulationTestAnswerService simulationTestAnswerService;

    @Autowired
    UserFaultService userFaultService;

    @Autowired
    SimulationTestScoreService simulationTestScoreService;

//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable("id") Integer id){
//        return userMapper.getUserById(id);
//    }
//
//    @GetMapping("/userpassword/{username}")
//    public User getPassword(@PathVariable("username") String username){
//        return userMapper.getUserByUsername(username);
//    }

    //查询所有用户
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);

        //使用模板引擎进行映射
        return "user/list";
    }

    //get跳转功能
    @GetMapping("/user")
    public String toAddUserPage(){
        //跳转到添加页面
        return "user/addUser";
    }

    //post添加用户功能
    @PostMapping("/user")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("usertype") String usertype,
                          Map<String,Object> map){


        //try尝试在用户表中插入数据，插入失败时catch错误并报错返回添加界面，成功则到用户列表界面
        try {
            userService.addUser(name, username, password, usertype);
        } catch (Exception e) {
            map.put("msg","用户名重复，请选择其他用户名！");
            return "user/addUser";
        }
        //重定向到用户列表页面
        return "redirect:/users";
    }

    //root用户编辑界面跳转
    @GetMapping("/user/{id}")
    public String toEditUserPage(@PathVariable("id") Integer id,Model model){

        model.addAttribute("user",userService.getUserById(id));
        return "user/addUser";
    }

    //学生用户编辑界面跳转
    @GetMapping("/stuUser/{id}")
    public String toStuEditUserPage(@PathVariable("id") Integer id,Model model){

        model.addAttribute("user",userService.getUserById(id));
        return "user/editStuUser";
    }

    //教师用户编辑界面跳转
    @GetMapping("/teaUser/{id}")
    public String toTeaEditUserPage(@PathVariable("id") Integer id,Model model){

        model.addAttribute("user",userService.getUserById(id));
        return "user/editTeaUser";
    }

    //管理员编辑用户信息
    @PutMapping("/user")
    public String editUser(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("usertype") String usertype,
                           Map<String,Object> map,Model model){

        String oldUsername = userService.getUserById(id).getUsername();
        String oldUsertype = userService.getUserById(id).getUsertype();
        //try尝试在用户表中update数据，更新失败时catch错误并报错返回更新界面，成功则到用户列表界面
        try {
            userService.editUser(id,name, username, password, usertype);
        } catch (Exception e) {
            model.addAttribute("user",userService.getUserById(id));
            map.put("msg","用户名重复，请选择其他用户名！");
            return "user/addUser";
        }
        //若用学生户名修改后修改其余表中对应的用户名
        if (!oldUsername.equals(username) && oldUsertype.equals("student")){
            try {
                userFaultService.updateUserFaultByUsername(oldUsername,username);
                simulationTestAnswerService.updateSimuTestAnswerByUsername(oldUsername,username);
                simulationTestScoreService.updateSimuTestScoreByUsername(oldUsername,username);
            } catch (Exception e) {
                System.out.println("学生用户名修改后，错题表、答卷表、成绩表中对应的用户名修改失败！");
            }
        }
        //重定向到用户列表页面
        return "redirect:/users";
    }

    //学生用户自身信息修改
    @PutMapping("/stuUser")
    public String editStuUser(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("usertype") String usertype,
                              Map<String,Object> map, Model model, HttpSession session){

        String oldUsername = userService.getUserById(id).getUsername();
        //try尝试在用户表中update数据，更新失败时catch错误并报错返回更新界面，成功则到用户列表界面
        try {
            userService.editUser(id,name, username, password, usertype);
        } catch (Exception e) {
            model.addAttribute("user",userService.getUserById(id));
            map.put("msg","用户名重复，请选择其他用户名！");
            return "user/editStuUser";
        }
        //若用学生户名修改后修改其余表中对应的用户名
        if (!oldUsername.equals(username)){
            try {
                userFaultService.updateUserFaultByUsername(oldUsername,username);
                simulationTestAnswerService.updateSimuTestAnswerByUsername(oldUsername,username);
                simulationTestScoreService.updateSimuTestScoreByUsername(oldUsername,username);
            } catch (Exception e) {
                System.out.println("学生用户名修改后，错题表、答卷表、成绩表中对应的用户名修改失败！");
            }
        }
        session.setAttribute("loginUsername",name);
        session.setAttribute("loginUsersUsername",username);
        model.addAttribute("user",userService.getUserById(id));
        map.put("msg","修改成功！");
        //重定向到用户编辑页面
        return "user/editStuUser";
    }

    //教师用户自身信息修改
    @PutMapping("/teaUser")
    public String editTeaUser(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("usertype") String usertype,
                              Map<String,Object> map,Model model,HttpSession session){


        //try尝试在用户表中update数据，更新失败时catch错误并报错返回更新界面，成功则到用户列表界面
        try {
            userService.editUser(id,name, username, password, usertype);
        } catch (Exception e) {
            model.addAttribute("user",userService.getUserById(id));
            map.put("msg","用户名重复，请选择其他用户名！");
            return "user/editTeaUser";
        }
        //更新session中的值
        session.setAttribute("loginUsername",name);
        session.setAttribute("loginUsersUsername",username);
        model.addAttribute("user",userService.getUserById(id));
        map.put("msg","修改成功！");
        //重定向到用户编辑页面
        return "user/editTeaUser";
    }

    //删除用户
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){

        String username = userService.getUserById(id).getUsername();
        userService.deleteUserById(id);
        //同时删除用户模拟卷做题记录
        simulationTestAnswerService.deleteSimuTestAnswerByUsername(username);
        //删除模拟卷成绩记录
        simulationTestScoreService.deleteByUsername(username);
        //删除错题表记录
        userFaultService.deleteByUsername(username);
        return "redirect:/users";
    }

}
