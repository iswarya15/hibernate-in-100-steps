package com.in28minutes.jpa.hibernate.learn_jpa;

import com.in28minutes.jpa.hibernate.learn_jpa.entity.Course;
import com.in28minutes.jpa.hibernate.learn_jpa.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnJpaApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository repository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(10001L);
		logger.info("Course 10001 - {} ", course);

//		repository.deleteById(10001L);

		repository.save(new Course("Mincroservices in 100 steps"));
	}
}
