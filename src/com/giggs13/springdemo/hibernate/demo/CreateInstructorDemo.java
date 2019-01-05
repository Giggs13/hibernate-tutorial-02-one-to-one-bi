package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Instructor;
import com.giggs13.springdemo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            System.out.println("Saving the Instructor: " + instructor);
            session.save(instructor);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
