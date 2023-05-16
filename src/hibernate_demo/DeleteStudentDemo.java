package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {			
//			int studentId = 1;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve a student object based on id=1
//			Student stu = session.get(Student.class, studentId);
		
//			session.delete(stu);
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			
			
			System.out.println("done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
