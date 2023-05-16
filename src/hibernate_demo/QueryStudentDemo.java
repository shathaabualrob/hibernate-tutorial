package hibernate_demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Student;

public class QueryStudentDemo {

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
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			@SuppressWarnings("unchecked")
			List<Student> theStu = session.createQuery("from Student").list();
			@SuppressWarnings("unchecked")
			List<Student> theStu2 = session.createQuery("from Student s where s.lastName='sulieman'").list();
			
			// display the students
			displayStudnets(theStu);
			
			displayStudnets(theStu2);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("done");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

	private static void displayStudnets(List<Student> theStu) {
		for (Student stu : theStu) {
			System.out.println(stu);
		}
		System.out.println("____________________________________");
	}

}
