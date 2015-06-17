package ToDoNotes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLDAO {

	private static Connection conn = null;

	// Hostname
	private static String dbHost = "walser.in";

	// Port -- Standard: 3306
	private static String dbPort = "3306";

	// Datenbankname
	private static String database = "ToDoNotes";

	// Datenbankuser
	private static String dbUser = "ToDo";

	// Datenbankpasswort
	private static String dbPassword = "12ToDo21";

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
