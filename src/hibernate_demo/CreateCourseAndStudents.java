package hibernate_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;
import hibernate_demo_entity.Review;
import hibernate_demo_entity.Student;

public class CreateCourseAndStudents {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			//create course
			Course c = new Course("English");
			
			//save the course.. and leverage the cascade all
			session.save(c);
			System.out.println("_____Save");
			
			//create students
			Student s1= new Student("eman","tawfeeq","Qabatiyah");
			Student s2= new Student("salha","waleed","Seer");
			
			//add students to course
			c.addStudent(s1);
			c.addStudent(s2);
			
			//save the students
			session.save(s1);
			session.save(s2);
			
			System.out.println("______Course's students: "+ c.getStudents());
			
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



















