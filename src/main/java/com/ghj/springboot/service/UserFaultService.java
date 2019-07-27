package com.ghj.springboot.service;

import com.ghj.springboot.model.Question;
import com.ghj.springboot.model.UserFault;

import java.util.List;

public interface UserFaultService {

    /**
     * 查询整个表
     * @return 该表的所有数据的list集合
     */
    List<UserFault> getAllUserFaults();

    /**
     * 根据用户的用户名查询用户标记的错题id
     * @param username
     * @return 错题id的list集合
     */
    List<Integer> getQuestionIdByUsername(String username);

    /**
     * 根据用户名和错题id查询记录
     * @param username
     * @param question_id
     * @return 错题记录对象
     */
    UserFault getUserFaultByUsernameAndQuestionId(String username,Integer question_id);

    /**
     * 向userfault表中插入数据
     * @param username
     * @param question_id
     */
    void addUserFault(String username,Integer question_id);

    /**
     * 编辑更新表中数据，根据用户名和错题id查询到数据并更改
     * @param username
     * @param oldQuestion_id
     * @param newQuestion_id
     */
    void editUserFault(String username,Integer oldQuestion_id,Integer newQuestion_id);

    /**
     * 根据用户名和错题id来定位删除数据
     * @param username
     * @param question_id
     */
    void deleteByUsernameAndQuestionId(String username,Integer question_id);

    /**
     * 根据题目id删除所有与此题相关的错题记录（用于管理员删除题目同时的操作）
     * @param question_id
     */
    void deleteByQuestionId(Integer question_id);

    /**
     * 查找个人分科目的错题集
     * @param subject_id
     * @param username
     * @return 错题集
     */
    List<Question> getQuestionsByUsernameAndSubjectId(Integer subject_id, String username);

    /**
     * 根据用户名删除该用户的错题记录
     * @param username
     */
    void deleteByUsername(String username);

    /**
     * 用户更新用户名后修改该用户错题集中的用户名
     * @param oldUsername
     * @param newUsername
     */
    void updateUserFaultByUsername(String oldUsername,String newUsername);
}
