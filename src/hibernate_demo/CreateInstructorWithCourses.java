package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;
import hibernate_demo_entity.Review;

public class CreateInstructorWithCourses {

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
			
			// create the objects
			Instructor inst = new Instructor("google","whatever","shatha@email.com");
			InstructorDetail instD = new InstructorDetail("HELLO", "hate to code!");
			
			Course c1= new Course("google1");
			Course c2= new Course("microsfoft");
			
			// associate the objects together
			inst.setInstructorDetail(instD);
			inst.add(c1);
			inst.add(c2);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// Saving the instructor will also save the detail object
			// becase of cascade.ALL
			session.save(inst);
			session.save(c1);
			session.save(c2);
			System.out.println("save");
			
			System.out.println(inst);
			
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
