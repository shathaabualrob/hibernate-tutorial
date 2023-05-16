package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			// use the session object to save Java object
			
			// create s student object
			System.out.println("creating new object...");
			Student stu = new Student("shatha", "sulieman", "shatha@gmail.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the studnet ...");
			session.save(stu);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
