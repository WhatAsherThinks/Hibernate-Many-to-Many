package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;

public class DeletePacmanCourseDemo {
    public static void main(String [] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start Transaction
            session.beginTransaction();

            //get Pacman course from DB
            int courseID = 10;
            Course tempCourse = session.get(Course.class, courseID);

            //delete course
            System.out.println("Deleting course: " + tempCourse);
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally {
            //close the session and Factory
            session.close();
            factory.close();
        }
    }
}
