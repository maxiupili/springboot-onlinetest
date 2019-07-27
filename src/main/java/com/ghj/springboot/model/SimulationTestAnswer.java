package com.ghj.springboot.model;

public class SimulationTestAnswer {

    private Integer simuAnswer_id;
    private String uesrname;
    private String simu_name;
    private Integer item_num;
    private Integer question_id;
    private String answer;

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getSimuAnswer_id() {
        return simuAnswer_id;
    }

    public void setSimuAnswer_id(Integer simuAnswer_id) {
        this.simuAnswer_id = simuAnswer_id;
    }

    public String getUesrname() {
        return uesrname;
    }

    public void setUesrname(String uesrname) {
        this.uesrname = uesrname;
    }

    public String getSimu_name() {
        return simu_name;
    }

    public void setSimu_name(String simu_name) {
        this.simu_name = simu_name;
    }

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SimulationTestAnswer{" +
                "simuAnswer_id=" + simuAnswer_id +
                ", uesrname='" + uesrname + '\'' +
                ", simu_name='" + simu_name + '\'' +
                ", item_num=" + item_num +
                ", question_id=" + question_id +
                ", answer='" + answer + '\'' +
                '}';
    }
}
