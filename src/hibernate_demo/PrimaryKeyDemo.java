package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Student;

public class PrimaryKeyDemo {

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
			System.out.println("creating 3 new objects...");
			Student stu1 = new Student("shorooq", "sulieman", "shorooq@gmail.com");
			Student stu2 = new Student("abood", "gaith", "abood@gmail.com");
			Student stu3 = new Student("faris", "mohammed", "faris@gmail.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the studnets ...");
			session.save(stu1);
			session.save(stu2);
			session.save(stu3);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
