package com.ghj.springboot.mapper;

import com.ghj.springboot.model.SimulationTestAnswer;
import com.ghj.springboot.model.SimulationTestCheckAnswerItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SimulationTestAnswerMapper {

    @Select("select * from simulationtestanswer where simuAnswer_id=#{simuAnswer_id}")
    SimulationTestAnswer selectBySimuAnswerId(Integer simuAnswer_id);

    @Select("select * from simulationtestanswer where username=#{username} and simu_name=#{simu_name} and item_num=#{item_num}")
    SimulationTestAnswer selectByUsernameAndSimuNameAndItemNum(String username,String simu_name,Integer item_num);

    @Insert("insert into simulationtestanswer(username,simu_name,item_num,answer) values(#{username},#{simu_name},#{item_num},#{answer})")
    void addSimuTestAnswer(String username,String simu_name,Integer item_num,String answer);

    @Update("update simulationtestanswer set username=#{username},simu_name=#{simu_name},item_num=#{item_num},answer=#{answer} where simuAnswer_id=#{simuAnswer_id}")
    void editSimuTestAnswerById(Integer simuAnswer_id,String username,String simu_name,Integer item_num,String answer);

    @Update("update simulationtestanswer set username=#{newUsername} where username=#{oldUsername}")
    void updateSimuTestAnswerByUsername(String oldUsername,String newUsername);

    @Delete("delete from simulationtestanswer where simuAnswer_id=#{simuAnswer_id}")
    void deleteSimuTestAnswerById(Integer simuAnswer_id);

    @Delete("delete from simulationtestanswer where username=#{username}")
    void deleteSimuTestAnswerByUsername(String username);

    @Delete("delete from simulationtestanswer where simu_name=#{simu_name}")
    void deleteSimuTestAnswerBySimuName(String simu_name);

    @Select("select max(item_num) from simulationtestanswer where username=#{username} and simu_name=#{simu_name}")
    Integer maxItem_num(String username,String simu_name);

    @Select("select b.username,a.simu_name,a.item_num,a.item_score,c.question_type,c.questiondetails,c.answer,c.imageurl,b.answer AS 'stu_answer' from simulationtest a,simulationtestanswer b,question c where a.question_id=c.question_id and a.item_num = b.item_num and a.simu_name = #{simu_name} and b.simu_name = a.simu_name and b.username = #{username} order by a.item_num")
    List<SimulationTestCheckAnswerItem> selectStuCheckAnswerItemByUsernameAndSimuName(String simu_name,String username);
}
