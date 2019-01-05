package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Instructor;
import com.giggs13.springdemo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            int id = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("Found instructor detail: " + instructorDetail);
            System.out.println("The associated instructor: " +
                    (Objects.nonNull(instructorDetail) ? instructorDetail.getInstructor() : null));

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
