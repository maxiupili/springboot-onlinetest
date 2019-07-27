package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.SimulationTestScoreMapper;
import com.ghj.springboot.model.SimulationTestScore;
import com.ghj.springboot.service.SimulationTestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simulationTestScoreService")
public class SimulationTestScoreServiceImpl implements SimulationTestScoreService {

    @Autowired
    SimulationTestScoreMapper simulationTestScoreMapper;

    @Override
    public List<SimulationTestScore> selectAll() {
        return simulationTestScoreMapper.selectAll();
    }

    @Override
    public SimulationTestScore selectById(Integer simuTestScore_id) {
        return simulationTestScoreMapper.selectById(simuTestScore_id);
    }

    @Override
    public void deleteById(Integer simuTestScore_id) {
        simulationTestScoreMapper.deleteById(simuTestScore_id);
    }

    @Override
    public void addSimuTestScoreItem(String username, String simu_name, Integer score, String state) {
        simulationTestScoreMapper.addSimuTestScoreItem(username, simu_name, score, state);
    }

    @Override
    public void editSimuTestScoreItem(Integer simuTestScore_id, String username, String simu_name, Integer score, String state) {
        simulationTestScoreMapper.editSimuTestScoreItem(simuTestScore_id,username,simu_name,score,state);
    }

    @Override
    public SimulationTestScore selectByUsernameAndSimuName(String username, String simu_name) {
        return simulationTestScoreMapper.selectByUsernameAndSimuName(username, simu_name);
    }

    @Override
    public List<SimulationTestScore> selectAllByUsername(String username) {
        return simulationTestScoreMapper.selectAllByUsername(username);
    }

    @Override
    public List<SimulationTestScore> selectUncheck() {
        return simulationTestScoreMapper.selectUncheck();
    }

    @Override
    public List<SimulationTestScore> selectAllBySimuName(String simu_name) {
        return simulationTestScoreMapper.selectAllBySimuName(simu_name);
    }

    @Override
    public void deleteByUsername(String username) {
        simulationTestScoreMapper.deleteByUsername(username);
    }

    @Override
    public void deleteBySimuName(String simu_name) {
        simulationTestScoreMapper.deleteBySimuName(simu_name);
    }

    @Override
    public void updateSimuTestScoreByUsername(String oldUsername, String newUsername) {
        simulationTestScoreMapper.updateSimuTestScoreByUsername(oldUsername,newUsername);
    }
}
