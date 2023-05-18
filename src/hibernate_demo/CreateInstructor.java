package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;

public class CreateInstructor {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			// create the objects
			Instructor inst = new Instructor("shatha","suliman","shatha@email.com");
			InstructorDetail instD = new InstructorDetail("HELLO", "hate to code!");
			
			// associate the objects together
			inst.setInstructorDetail(instD);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// Saving the instructor will also save the detail object
			// becase of cascade.ALL
			session.save(inst);
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
