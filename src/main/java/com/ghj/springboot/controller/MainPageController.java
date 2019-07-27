package com.ghj.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    //跳转到管理员大纲界面
    @GetMapping("/outLine-root")
    public String toOutlineRootPage() {
        return "ncreIntroduce/outline-root";
    }

    //跳转到学生大纲界面
    @GetMapping("/outLine-stu")
    public String toOutlineStuPage() {
        return "ncreIntroduce/outline-stu";
    }

    //跳转到教师大纲界面
    @GetMapping("/outLine-tea")
    public String toOutlineTeaPage() {
        return "ncreIntroduce/outline-stu";
    }

    //跳转到管理员考生须知界面
    @GetMapping("/stuNotice-root")
    public String toNoticeRootPage() {
        return "ncreIntroduce/notice-root";
    }

    //跳转到学生用户考生须知界面
    @GetMapping("/stuNotice-stu")
    public String toNoticeStuPage() {
        return "ncreIntroduce/notice-stu";
    }

    //跳转到教师用户考生须知界面
    @GetMapping("/stuNotice-tea")
    public String toNoticeTeaPage() {
        return "ncreIntroduce/notice-tea";
    }

    //跳转到管理员考试介绍界面
    @GetMapping("/examIntroduce-root")
    public String toExamIntroduceRootPage() {
        return "ncreIntroduce/examIntroduce-root";
    }

    //跳转到学生用户考试介绍界面
    @GetMapping("/examIntroduce-stu")
    public String toExamIntroduceStuPage() {
        return "ncreIntroduce/examIntroduce-stu";
    }

    //跳转到学生用户考试介绍界面
    @GetMapping("/examIntroduce-tea")
    public String toExamIntroduceTeaPage() {
        return "ncreIntroduce/examIntroduce-tea";
    }
}