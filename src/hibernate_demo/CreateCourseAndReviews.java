package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;
import hibernate_demo_entity.Review;

public class CreateCourseAndReviews {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			//create course
			Course c = new Course("Python");
			
			//add some reviews
			c.addReview(new Review("what a great course"));
			c.addReview(new Review("what as amazing course"));
			c.addReview(new Review("that was useful"));
			
			//save the course.. and leverage the cascade all
			session.save(c);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// clean up code 
			session.close();
			factory.close();
		}

	}

}
