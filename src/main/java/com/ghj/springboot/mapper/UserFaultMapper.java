package com.ghj.springboot.mapper;

import com.ghj.springboot.model.Question;
import com.ghj.springboot.model.UserFault;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserFaultMapper {

    @Select("select * from userfault")
    List<UserFault> getAllUserFaults();

    @Select("select question_id from userfault where username=#{username}")
    List<Integer> getQuestionIdByUsername(String username);

    @Select("select * from userfault where username=#{username} and question_id=#{question_id}")
    UserFault getUserFaultByUsernameAndQuestionId(String username,Integer question_id);

    @Select("select * from question where subject_id=#{subject_id} and question_id in (select question_id from userfault where username=#{username})")
    List<Question> getQuestionsByUsernameAndSubjectId(Integer subject_id,String username);

    @Insert("insert into userfault(username,question_id) values(#{username},#{question_id})")
    void addUserFault(String username,Integer question_id);

    @Update("update userfault set question_id=#{newQuestion_id} where username=#{username} and question_id=#{oldQuestion_id}")
    void editUserFault(String username,Integer oldQuestion_id,Integer newQuestion_id);

    @Update("update userfault set username=#{newUsername} where username=#{oldUsername}")
    void updateUserFaultByUsername(String oldUsername,String newUsername);

    @Delete("delete from userfault where username=#{username} and question_id=#{question_id}")
    void deleteByUsernameAndQuestionId(String username,Integer question_id);

    @Delete("delete from userfault where question_id=#{question_id}")
    void deleteByQuestionId(Integer question_id);

    @Delete("delete from userfault where username=#{username}")
    void deleteByUsername(String username);


}
