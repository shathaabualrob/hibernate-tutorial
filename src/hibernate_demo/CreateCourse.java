package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;

public class CreateCourse {

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
			
			// get the instructor from db
			Instructor inst = session.get(Instructor.class, 1);
			
			// create some courses
			Course c1 = new Course("C++");
			Course c2 = new Course("Mathmatics");
			
			
			// add courses to instructor
			inst.add(c1);
			inst.add(c2);
			
			// save the courses
			session.save(c1);
			session.save(c2);
			
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
