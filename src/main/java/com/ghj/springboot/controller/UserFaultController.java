package com.ghj.springboot.controller;

import com.ghj.springboot.model.UserFault;
import com.ghj.springboot.service.UserFaultService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserFaultController {

    @Autowired
    UserFaultService userFaultService;

    //标记错题即向错题表中插入数据，并避免插入重复数据
    @PostMapping("/addUserFault")
    public String addUserFaultRecord(@RequestParam("username") String username,
                                     @RequestParam("question_id")Integer question_id,
                                     @RequestParam("subject_id") Integer subject_id,
                                     @RequestParam("pageNum") Integer pageNum,
                                     HttpSession session) {
        UserFault userFault = userFaultService.getUserFaultByUsernameAndQuestionId(username,question_id);
        if (userFault == null) {
            try {
                userFaultService.addUserFault(username,question_id);
            } catch (Exception e) {
                session.setAttribute("addUserFaultMsg","标记错题失败！");
                session.setAttribute("thisQuestionId",question_id);
                return "redirect:/questionsClassify/"+subject_id+"?pageNum="+pageNum;
            }
            session.setAttribute("thisQuestionId",question_id);
            session.setAttribute("addUserFaultMsg","标记错题成功！");
            return "redirect:/questionsClassify/"+subject_id+"?pageNum="+pageNum;
        }
        //测试session的传值
        //map.put("msg","错题记录已存在，不需要重复标记！");
        session.setAttribute("thisQuestionId",question_id);
        session.setAttribute("addUserFaultMsg","错题记录已存在，不需要重复标记！");
        return "redirect:/questionsClassify/"+subject_id+"?pageNum="+pageNum;
    }

    //跳转到错题集分类页面
    @GetMapping("/questionsFaultClassify")
    public String toQuestionsFaultClassifyPage() {
        return "question/questionsFaultClassify";
    }

    //取消标记错题
    @DeleteMapping("/deleteUserFault")
    public String deleteUserFault(@RequestParam("username") String username,
                                  @RequestParam("question_id")Integer question_id,
                                  @RequestParam("subject_id") Integer subject_id) {
        //删除该用户该题号的错题记录
        userFaultService.deleteByUsernameAndQuestionId(username,question_id);

        return "redirect:/questionsClassify/"+username+"/"+subject_id+"?pageNum=1";


    }
}
