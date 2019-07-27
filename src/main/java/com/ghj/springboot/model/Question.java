package com.ghj.springboot.model;

public class Question {

    private Integer question_id;
    private String questiondetails; //题干
    private String answer;
    private String question_type;
    private Integer rank_id;
    private Integer subject_id;
    private String imageurl;       //图面地址、图片名

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", questiondetails='" + questiondetails + '\'' +
                ", answer='" + answer + '\'' +
                ", question_type='" + question_type + '\'' +
                ", rank_id=" + rank_id +
                ", subject_id=" + subject_id +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
