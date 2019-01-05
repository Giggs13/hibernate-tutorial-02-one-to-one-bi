package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Instructor;
import com.giggs13.springdemo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            int id = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("Found instructor detail: " + instructorDetail);
            if (Objects.nonNull(instructorDetail)) {
                System.out.println("The associated instructor: " + instructorDetail.getInstructor());
                instructorDetail.getInstructor().setInstructorDetail(null);
            }
            session.delete(instructorDetail);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
