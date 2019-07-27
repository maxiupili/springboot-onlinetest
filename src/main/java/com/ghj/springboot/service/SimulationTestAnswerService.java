package com.ghj.springboot.service;

import com.ghj.springboot.model.SimulationTestAnswer;
import com.ghj.springboot.model.SimulationTestCheckAnswerItem;

import java.util.List;

public interface SimulationTestAnswerService {

    /**
     * 根据id
     * @param simuAnswer_id
     * @return 对应答题记录
     */
    SimulationTestAnswer selectBySimuAnswerId(Integer simuAnswer_id);

    /**
     * 添加记录
     * @param username
     * @param simu_name
     * @param item_num
     * @param answer
     */
    void addSimuTestAnswer(String username,String simu_name,Integer item_num,String answer);

    /**
     * 根据id编辑记录
     * @param simuAnswer_id
     * @param username
     * @param simu_name
     * @param item_num
     * @param answer
     */
    void editSimuTestAnswerById(Integer simuAnswer_id,String username,String simu_name,Integer item_num,String answer);

    /**
     * 根据id删除记录
     * @param simuAnswer_id
     */
    void deleteSimuTestAnswerById(Integer simuAnswer_id);

    /**
     * 根据三个属性查询是否已有记录
     * @param username
     * @param simu_name
     * @param item_num
     * @return 该记录或null
     */
    SimulationTestAnswer selectByUsernameAndSimuNameAndItemNum(String username,String simu_name,Integer item_num);

    /**
     * 根据username和simu_name返回对应学生做题数量
     * @param username
     * @param simu_name
     * @return
     */
    Integer maxItem_num(String username,String simu_name);

    /**
     * 根据simu_name和username
     * @param simu_name
     * @param username
     * @return 返回校对答案条目合集
     */
    List<SimulationTestCheckAnswerItem> selectStuCheckAnswerItemByUsernameAndSimuName(String simu_name, String username);

    /**
     * 根据username删除模拟卷作答表中对应用户的记录
     * @param username
     */
    void deleteSimuTestAnswerByUsername(String username);

    /**
     * 根据username删除对应的答题记录
     * @param simu_name
     */
    void deleteSimuTestAnswerBySimuName(String simu_name);

    /**
     * 用户修改了用户名后在答卷表中修改对应的数据
     * @param oldUsername
     * @param newUsername
     */
    void updateSimuTestAnswerByUsername(String oldUsername,String newUsername);
}
