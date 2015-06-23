package ToDoNotes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class builds the connection to the database.
 */
class MySQLDAO {
	private static Connection conn = null;

    /**
     * The object that represents the connection.
     */
	private MySQLDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            String dbHost = "walser.in";
            String dbPort = "3306";
            String database = "ToDoNotes";
            String dbUser = "ToDo";
            String dbPassword = "12ToDo21";
            conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found.");
		} catch (SQLException e) {
			System.out.println("Connection not possible.");
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