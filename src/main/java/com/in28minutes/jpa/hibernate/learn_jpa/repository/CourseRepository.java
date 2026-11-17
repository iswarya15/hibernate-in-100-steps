package com.in28minutes.jpa.hibernate.learn_jpa.repository;

import com.in28minutes.jpa.hibernate.learn_jpa.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Course findById(Long id){
       return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void save(Course course) {
        if (course.getId() == null) {
//            insert
            em.persist(course);
        } else {
//            update
            em.merge(course);
        }
    }

    public void playWithEntityManager() {
        logger.info("playWithEntityManager - start");
        Course course = new Course("Web services in 100 steps");
        em.persist(course);
        em.flush();

        course.setName("Web Services - Updated");
        em.flush();

        Course course1 = new Course("Learn Angular");
        em.persist(course1);
        em.flush();

        em.detach(course1); // Hibernate stops tracking course1 ONLY

        course1.setName("Learn Angular - Updated");  // IGNORED by Hibernate (no UPDATE SQL!)
        em.flush();

        em.clear(); // Hibernate stops tracking BOTH course1 and course2

        course.setName("Web Services - Again Updated"); // IGNORED (no UPDATE SQL!)
        course1.setName("Learn Angular - Again Updated"); // IGNORED (no UPDATE SQL!)

        em.flush(); // Nothing happens; no SQL is sent


    }

}
