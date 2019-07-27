package com.ghj.springboot.controller;

import com.ghj.springboot.model.Question;
import com.ghj.springboot.model.SimulationTestAnswer;
import com.ghj.springboot.model.SimulationTestItem;
import com.ghj.springboot.model.SimulationTestScore;
import com.ghj.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SimulationTestScoreController {

    @Autowired
    SimulationTestScoreService simulationTestScoreService;

    @Autowired
    SimulationTestAnswerService simulationTestAnswerService;

    @Autowired
    SimulationTestItemService simulationTestItemService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserFaultService userFaultService;


    //学生界面导航栏跳转
    @GetMapping("/toStuScorePage/{username}")
    public String toStuScorePage(@PathVariable("username") String username,Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAllByUsername(username);
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreStu";
    }

    //管理员界面导航栏跳转
    @GetMapping("/toRootScorePage")
    public String toRootScorePage(Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAll();
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreRoot";
    }

    //管理员页面按用户名查模拟卷答题记录
    @PostMapping("/rootSelectScoreByUsername")
    public String rootSelectScoreByUsername(@RequestParam("username") String username,Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAllByUsername(username);
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreRoot";
    }

    //管理员页面按用户名查模拟卷答题记录
    @PostMapping("/rootSelectScoreBySimuName")
    public String rootSelectScoreBySimuName(@RequestParam("simu_name") String simu_name,Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAllBySimuName(simu_name);
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreRoot";
    }

    //教师界面导航栏跳转
    @GetMapping("/toTeacherScorePage")
    public String toTeacherScorePage(Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAll();
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreTea";
    }

    //教师页面只显示待批阅
    @GetMapping("/showUncheckItemOnly")
    public String showUncheckItemOnly(Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectUncheck();
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreTea";
    }

    //教师页面按用户名查模拟卷答题记录
    @PostMapping("/teacherSelectScoreByUsername")
    public String teacherSelectScoreByUsername(@RequestParam("username") String username,Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAllByUsername(username);
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreTea";
    }

    //教师页面按用户名查模拟卷答题记录
    @PostMapping("/teacherSelectScoreBySimuName")
    public String teacherSelectScoreBySimuName(@RequestParam("simu_name") String simu_name,Model model) {
        List<SimulationTestScore> simulationTestScores = simulationTestScoreService.selectAllBySimuName(simu_name);
        model.addAttribute("simulationTestScores",simulationTestScores);
        return "simulation/simulationTestScoreTea";
    }

    //stu提交试卷，计算分数并录入同时标记错题，跳转到结分界面
    @PostMapping("/submitPaper")
    public String submitPaperAndScore(@RequestParam("username") String username,
                                      @RequestParam("simu_name") String simu_name,
                                      Model model) {
        Integer maxItem_num = simulationTestAnswerService.maxItem_num(username, simu_name);
        Integer grossScore = 0;
        String state = "主观题待批阅";

        if (maxItem_num == null) {
            return "redirect:/toStuScorePage/"+username;
        } else {
            for (int i = 1; i < maxItem_num+1; i++) {
                SimulationTestAnswer simulationTestAnswer = simulationTestAnswerService.selectByUsernameAndSimuNameAndItemNum(username,simu_name,i);
                //学生未提交该题的答案的情况
                if (simulationTestAnswer == null) {
                    continue;
                }
                String stuAnswer = simulationTestAnswer.getAnswer();
                SimulationTestItem simulationTestItem = simulationTestItemService.selectItemByItemNumandSimuName(i,simu_name);
                Integer question_id = simulationTestItem.getQuestion_id();
                Integer item_score = simulationTestItem.getItem_score();
                Question question = questionService.getQuestionByQuestionId(question_id);
                String rightAnswer = question.getAnswer();
                if (stuAnswer.equals(rightAnswer)||stuAnswer.toUpperCase().equals(rightAnswer)) {
                    grossScore = grossScore + item_score;
                } else {
                    //向错题表插入错题
                    if (userFaultService.getUserFaultByUsernameAndQuestionId(username,question_id) == null) {
                        try {
                            userFaultService.addUserFault(username,question_id);
                        } catch (Exception e) {
                            System.out.println("插入错题失败！");
                        }
                    }
                }
            }
            SimulationTestScore simulationTestScore = simulationTestScoreService.selectByUsernameAndSimuName(username, simu_name);
            if (simulationTestScore == null) {
                try {
                    simulationTestScoreService.addSimuTestScoreItem(username,simu_name,grossScore,state);
                } catch (Exception e) {
                    System.out.println("模拟卷分数插入失败！");
                }
            } else {
                try {
                    simulationTestScoreService.editSimuTestScoreItem(simulationTestScore.getSimuTestScore_id(),username,simu_name,grossScore,state);
                } catch (Exception e) {
                    System.out.println("模拟卷分数编辑失败！");
                }
            }
            return "redirect:/toStuScorePage/"+username;
        }

    }
}
