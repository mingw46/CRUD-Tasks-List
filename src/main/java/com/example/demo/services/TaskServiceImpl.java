package com.example.demo.services;

import com.example.demo.domain.Task;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    @Override
    public List<Task> ListAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveOrUpdate(Task task) {
        taskRepository.save(task);
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }



}
