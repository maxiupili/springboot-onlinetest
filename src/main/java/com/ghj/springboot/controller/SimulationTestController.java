package com.ghj.springboot.controller;

import com.ghj.springboot.model.Question;
import com.ghj.springboot.model.SimulationTestMixItem;
import com.ghj.springboot.service.QuestionService;
import com.ghj.springboot.service.SimulationTestAnswerService;
import com.ghj.springboot.service.SimulationTestItemService;
import com.ghj.springboot.service.SimulationTestScoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class SimulationTestController {

    @Autowired
    SimulationTestItemService simulationTestItemService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SimulationTestAnswerService simulationTestAnswerService;

    @Autowired
    SimulationTestScoreService simulationTestScoreService;

    //root跳转到模拟卷主页面
    @GetMapping("/simulationTestRoot")
    public String toSimulationMainPage(Model model){

        List<String> SimuNames = simulationTestItemService.selectSimuName();
        model.addAttribute("SimuNames",SimuNames);
        return "simulation/testListRoot";
    }

    //tea跳转到模拟卷主页面
    @GetMapping("/simulationTestTeacher")
    public String toTeaSimulationMainPage(Model model){

        List<String> SimuNames = simulationTestItemService.selectSimuName();
        model.addAttribute("SimuNames",SimuNames);
        return "simulation/testListTea";
    }

    //stu跳转到模拟卷主页面
    @GetMapping("/simulationTestStu")
    public String toStuSimulationMainPage(Model model){

        List<String> SimuNames = simulationTestItemService.selectSimuName();
        model.addAttribute("SimuNames",SimuNames);
        return "simulation/testListStu";
    }

    //root根据simu_name删除记录，并返回到主页面
    @DeleteMapping("/simulationTestRoot")
    public String deleteSimuItemBySimuName(@RequestParam("simu_name") String simu_name) {
        simulationTestItemService.deleteItemBySimuName(simu_name);
        //同时删除答卷表中对应simu_name的记录
        simulationTestAnswerService.deleteSimuTestAnswerBySimuName(simu_name);
        //同时删除成绩表中对应simu_name的记录
        simulationTestScoreService.deleteBySimuName(simu_name);
        return "redirect:/simulationTestRoot";
    }

    //root根据simu_name到对应的预览界面
    @GetMapping("/previewSimulationTestRoot")
    public String toPreviewPage(@RequestParam("simu_name") String simu_name,Model model) {
        List<SimulationTestMixItem> simulationTestMixItems = simulationTestItemService.selectMixItemBySimuName(simu_name);
        model.addAttribute("simulationTestMixItems",simulationTestMixItems);
        model.addAttribute("simu_name",simu_name);
        return "simulation/previewSimuMixItem";
    }

    //tea根据simu_name到对应的预览界面
    @GetMapping("/previewSimulationTestTea")
    public String toTeaPreviewPage(@RequestParam("simu_name") String simu_name,Model model) {
        List<SimulationTestMixItem> simulationTestMixItems = simulationTestItemService.selectMixItemBySimuName(simu_name);
        model.addAttribute("simulationTestMixItems",simulationTestMixItems);
        model.addAttribute("simu_name",simu_name);
        return "simulation/previewSimuMixItemTea";
    }

    //学生用户根据simu_name到对应的预览界面
    @GetMapping("/previewSimulationTestStu")
    public String toStuPreviewPage(@RequestParam("simu_name") String simu_name,
                                   @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                   Model model) {

        PageHelper.startPage(pageNum,1);
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

    //根据simu_id删除条目并返回到该条目原本所在的模拟卷的预览界面
    @DeleteMapping("/deleteSimuItemById")
    public String deleteSimuItemById(@RequestParam("simu_id") Integer simu_id,Model model) {

        String simu_name = simulationTestItemService.selectSimuNameBySimuId(simu_id);
        simulationTestItemService.deleteItemById(simu_id);
        List<SimulationTestMixItem> simulationTestMixItems = simulationTestItemService.selectMixItemBySimuName(simu_name);
        model.addAttribute("simulationTestMixItems",simulationTestMixItems);
        model.addAttribute("simu_name",simu_name);

        return "simulation/previewSimuMixItem";
    }

    //跳转到添加模拟卷条目界面
    @GetMapping("/addSimuItem/{question_id}")
    public String toAddSimuItemPage(@PathVariable("question_id") Integer question_id,Model model){
        model.addAttribute("question_id",question_id);
        return "simulation/addSimuItem";
    }

    //添加SimuItem
    @PostMapping("/addSimuItem")
    public String addSimuItem(@RequestParam("item_num") Integer item_num,
                              @RequestParam("question_id") Integer question_id,
                              @RequestParam("item_score") Integer item_score,
                              @RequestParam("simu_name") String simu_name,
                              Model model) {
        try {
            simulationTestItemService.addItem(item_num,question_id,item_score,simu_name);
        } catch (Exception e) {
            model.addAttribute("question_id",question_id);
            model.addAttribute("msg","添加失败！");
            return "simulation/addSimuItem";
        }

        model.addAttribute("question_id",question_id);
        model.addAttribute("msg","添加成功！");
        return "simulation/addSimuItem";
    }


    //添加操作界面的返回
    @GetMapping("/comeBack/{question_id}")
    public String comeBackToQuestionsPage(@PathVariable("question_id") Integer question_id) {
        Question question = questionService.getQuestionByQuestionId(question_id);
        Integer subject_id = question.getSubject_id();
        return "redirect:/questionsClassifyRoot/"+subject_id;
    }

    //跳转到edit simuItem界面
    @GetMapping("/editSimuItem/{simu_id}")
    public String toEditSimuItemPage(@PathVariable("simu_id") Integer simu_id,Model model) {
        model.addAttribute("simulationTsetItem",simulationTestItemService.selectItemBySimuId(simu_id));
        return "simulation/editSimuItem";
    }

    //编辑simuItem
    @PutMapping("/editSimuItem")
    public String editSimuItem(@RequestParam("simu_id") Integer simu_id,
                               @RequestParam("item_num") Integer item_num,
                               @RequestParam("question_id") Integer question_id,
                               @RequestParam("item_score") Integer item_score,
                               @RequestParam("simu_name") String simu_name,
                               Model model) {
        try {
            simulationTestItemService.editItem(simu_id,item_num,question_id,item_score,simu_name);
        } catch (Exception e) {
            model.addAttribute("simulationTsetItem",simulationTestItemService.selectItemBySimuId(simu_id));
            model.addAttribute("msg","修改失败！");
            return "simulation/editSimuItem";
        }

        model.addAttribute("simulationTsetItem",simulationTestItemService.selectItemBySimuId(simu_id));
        model.addAttribute("msg","修改成功！");
        return "simulation/editSimuItem";
    }

    //返回到修改后的预览界面
    @GetMapping("/comeBackToPreview/{simu_id}")
    public String backToPreviewPage(@PathVariable("simu_id") Integer simu_id,Model model) {
        String simu_name = simulationTestItemService.selectItemBySimuId(simu_id).getSimu_name();
        List<SimulationTestMixItem> simulationTestMixItems = simulationTestItemService.selectMixItemBySimuName(simu_name);
        model.addAttribute("simulationTestMixItems",simulationTestMixItems);
        model.addAttribute("simu_name",simu_name);
        return "simulation/previewSimuMixItem";
    }

}
