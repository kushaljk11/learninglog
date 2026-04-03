package com.learninglog.learninglogproject.topic.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Topic {
    private  int id;
    private String name;
    private int userId;
    private Timestamp createdAt;

    public Topic(int id, String name, int userId, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}