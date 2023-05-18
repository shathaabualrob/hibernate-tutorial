package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;

public class DeleteCourse {

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
			
			
			// start a transaction
			session.beginTransaction();
			
			Course course = session.get(Course.class, 10);
			
			System.out.println("______deleting course");
			
			session.delete(course);
			
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
