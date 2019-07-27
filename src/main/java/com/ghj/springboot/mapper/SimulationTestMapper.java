package com.ghj.springboot.mapper;

import com.ghj.springboot.model.SimulationTestItem;
import com.ghj.springboot.model.SimulationTestMixItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SimulationTestMapper {

    @Select("select * from simulationtest")
    List<SimulationTestItem> selactAllItem();

    @Delete("delete from simulationtest where simu_id=#{simu_id}")
    void deleteItemById(Integer simu_id);

    @Delete("delete from simulationtest where question_id=#{question_id}")
    void deleteItemByQuestionId(Integer question_id);

    @Delete("delete from simulationtest where simu_name=#{simu_name}")
    void deleteItemBySimuName(String simu_name);

    @Update("update simulationtest set item_num=#{item_num},question_id=#{question_id},item_score=#{item_score},simu_name=#{simu_name} where simu_id=#{simu_id}")
    void editItem(Integer simu_id,Integer item_num,Integer question_id,Integer item_score,String simu_name);

    @Insert("insert into simulationtest(item_num,question_id,item_score,simu_name) values(#{item_num},#{question_id},#{item_score},#{simu_name})")
    void addItem(Integer item_num,Integer question_id,Integer item_score,String simu_name);

    @Select("select simu_name from simulationtest group by simu_name")
    List<String> selectSimuName();

    @Select("select a.simu_id,a.item_num,a.item_score,a.question_id,b.questiondetails,b.answer,b.question_type,b.rank_id,b.subject_id,b.imageurl from simulationtest a,question b where a.question_id = b.question_id and a.simu_name = #{simu_name} order by a.item_num")
    List<SimulationTestMixItem> selectMixItemBySimuName(String simuname);

    @Select("select simu_name from simulationtest where simu_id=#{simu_id}")
    String selectSimuNameBySimuId(Integer simu_id);

    @Select("select * from simulationtest where simu_id=#{simu_id}")
    SimulationTestItem selectItemBySimuId(Integer simu_id);

    @Select("select * from simulationtest where item_num=#{item_num} and simu_name=#{simu_name}")
    SimulationTestItem selectItemByItemNumandSimuName(Integer item_num,String simu_name);

}
