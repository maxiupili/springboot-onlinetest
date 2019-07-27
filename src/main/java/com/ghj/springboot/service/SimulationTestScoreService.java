package com.ghj.springboot.service;

import com.ghj.springboot.model.SimulationTestScore;

import java.util.List;

public interface SimulationTestScoreService {

    /**
     * 查询所有
     * @return
     */
    List<SimulationTestScore> selectAll();

    /**
     * 依据id查询
     * @param simuTestScore_id
     * @return
     */
    SimulationTestScore selectById(Integer simuTestScore_id);

    /**
     * 依据id删除
     * @param simuTestScore_id
     */
    void deleteById(Integer simuTestScore_id);

    /**
     * 添加条目
     * @param username
     * @param simu_name
     * @param score
     * @param state
     */
    void addSimuTestScoreItem(String username,String simu_name,Integer score,String state);

    /**
     * 依据id编辑
     * @param simuTestScore_id
     * @param username
     * @param simu_name
     * @param score
     * @param state
     */
    void editSimuTestScoreItem(Integer simuTestScore_id,String username,String simu_name,Integer score,String state);

    /**
     * 根据username和simu_name
     * @param username
     * @param simu_name
     * @return 返回对应条目
     */
    SimulationTestScore selectByUsernameAndSimuName(String username,String simu_name);

    /**
     * 根据username
     * @param username
     * @return 返回该用户的记录集合
     */
    List<SimulationTestScore> selectAllByUsername(String username);

    /**
     * 查带批阅的卷子
     * @return
     */
    List<SimulationTestScore> selectUncheck();

    /**
     * 依据simu_name查询结果
     * @param simu_name
     * @return
     */
    List<SimulationTestScore> selectAllBySimuName(String simu_name);

    /**
     * 根据username删除对应用户的模拟卷成绩记录
     * @param username
     */
    void deleteByUsername(String username);

    /**
     * 根据simu_name删除对应模拟卷的成绩记录
     * @param simu_name
     */
    void deleteBySimuName(String simu_name);

    /**
     * 用户修改用户名后修改成绩表中对应的用户名
     * @param oldUsername
     * @param newUsername
     */
    void updateSimuTestScoreByUsername(String oldUsername,String newUsername);
}
