package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.SimulationTestAnswerMapper;
import com.ghj.springboot.model.SimulationTestAnswer;
import com.ghj.springboot.model.SimulationTestCheckAnswerItem;
import com.ghj.springboot.service.SimulationTestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simulationTestAnswerService")
public class SimulationTestAnswerServiceImpl implements SimulationTestAnswerService {

    @Autowired
    SimulationTestAnswerMapper simulationTestAnswerMapper;

    @Override
    public SimulationTestAnswer selectBySimuAnswerId(Integer simuAnswer_id) {
        return simulationTestAnswerMapper.selectBySimuAnswerId(simuAnswer_id);
    }

    @Override
    public void addSimuTestAnswer(String username, String simu_name, Integer item_num, String answer) {
        simulationTestAnswerMapper.addSimuTestAnswer(username,simu_name,item_num,answer);
    }

    @Override
    public void editSimuTestAnswerById(Integer simuAnswer_id, String username, String simu_name, Integer item_num, String answer) {
        simulationTestAnswerMapper.editSimuTestAnswerById(simuAnswer_id,username,simu_name,item_num,answer);
    }

    @Override
    public void deleteSimuTestAnswerById(Integer simuAnswer_id) {
        simulationTestAnswerMapper.deleteSimuTestAnswerById(simuAnswer_id);
    }

    @Override
    public SimulationTestAnswer selectByUsernameAndSimuNameAndItemNum(String username, String simu_name, Integer item_num) {
        return simulationTestAnswerMapper.selectByUsernameAndSimuNameAndItemNum(username, simu_name, item_num);
    }

    @Override
    public Integer maxItem_num(String username, String simu_name) {
        return simulationTestAnswerMapper.maxItem_num(username, simu_name);
    }

    @Override
    public List<SimulationTestCheckAnswerItem> selectStuCheckAnswerItemByUsernameAndSimuName(String simu_name, String username) {
        return simulationTestAnswerMapper.selectStuCheckAnswerItemByUsernameAndSimuName(simu_name,username);
    }

    @Override
    public void deleteSimuTestAnswerByUsername(String username) {
        simulationTestAnswerMapper.deleteSimuTestAnswerByUsername(username);
    }

    @Override
    public void deleteSimuTestAnswerBySimuName(String simu_name) {
        simulationTestAnswerMapper.deleteSimuTestAnswerBySimuName(simu_name);
    }

    @Override
    public void updateSimuTestAnswerByUsername(String oldUsername, String newUsername) {
        simulationTestAnswerMapper.updateSimuTestAnswerByUsername(oldUsername,newUsername);
    }
}
