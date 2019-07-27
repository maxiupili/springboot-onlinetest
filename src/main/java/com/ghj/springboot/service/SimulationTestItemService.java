package com.ghj.springboot.service;

import com.ghj.springboot.model.SimulationTestItem;
import com.ghj.springboot.model.SimulationTestMixItem;

import java.util.List;

public interface SimulationTestItemService {

    /**
     * 查询全部模拟卷题目条目
     * @return 条目合集
     */
    List<SimulationTestItem> selactAllItem();

    /**
     * 依据simu_id删除条目
     * @param simu_id
     */
    void deleteItemById(Integer simu_id);

    /**
     * 根据simu_id编辑条目
     * @param simu_id
     * @param item_num
     * @param question_id
     * @param item_score
     * @param simu_name
     */
    void editItem(Integer simu_id,Integer item_num,Integer question_id,Integer item_score,String simu_name);

    /**
     * 添加条目
     * @param item_num
     * @param question_id
     * @param item_score
     * @param simu_name
     */
    void addItem(Integer item_num,Integer question_id,Integer item_score,String simu_name);

    /**
     * 查询模拟卷子名
     * @return
     */
    List<String> selectSimuName();

    /**
     * 根据question_id删除记录
     * @param question_id
     */
    void deleteItemByQuestionId(Integer question_id);

    /**
     * 根据模拟卷名删除记录
     * @param simu_name
     */
    void deleteItemBySimuName(String simu_name);

    /**
     * 根据模拟卷名
     * @param simuname
     * @return 返回混合集
     */
    List<SimulationTestMixItem> selectMixItemBySimuName(String simuname);

    /**
     * 根据simu_id
     * @param simu_id
     * @return 返回对应的simu_name
     */
    String selectSimuNameBySimuId(Integer simu_id);

    /**
     * 根据simu_id
     * @param simu_id
     * @return 返回对应Item
     */
    SimulationTestItem selectItemBySimuId(Integer simu_id);

    /**
     * 根据item_num和simu_num
     * @param item_num
     * @param simu_name
     * @return 返回对应条目
     */
    SimulationTestItem selectItemByItemNumandSimuName(Integer item_num,String simu_name);
}
