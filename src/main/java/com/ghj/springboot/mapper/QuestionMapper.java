package com.ghj.springboot.mapper;

import com.ghj.springboot.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface QuestionMapper {

    @Select("select * from question")
    List<Question> getAllQuestion();

    @Select("select * from question where question_id = #{question_id}")
    Question getQuestionByQuestionId(Integer question_id);

    @Select("select * from question where subject_id = #{subject_id}")
    List<Question> getQuestionsBySubjectId(Integer subject_id);

    @Select("select * from question where question_type=#{question_type} and subject_id = #{subject_id}")
    List<Question> getQuestionsByQuestionTypeAndSubjectId(String question_type,Integer subject_id);

    @Insert("insert into question(questiondetails,answer,question_type,rank_id,subject_id,imageurl) values(#{questiondetails},#{answer},#{question_type},#{rank_id},#{subject_id},#{imageurl})")
    void addQuestion(String questiondetails,String answer,String question_type,Integer rank_id,Integer subject_id,String imageurl);

    @Update("update question set questiondetails=#{questiondetails},answer=#{answer},question_type=#{question_type},rank_id=#{rank_id},subject_id=#{subject_id},imageurl=#{imageurl} where question_id=#{question_id}")
    void editQuestion(Integer question_id,String questiondetails,String answer,String question_type,Integer rank_id,Integer subject_id,String imageurl);

    @Delete("delete from question where question_id=#{question_id}")
    void deleteQuestionById(Integer question_id);

}
