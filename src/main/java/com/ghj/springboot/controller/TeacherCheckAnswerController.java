package com.ghj.springboot.controller;

import com.ghj.springboot.model.SimulationTestCheckAnswerItem;
import com.ghj.springboot.model.SimulationTestScore;
import com.ghj.springboot.service.SimulationTestAnswerService;
import com.ghj.springboot.service.SimulationTestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherCheckAnswerController {

    @Autowired
    SimulationTestAnswerService simulationTestAnswerService;

    @Autowired
    SimulationTestScoreService simulationTestScoreService;

    //教师用户跳转到对应学生模拟卷答题集
    @PostMapping("/teacherCheckStuAnswer")
    public String toTeaCheckStuAnswerPage(@RequestParam("simu_name") String simu_name,
                                          @RequestParam("username") String username,
                                          Model model) {

        List<SimulationTestCheckAnswerItem> simulationTestCheckAnswerItems = simulationTestAnswerService.selectStuCheckAnswerItemByUsernameAndSimuName(simu_name,username);
        SimulationTestScore simulationTestScore = simulationTestScoreService.selectByUsernameAndSimuName(username,simu_name);

        model.addAttribute("simulationTestCheckAnswerItems",simulationTestCheckAnswerItems);
        model.addAttribute("thisPaperSimuName",simu_name);
        model.addAttribute("thisPaperUsername",username);
        model.addAttribute("thisPaperScore",simulationTestScore.getScore());

        return "simulation/teacherCheckStuSimuTestAnswer";
    }

    //教师用户批阅主观题
    @PostMapping("/teacherPlusSimuTestScore")
    public String teaPlusSimuTestScore(@RequestParam("simu_name") String simu_name,
                                       @RequestParam("username") String username,
                                       @RequestParam("score") Integer score){
        SimulationTestScore simulationTestScore = simulationTestScoreService.selectByUsernameAndSimuName(username,simu_name);
        score = score + simulationTestScore.getScore();
        try {
            simulationTestScoreService.editSimuTestScoreItem(simulationTestScore.getSimuTestScore_id(),username,simu_name,score,"主观题已批阅");
        } catch (Exception e) {
            System.out.println("教师批阅完更新学生模拟卷成绩失败！");
        }

        return "redirect:/toTeacherScorePage";
    }
}
