package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {
    public static void main(String [] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{

            //start Transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            //add some reviews
            tempCourse.addReview(new Review("Great course ...  Loved it!"));
            tempCourse.addReview(new Review("Cool course, job well done."));
            tempCourse.addReview(new Review("What a dumb course, you are an idiot! "));

            // save the course
            // leverage the cascade call. it will also save all the reviews.
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);


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
