package demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {
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

            //create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course
            // leverage the cascade call. it will also save all the reviews.
            System.out.println("\nSaving the course...");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            //create the students
            Student tempStudent1 = new Student("John","Doe", "jdoe@coder.com");
            Student tempStudent2 = new Student("Mary","Public", "mpublic@coder.com");

            //add Students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            //save the students
            System.out.println("\nSaving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse.getStudents());

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
