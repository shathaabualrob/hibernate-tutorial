package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;

public class DeleteInstructor {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
		
			// start a transaction
			session.beginTransaction();
			
			Instructor inst = session.get(Instructor.class, 1);
			
			System.out.println(inst);
			
			if(inst != null) {
				// this will also delet the detail object
				session.delete(inst);
				System.out.println("deleted");
			}
			// commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			factory.close();
		}

	}

}
