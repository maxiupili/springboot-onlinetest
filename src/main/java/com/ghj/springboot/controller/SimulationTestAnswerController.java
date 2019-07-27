package com.ghj.springboot.controller;

import com.ghj.springboot.model.SimulationTestAnswer;
import com.ghj.springboot.model.SimulationTestMixItem;
import com.ghj.springboot.service.SimulationTestAnswerService;
import com.ghj.springboot.service.SimulationTestItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SimulationTestAnswerController {

    @Autowired
    SimulationTestAnswerService simulationTestAnswerService;

    @Autowired
    SimulationTestItemService simulationTestItemService;

    //做题提交答案并跳转到下一题
    @PostMapping("/addSimuTestAnswer")
    public String addSimuTestAnswerRecord(@RequestParam("username") String username,
                                          @RequestParam("simu_name") String simu_name,
                                          @RequestParam("item_num") Integer item_num,
                                          @RequestParam("answer") String answer, Model model) {
        SimulationTestAnswer simulationTestAnswer = simulationTestAnswerService.selectByUsernameAndSimuNameAndItemNum(username, simu_name, item_num);
        //已有该题记录则更新答案，无则插入记录
        if (simulationTestAnswer == null) {
            try {
                simulationTestAnswerService.addSimuTestAnswer(username,simu_name,item_num,answer);
            } catch (Exception e) {
                System.out.println("答题记录插入出错！");
            }
        } else {
            Integer simuAnswer_id = simulationTestAnswer.getSimuAnswer_id();
            try{
                simulationTestAnswerService.editSimuTestAnswerById(simuAnswer_id,username,simu_name,item_num,answer);
            } catch (Exception e) {
                System.out.println("答题记录更新出错！");
            }
        }
        PageHelper.startPage(item_num+1,1);
        try {
            List<SimulationTestMixItem> simulationTestMixItems = simulationTestItemService.selectMixItemBySimuName(simu_name);

            PageInfo<SimulationTestMixItem> pageInfo = new PageInfo<>(simulationTestMixItems,5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("simu_name",simu_name);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "simulation/previewSimuMixItemStu";
    }
}
