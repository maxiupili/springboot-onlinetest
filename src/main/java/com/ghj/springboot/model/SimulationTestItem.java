package com.ghj.springboot.model;

public class SimulationTestItem {

    private Integer simu_id;
    private Integer item_num;    //题目在卷子中的序号
    private Integer question_id;
    private Integer item_score;  //题目分值
    private String simu_name;    //模拟卷名字

    public Integer getSimu_id() {
        return simu_id;
    }

    public void setSimu_id(Integer simu_id) {
        this.simu_id = simu_id;
    }

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getItem_score() {
        return item_score;
    }

    public void setItem_score(Integer item_score) {
        this.item_score = item_score;
    }

    public String getSimu_name() {
        return simu_name;
    }

    public void setSimu_name(String simu_name) {
        this.simu_name = simu_name;
    }

    @Override
    public String toString() {
        return "SimulationTestItem{" +
                "simu_id=" + simu_id +
                ", item_num=" + item_num +
                ", question_id=" + question_id +
                ", item_score=" + item_score +
                ", simu_name='" + simu_name + '\'' +
                '}';
    }
}
