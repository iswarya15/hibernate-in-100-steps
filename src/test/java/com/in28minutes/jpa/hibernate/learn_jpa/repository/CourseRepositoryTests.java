package com.in28minutes.jpa.hibernate.learn_jpa.repository;

import com.in28minutes.jpa.hibernate.learn_jpa.LearnJpaApplication;
import com.in28minutes.jpa.hibernate.learn_jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(classes = LearnJpaApplication.class)
class CourseRepositoryTests {

	@Autowired
	CourseRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void contextLoads() {
		logger.info("Testing is Running");
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteById() {
		repository.deleteById(10001L);
		assertNull(repository.findById(10001L));
	}

	@Test
	@DirtiesContext
	void save_basics() {
//		get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

//		update details
		course.setName("JPA - UPDATED");
		repository.save(course);

//		check value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA - UPDATED", course.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}
