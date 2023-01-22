package assessments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userrecords", "root", "root");

		String q = "create table UserDetails(userid int(20),name varchar(100),DOB DATE, address varchar(300),phone BIGINT, UserRole varchar(20))";

		Statement st = con.createStatement();
		st.executeUpdate(q);
		System.out.println("table created in database");

	}

}

