package com.ghj.springboot.controller;

import com.ghj.springboot.model.Question;
import com.ghj.springboot.service.QuestionService;
import com.ghj.springboot.service.SimulationTestItemService;
import com.ghj.springboot.service.UserFaultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserFaultService userFaultService;

    @Autowired
    SimulationTestItemService simulationTestItemService;

//    测试成功
//    @ResponseBody
//    @GetMapping("/question/{question_id}")
//    public Question getQuestionById (@PathVariable("question_id") Integer question_id) {
//        return questionService.getQuestionByQuestionId(question_id);
//    }


    //跳转到指定页数的界面
    //@GetMapping("/questions/{pageNum}")
    //public String getQuestionsByPage



    //跳转到题库界面
    @GetMapping("/questions")
    public String getQuestions (Model model,
                                @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getAllQuestion();
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionlist";
    }

    //来到添加题目页面
    @GetMapping("/question")
    public String toAddQuestionPage() {
        return "question/addQuestion";
    }

    //post添加题目
    @PostMapping("/question")
    public String addQuestion(@RequestParam("questiondetails") String questiondetails,
                              @RequestParam("answer") String answer,
                              @RequestParam("question_type") String question_type,
                              @RequestParam("rank_id") Integer rank_id,
                              @RequestParam("subject_id") Integer subject_id,
                              @RequestParam("imageurl") String imageurl,
                              Map<String,Object> map) {
        if (questiondetails.isEmpty()||answer.isEmpty()||question_type.isEmpty()||rank_id==null||subject_id==null) {
            map.put("msg","题目信息不完善，添加题目失败！");
            return "question/addQuestion";
        }
        try {
            questionService.addQuestion(questiondetails,answer,question_type,rank_id,subject_id,imageurl);
        } catch (Exception e) {
            map.put("msg","添加题目失败！");
            return "question/addQuestion";
        }
        //回到题库界面
        return "redirect:/questionsClassifyRoot/"+subject_id;
    }

    //题目编辑界面跳转
    @GetMapping("/question/{question_id}")
    public String toEditQuestionPage(@PathVariable("question_id") Integer question_id,Model model){

        model.addAttribute("question",questionService.getQuestionByQuestionId(question_id));
        return "question/editQuestion";
    }

    //put修改题目
    @PutMapping("/question")
    public String editQuestion(@RequestParam("question_id") Integer question_id,
                               @RequestParam("questiondetails") String questiondetails,
                               @RequestParam("answer") String answer,
                               @RequestParam("question_type") String question_type,
                               @RequestParam("rank_id") Integer rank_id,
                               @RequestParam("subject_id") Integer subject_id,
                               @RequestParam("imageurl") String imageurl,
                               Map<String,Object> map,Model model) {
        try {
            questionService.editQuestion(question_id,questiondetails,answer,question_type,rank_id,subject_id,imageurl);
        } catch (Exception e) {

            model.addAttribute("question",questionService.getQuestionByQuestionId(question_id));
            map.put("msg","编辑题目失败！");
            return "question/editQuestion";

        }

        return "redirect:/questionsClassifyRoot/"+subject_id;
    }

    //删除题目
    @DeleteMapping("/question/{question_id}")
    public String deleteQuestion(@PathVariable("question_id") Integer question_id){

        Question question = questionService.getQuestionByQuestionId(question_id);
        Integer subject_id = question.getSubject_id();
        questionService.deleteQuestionById(question_id);
        //同时删除错题表中失效的错题记录
        userFaultService.deleteByQuestionId(question_id);
        //同时删除模拟卷表中含此问题id的记录
        simulationTestItemService.deleteItemByQuestionId(question_id);
        //返回到对应科目代号的首页
        return "redirect:/questionsClassifyRoot/"+subject_id;
    }

    //跳转到题库分类界面
    @GetMapping("/questionsClassify")
    public String toQuestionsClassifyPage() {
        return "question/questionsClassify";
    }


    //跳转到题库分类管理员界面
    @GetMapping("/questionsClassifyRoot")
    public String toQuestionsClassifyRootPage() {
        return "question/questionsClassifyRoot";
    }

    //从管理员分类界面跳转到对应题库
    @GetMapping("/questionsClassifyRoot/{subject_id}")
    public String toQuestionListPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                Model model,
                                                @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","choice");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsBySubjectId(subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionlist";
    }

    //从管理员分类界面跳转到选择题库
    @GetMapping("/questionsClassifyRoot/choice/{subject_id}")
    public String toChoiceQuestionListPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                Model model,
                                                @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","choice");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("选择题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionlist";
    }

    //从管理员分类界面跳转到填空题库
    @GetMapping("/questionsClassifyRoot/gapFilling/{subject_id}")
    public String toGapFillingQuestionListPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                      Model model,
                                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                      @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","gapFilling");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("填空题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionlist";
    }

    //从管理员分类界面跳转到主观题库
    @GetMapping("/questionsClassifyRoot/subjective/{subject_id}")
    public String toSubjectiveQuestionListPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                      Model model,
                                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                      @RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {
        model.addAttribute("questionType","subjective");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 2;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("主观题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,5);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionlist";
    }

    //从stu分类界面跳转到对应题库
    @GetMapping("/questionsClassify/{subject_id}")
    public String toQuestionSimpleTestPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                      Model model,
                                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                      @RequestParam(defaultValue="1",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","choice");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsBySubjectId(subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionSimpleTest";
    }

    //从stu分类界面跳转到选择题库
    @GetMapping("/questionsClassify/choice/{subject_id}")
    public String toChoiceQuestionSimpleTestPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                      Model model,
                                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                      @RequestParam(defaultValue="1",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","choice");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("选择题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionSimpleTest";
    }

    //从stu分类界面跳转到填空题库
    @GetMapping("/questionsClassify/gapFilling/{subject_id}")
    public String toGapFillingQuestionSimpleTestPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                            Model model,
                                                            @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                            @RequestParam(defaultValue="1",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","gapFilling");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("填空题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionSimpleTest";
    }

    //从stu分类界面跳转到主观题库
    @GetMapping("/questionsClassify/subjective/{subject_id}")
    public String toSubjectiveQuestionSimpleTestPageBySubjectId(@PathVariable("subject_id") Integer subject_id,
                                                            Model model,
                                                            @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                            @RequestParam(defaultValue="1",value="pageSize")Integer pageSize) {

        model.addAttribute("questionType","subjective");

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = questionService.getQuestionsByQuestionTypeAndSubjectId("主观题",subject_id);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/questionSimpleTest";
    }

    //跳转到对应用户错题查看界面
    @GetMapping("/questionsClassify/{username}/{subject_id}")
    public String toQuestionUserFaultCheckPageBySubjectId(@PathVariable("username") String username,
                                                      @PathVariable("subject_id") Integer subject_id,
                                                      Model model,
                                                      @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                                      @RequestParam(defaultValue="1",value="pageSize")Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;    //设置默认每页显示的数据数
        }

        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Question> questions = userFaultService.getQuestionsByUsernameAndSubjectId(subject_id,username);
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数,结果list类型是Page<E>
            //参数5是页码导航栏连续显示的页数
            PageInfo<Question> pageInfo = new PageInfo<>(questions,3);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("thisSubject_id",subject_id);
        } catch (Exception e) {
            //分页查询失败
            return "error/4XX";
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "question/userFaultCheck";
    }
}
