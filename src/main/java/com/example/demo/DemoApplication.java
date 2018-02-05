package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {


	private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner test(TaskRepository taskRepository, UserRepository userRepository){
		return args -> {
			taskRepository.save(new Task("Task1", "Task1", 1));
			taskRepository.save(new Task("Task2", "Task2", 5));
			userRepository.save(new User("pawel", "pawel", "pawel@wp.pl", "ADMIN"));
		};
	}

}