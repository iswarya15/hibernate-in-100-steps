package com.in28minutes.jpa.hibernate.learn_jpa.repository;

import com.in28minutes.jpa.hibernate.learn_jpa.LearnJpaApplication;
import com.in28minutes.jpa.hibernate.learn_jpa.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(classes = LearnJpaApplication.class)
class JPQLTest {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void jpql_basic() {
		List resultList = em.createQuery("Select c from Course c").getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}

	@Test
	void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}

	@Test
	void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%100 steps'", Course.class);
		List<Course> resultList = query.getResultList();

		logger.info("Select c from Course c where name like '%100' -> {}", resultList);
	}
}
