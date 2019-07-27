package com.ghj.springboot.service.impl;

import com.ghj.springboot.mapper.SimulationTestMapper;
import com.ghj.springboot.model.SimulationTestItem;
import com.ghj.springboot.model.SimulationTestMixItem;
import com.ghj.springboot.service.SimulationTestItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simulationTestItemService")
public class SimulationTestItemServiceImpl implements SimulationTestItemService {

    @Autowired
    SimulationTestMapper simulationTestMapper;

    @Override
    public List<SimulationTestItem> selactAllItem() {
        return simulationTestMapper.selactAllItem();
    }

    @Override
    public void deleteItemById(Integer simu_id) {
        simulationTestMapper.deleteItemById(simu_id);
    }

    @Override
    public void editItem(Integer simu_id, Integer item_num, Integer question_id, Integer item_score, String simu_name) {
        simulationTestMapper.editItem(simu_id,item_num,question_id,item_score,simu_name);
    }

    @Override
    public void addItem(Integer item_num, Integer question_id, Integer item_score, String simu_name) {
        simulationTestMapper.addItem(item_num,question_id,item_score,simu_name);
    }

    @Override
    public List<String> selectSimuName() {
        return simulationTestMapper.selectSimuName();
    }

    @Override
    public void deleteItemByQuestionId(Integer question_id) {
        simulationTestMapper.deleteItemByQuestionId(question_id);
    }

    @Override
    public void deleteItemBySimuName(String simu_name) {
        simulationTestMapper.deleteItemBySimuName(simu_name);
    }

    @Override
    public List<SimulationTestMixItem> selectMixItemBySimuName(String simuname) {
        return simulationTestMapper.selectMixItemBySimuName(simuname);
    }

    @Override
    public String selectSimuNameBySimuId(Integer simu_id) {
        return simulationTestMapper.selectSimuNameBySimuId(simu_id);
    }

    @Override
    public SimulationTestItem selectItemBySimuId(Integer simu_id) {
        return simulationTestMapper.selectItemBySimuId(simu_id);
    }

    @Override
    public SimulationTestItem selectItemByItemNumandSimuName(Integer item_num, String simu_name) {
        return simulationTestMapper.selectItemByItemNumandSimuName(item_num, simu_name);
    }
}
