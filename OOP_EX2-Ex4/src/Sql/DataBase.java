package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * this class 
 * @author Roi Abramovitch
 *
 */
public class DataBase {
	public static void main(String[] args)
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		int count = 0;
		double avr = 0.0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			
			Statement statement = connection.createStatement();
			
			//select data
			String allCustomersQuery = "SELECT * FROM logs WHERE FirstID = 311505481";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\t\tPoint\t\tSomeDouble");
			while(resultSet.next())
			{
				count++;
				avr = avr + resultSet.getDouble("Point");
				System.out.println(resultSet.getInt("FirstID")+"\t\t" +
						resultSet.getInt("SecondID")+"\t\t" +
						resultSet.getInt("ThirdID")+"\t\t" +
						resultSet.getTimestamp("LogTime") +"\t\t\t\t" +
						resultSet.getDouble("Point") +"\t\t" +
						resultSet.getDouble("SomeDouble"));
			}
			avr = (avr/count);
			System.out.println(avr);
			resultSet.close();		
			statement.close();		
			connection.close();		
		}
		
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
