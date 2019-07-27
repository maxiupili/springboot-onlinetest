package com.ghj.springboot.model;

public class UserFault {

    private Integer userfault_id;
    private String username;
    private Integer question_id;

    public Integer getUserfault_id() {
        return userfault_id;
    }

    public void setUserfault_id(Integer userfault_id) {
        this.userfault_id = userfault_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
}
