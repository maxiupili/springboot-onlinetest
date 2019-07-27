package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.UserFaultMapper;
import com.ghj.springboot.model.Question;
import com.ghj.springboot.model.UserFault;
import com.ghj.springboot.service.UserFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userFaultService")
public class UserFaultServiceImpl implements UserFaultService {

    @Autowired
    UserFaultMapper userFaultMapper;

    @Override
    public List<UserFault> getAllUserFaults() {
        return userFaultMapper.getAllUserFaults();
    }

    @Override
    public List<Integer> getQuestionIdByUsername(String username) {
        return userFaultMapper.getQuestionIdByUsername(username);
    }

    @Override
    public UserFault getUserFaultByUsernameAndQuestionId(String username, Integer question_id) {
        return userFaultMapper.getUserFaultByUsernameAndQuestionId(username,question_id);
    }

    @Override
    public void addUserFault(String username, Integer question_id) {
        userFaultMapper.addUserFault(username,question_id);
    }

    @Override
    public void editUserFault(String username, Integer oldQuestion_id, Integer newQuestion_id) {
        userFaultMapper.editUserFault(username,oldQuestion_id,newQuestion_id);
    }

    @Override
    public void deleteByUsernameAndQuestionId(String username, Integer question_id) {
        userFaultMapper.deleteByUsernameAndQuestionId(username,question_id);
    }

    @Override
    public void deleteByQuestionId(Integer question_id) {
        userFaultMapper.deleteByQuestionId(question_id);
    }

    @Override
    public List<Question> getQuestionsByUsernameAndSubjectId(Integer subject_id, String username) {
        return userFaultMapper.getQuestionsByUsernameAndSubjectId(subject_id,username);
    }

    @Override
    public void deleteByUsername(String username) {
        userFaultMapper.deleteByUsername(username);
    }

    @Override
    public void updateUserFaultByUsername(String oldUsername, String newUsername) {
        userFaultMapper.updateUserFaultByUsername(oldUsername,newUsername);
    }
}
