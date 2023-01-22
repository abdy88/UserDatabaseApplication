package assessments;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



public class UserData {

	public static void main(String[] args) throws Exception {
		int option = 0;

		do {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userrecords", "root", "root");

			Scanner sc = new Scanner(System.in);

			System.out.println("Select from the following options to interact with the user data :" + '\n');
			System.out.println("1. Insert user data in database");
			System.out.println("2. Display all users data");
			System.out.println("3. Delete specific user by Userid");
			System.out.println("4. Display specific user data as per their Userid");
			System.out.println("5. Exit " + '\n');

			option = sc.nextInt();

			switch (option) {

			case 1:

				String y = " insert into UserDetails(userid,name,DOB,address,phone,UserRole) values(?,?,?,?,?,?)";

				PreparedStatement st1 = con.prepareStatement(y);

				System.out.println("Enter Userid");
				int id1 = sc.nextInt();

				// Check if the user's ID already exists in the database

				PreparedStatement checkStmt = con.prepareStatement("Select count(*) FROM UserDetails where userid = ?");
				checkStmt.setInt(1, id1);
				ResultSet checkRs = checkStmt.executeQuery();
				checkRs.next();
				int count = checkRs.getInt(1);
				checkStmt.close();

				if (count > 0) {
					throw new SQLException("User with ID " + id1 + " already exists in the database");
				} else

				{
					// Insert the new user into the database

					System.out.println("Enter name");
					String name1 = sc.next();

					System.out.println("Enter DOB in YYYY-MM-DD format between 1900 and 2005");
					String dateOfBirth = sc.next();
					Date date1 = Date.valueOf(dateOfBirth);
					LocalDate ld = date1.toLocalDate();
					
					//Check if date is valid
					
					if (ld.isBefore(LocalDate.of(1900, 1, 1)) || ld.isAfter(LocalDate.of(2005, 1, 1))) {

						System.out.println("Invalid DOB.....Enter DOB in the given range");
						break;
					}

					sc.nextLine();
					System.out.println("Enter address ");
					String address1 = sc.nextLine();

					System.out.println("Enter 10 digit phone number");

					long phone1 = sc.nextLong();
					
					//Check if phone number is valid

					if (phone1 < 1000000000L || phone1 > 9999999999L) {
						System.out.println("Phone number is not 10 digits..!! Enter again");
						break;
					}

					System.out.println("Enter user role:" );
					String userrole1 = sc.next();

					st1.setInt(1, id1);
					st1.setString(2, name1);
					st1.setDate(3, date1);
					st1.setString(4, address1);
					st1.setLong(5, phone1);
					st1.setString(6, userrole1);

					st1.executeUpdate();

					System.out.println("user data inserted successfully" + '\n');

				}

				con.close();

				break;

			case 2:

				Statement st4 = con.createStatement();
				ResultSet rs1 = st4.executeQuery("select * from UserDetails");

				ArrayList<User> users = new ArrayList<>();

				while (rs1.next()) {

					int id2 = rs1.getInt("userid");
					String name2 = rs1.getString("name");
					String date2 = rs1.getString("DOB");
					String address2 = rs1.getString("address");
					long phone2 = rs1.getLong("phone");
					String userrole2 = rs1.getString("UserRole");

					User user = new User(id2, name2, date2, address2, phone2, userrole2);
					users.add(user);

				}

				System.out.println("------All user details-------- " + '\n');
				for (User user : users) {

					System.out.println(user.getId() + " " + user.getName() + " " + user.getDate() + " "
							+ user.getAddress() + " " + user.getPhone() + " " + user.getUserRole());
				}

				System.out.println('\n');

				st4.close();
				con.close();
				break;

			case 3:

				String x = "delete from  UserDetails " + "where userid= ? ";

				PreparedStatement st2 = con.prepareStatement(x);

				System.out.println("Enter existing id");
				int existingid = sc.nextInt();

				st2.setInt(1, existingid);
				st2.executeUpdate();

				System.out.println("user record deleted " + '\n');

				con.close();

				break;

			case 4:
				System.out.println("Enter user id to be displayed");
				int existingid1 = sc.nextInt();

				Statement st3 = con.createStatement();
				ResultSet rs = st3.executeQuery("select * from UserDetails where userid= " + existingid1);

				while (rs.next()) {

					int id2 = rs.getInt("userid");
					String name2 = rs.getString("name");
					String date2 = rs.getString("DOB");
					String address2 = rs.getString("address");
					long phone2 = rs.getLong("phone");
					String userrole2 = rs.getString("UserRole");
					
					
					System.out.println("-----User record retrieved------");
					System.out.println(id2 + " " + name2 + " " + date2 + " " + address2 + " " + phone2 + " " + userrole2 + '\n');

				}
				st3.close();
				con.close();

				break;

			case 5:
				System.out.println("Exiting...." + '\n');
				break;

			default:
				System.out.println('\n' + "Choose from the available options..! " + '\n');

			}

		} while (option != 5);

	}

}

