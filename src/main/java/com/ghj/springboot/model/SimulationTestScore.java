package com.ghj.springboot.model;

public class SimulationTestScore {

    private Integer simuTestScore_id;
    private String username;
    private String simu_name;
    private Integer Score;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSimuTestScore_id() {
        return simuTestScore_id;
    }

    public void setSimuTestScore_id(Integer simuTestScore_id) {
        this.simuTestScore_id = simuTestScore_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSimu_name() {
        return simu_name;
    }

    public void setSimu_name(String simu_name) {
        this.simu_name = simu_name;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    @Override
    public String toString() {
        return "SimulationTestScore{" +
                "simuTestScore_id=" + simuTestScore_id +
                ", username='" + username + '\'' +
                ", simu_name='" + simu_name + '\'' +
                ", Score=" + Score +
                ", state='" + state + '\'' +
                '}';
    }
}
