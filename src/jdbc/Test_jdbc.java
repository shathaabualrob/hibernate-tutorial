package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test_jdbc {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/hb-03-one-to-many?"
				+ "allowPublicKeyRetrieval=true&useSSL=false"
				+ "&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + jdbcURL);
			
			@SuppressWarnings("unused")
			Connection myconn = DriverManager.getConnection(jdbcURL, user, pass);
			
			System.out.println("Connection Successful!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
