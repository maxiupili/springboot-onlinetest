package com.ghj.springboot.model;

public class SimulationTestCheckAnswerItem {

    private String username;
    private String simu_name;
    private Integer item_num;
    private Integer item_score;
    private String question_type;
    private String questiondetails;
    private String answer;
    private String imageurl;
    private String stu_answer;

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

    public Integer getItem_num() {
        return item_num;
    }

    public void setItem_num(Integer item_num) {
        this.item_num = item_num;
    }

    public Integer getItem_score() {
        return item_score;
    }

    public void setItem_score(Integer item_score) {
        this.item_score = item_score;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestiondetails() {
        return questiondetails;
    }

    public void setQuestiondetails(String questiondetails) {
        this.questiondetails = questiondetails;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStu_answer() {
        return stu_answer;
    }

    public void setStu_answer(String stu_answer) {
        this.stu_answer = stu_answer;
    }

    @Override
    public String toString() {
        return "SimulationTestCheckAnswerItem{" +
                "username='" + username + '\'' +
                ", simu_name='" + simu_name + '\'' +
                ", item_num=" + item_num +
                ", item_score=" + item_score +
                ", question_type='" + question_type + '\'' +
                ", questiondetails='" + questiondetails + '\'' +
                ", answer='" + answer + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", stu_answer='" + stu_answer + '\'' +
                '}';
    }
}
