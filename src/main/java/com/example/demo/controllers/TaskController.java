package com.example.demo.controllers;

import com.example.demo.domain.Task;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class TaskController {

    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/task")
    public String helloWorld(Model model){
        model.addAttribute("tasks", taskService.ListAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("username: " + auth.getName());
        return "tasksList";
    }
    @RequestMapping("/show/{id}")
    public String getTask(@PathVariable String id, Model model){
        model.addAttribute("product", taskService.getById(Long.valueOf(id)));
        return "editTask";
    }


    @RequestMapping("/new")
    public String addTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public String addTaskFromForm(@Valid @ModelAttribute("task") Task task,
                                  Model model, BindingResult result)
    {

        if(result.hasErrors()){
            return "error";
        }


        taskService.saveOrUpdate(task);

        return "redirect:/task";
    }

    @RequestMapping(value = "/task/delete/{id}")
    public String deleteTaskById(@PathVariable String id){
        taskService.deleteTask(Long.valueOf(id));
        return "redirect:/task";
    }

    @RequestMapping(value = "/task/edit/{id}")
    public String saveTaskById(@PathVariable String id, Model model){

       model.addAttribute("task", taskService.getById(Long.valueOf(id)));
       model.addAttribute("users", userRepository.findAll());
        return "editTask";
    }

    @RequestMapping(value = "/task/edit/{id}", method = RequestMethod.POST)
    public String updateTaskById(@ModelAttribute("task") Task task,
                                 @PathVariable("id") String id,
                                 Model model){

        model.addAttribute("succes","Task successfully edited");
        taskService.saveOrUpdate(task);
        return "editTask";
    }



}
