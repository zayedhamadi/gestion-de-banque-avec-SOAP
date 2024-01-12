package DataBase;



import java.sql.Connection;
import java.sql.DriverManager;
public class Database {

	private static Connection connection;
	static {
	try {
	Class.forName("com.mysql.jdbc.Driver");
	connection= DriverManager.getConnection
	("jdbc:mysql://localhost:5306/banque","root","");
    System.err.println("Connected successfully!");

	} 
	catch (Exception e) 
	{
	e.printStackTrace();
	}
	}
	public static Connection getConnection() 
	{
	return connection;
	}
}