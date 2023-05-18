package hibernate_demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hibernate_demo_entity.Course;
import hibernate_demo_entity.Instructor;
import hibernate_demo_entity.InstructorDetail;

public class FetchJoin {

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
			
			// get the instructor from db using HQL
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId",
					Instructor.class
					);
			
			//set parameter on query
			query.setParameter("theInstructorId", 1);
			
			// execute query and get instructor
			Instructor inst = query.getSingleResult();
			
			
			System.out.println("____Instructor: "+inst);
			System.out.println(inst.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			session.close();
			
			System.out.println("____Courses: "+inst.getCourses());	
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
