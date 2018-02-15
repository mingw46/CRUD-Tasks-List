package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class DemoApplication {


	private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner test(TaskRepository taskRepository, UserRepository userRepository){
		return args -> {
			User user = new User("pawel", "$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq", "pawel@wp.pl", "ROLE_ADMIN", true);
			userRepository.save(user);
			Task task = new Task("Task1", "Task1");
			task.setUser(user);
			taskRepository.save(task);
			//taskRepository.save(new Task("Task2", "Task2", 5));
			User user2 = new User("pawdsfel", "$2a$10$TA.UfUqLa8uDeGkt95FfLeq7T5Y5vpDpzAtvJrHSLzLliY/PARXUq", "pawel@wp.pl", "ROLE_ADMIN", true);
			userRepository.save(user2);



		};
	}

}
