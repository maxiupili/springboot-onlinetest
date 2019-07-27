package com.ghj.springboot.mapper;

import com.ghj.springboot.model.SimulationTestScore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SimulationTestScoreMapper {

    @Select("select * from simulationtestscore order by simu_name,score desc")
    List<SimulationTestScore> selectAll();

    @Select("select * from simulationtestscore where state='主观题待批阅' order by simu_name,score desc")
    List<SimulationTestScore> selectUncheck();

    @Select("select * from simulationtestscore where username=#{username}")
    List<SimulationTestScore> selectAllByUsername(String username);

    @Select("select * from simulationtestscore where simu_name=#{simu_name} order by score desc")
    List<SimulationTestScore> selectAllBySimuName(String simu_name);

    @Select("select * from simulationtestscore where simuTestScore_id=#{simuTestScore_id}")
    SimulationTestScore selectById(Integer simuTestScore_id);

    @Select("select * from simulationtestscore where username=#{username} and simu_name=#{simu_name}")
    SimulationTestScore selectByUsernameAndSimuName(String username,String simu_name);

    @Delete("delete from simulationtestscore where simuTestScore_id=#{simuTestScore_id}")
    void deleteById(Integer simuTestScore_id);

    @Delete("delete from simulationtestscore where username=#{username}")
    void deleteByUsername(String username);

    @Delete("delete from simulationtestscore where simu_name=#{simu_name}")
    void deleteBySimuName(String simu_name);

    @Insert("insert into simulationtestscore(username,simu_name,score,state) values(#{username},#{simu_name},#{score},#{state})")
    void addSimuTestScoreItem(String username,String simu_name,Integer score,String state);

    @Update("update simulationtestscore set username=#{username},simu_name=#{simu_name},score=#{score},state=#{state} where simuTestScore_id=#{simuTestScore_id}")
    void editSimuTestScoreItem(Integer simuTestScore_id,String username,String simu_name,Integer score,String state);

    @Update("update simulationtestscore set username=#{newUsername} where username=#{oldUsername}")
    void updateSimuTestScoreByUsername(String oldUsername,String newUsername);
}
