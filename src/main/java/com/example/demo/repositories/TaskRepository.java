package com.example.demo.repositories;




import com.example.demo.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(Long id);

}