package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.QuestionMapper;
import com.ghj.springboot.model.Question;
import com.ghj.springboot.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestion() {
        return questionMapper.getAllQuestion();
    }

    @Override
    public Question getQuestionByQuestionId(Integer question_id) {
        return questionMapper.getQuestionByQuestionId(question_id);
    }

    @Override
    public List<Question> getQuestionsBySubjectId(Integer subject_id) {
        return questionMapper.getQuestionsBySubjectId(subject_id);
    }

    @Override
    public void addQuestion(String questiondetails, String answer, String question_type, Integer rank_id, Integer subject_id, String imageurl) {
        questionMapper.addQuestion(questiondetails,answer,question_type,rank_id,subject_id,imageurl);
    }

    @Override
    public void editQuestion(Integer question_id, String questiondetails, String answer, String question_type, Integer rank_id, Integer subject_id, String imageurl) {
        questionMapper.editQuestion(question_id,questiondetails,answer,question_type,rank_id,subject_id,imageurl);
    }

    @Override
    public void deleteQuestionById(Integer question_id) {
        questionMapper.deleteQuestionById(question_id);
    }

    @Override
    public List<Question> getQuestionsByQuestionTypeAndSubjectId(String question_type, Integer subject_id) {
        return questionMapper.getQuestionsByQuestionTypeAndSubjectId(question_type,subject_id);
    }
}
