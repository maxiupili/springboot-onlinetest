package com.ghj.springboot.model;

public class SimulationTestMixItem {

    private Integer simu_id;
    private Integer item_num;
    private Integer item_score;
    private Integer question_id;
    private String questiondetails;
    private String answer;
    private String question_type;
    private Integer rank_id;
    private Integer subject_id;
    private String imageurl;

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

    public Integer getItem_score() {
        return item_score;
    }

    public void setItem_score(Integer item_score) {
        this.item_score = item_score;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
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

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public Integer getRank_id() {
        return rank_id;
    }

    public void setRank_id(Integer rank_id) {
        this.rank_id = rank_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "SimulationTestMixItem{" +
                "simu_id=" + simu_id +
                ", item_num=" + item_num +
                ", item_score=" + item_score +
                ", question_id=" + question_id +
                ", questiondetails='" + questiondetails + '\'' +
                ", answer='" + answer + '\'' +
                ", question_type='" + question_type + '\'' +
                ", rank_id=" + rank_id +
                ", subject_id=" + subject_id +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
