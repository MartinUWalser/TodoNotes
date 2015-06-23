package ToDoNotes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class builds the connection to the database.
 */
public class MySQLDAO {
	private static Connection conn = null;
	// Hostname
	private static String dbHost = "walser.in";
	// Port
	private static String dbPort = "3306";
	// databasename
	private static String database = "ToDoNotes";
	// databaseuser
	private static String dbUser = "ToDo";
	// databasepassword
	private static String dbPassword = "12ToDo21";

    /**
     * The object that represents the connection.
     */
	private MySQLDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
		}
	}

    /**
     * The method to get the connection from the object.
     * @return The connection.
     */
	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed())
				new MySQLDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}