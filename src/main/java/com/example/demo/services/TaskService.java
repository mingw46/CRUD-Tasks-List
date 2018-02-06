package com.example.demo.services;

import com.example.demo.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> ListAll();

    Task getById(Long id);

    Task saveOrUpdate(Task task);

    void deleteTask(Long id);


}
