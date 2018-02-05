package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskTitle;

    private String taskDescription;

    private int assignedUser;


      public Task(){}

    public Task(String taskTitle, String taskDescription, int assignedUser) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.assignedUser = assignedUser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(int assignedUser) {
        this.assignedUser = assignedUser;
    }
}
