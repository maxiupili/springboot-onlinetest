package com.ghj.springboot.service;

import com.ghj.springboot.model.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 查题库
     * @return 题库集
     */
    List<Question> getAllQuestion();

    /**
     * 依据题目id查询题目
     * @param question_id
     * @return 题目对象
     */
    Question getQuestionByQuestionId(Integer question_id);

    /**
     * 依据科目id查询题目集
     * @param subject_id
     * @return 指定科目代码的题目集合
     */
    List<Question> getQuestionsBySubjectId(Integer subject_id);


    /**
     * 添加问题
     * @param questiondetails
     * @param answer
     * @param question_type
     * @param rank_id
     * @param subject_id
     * @param imageurl
     */
    void addQuestion(String questiondetails,String answer,String question_type,Integer rank_id,Integer subject_id,String imageurl);

    /**
     * 根据id编辑问题
     * @param question_id
     * @param questiondetails
     * @param answer
     * @param question_type
     * @param rank_id
     * @param subject_id
     * @param imageurl
     */
    void editQuestion(Integer question_id,String questiondetails,String answer,String question_type,Integer rank_id,Integer subject_id,String imageurl);

    /**
     * 依据问题id删除
     * @param question_id
     */
    void deleteQuestionById(Integer question_id);

    /**
     * 根据题目类型和科目代号查题集
     * @param question_type
     * @param subject_id
     * @return 题目集合
     */
    List<Question> getQuestionsByQuestionTypeAndSubjectId(String question_type,Integer subject_id);
}
