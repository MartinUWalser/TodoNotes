package ToDoNotes.Database;

import java.sql.*;
import java.util.ArrayList;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;

public class GroupQuerys {

	public static void insertGroup(Group name) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO `Group` (name) VALUES (?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, name.getName());

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Group> getAllGroupNames() {
		ArrayList<Group> groupNames = new ArrayList<Group>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;

		String query = "SELECT DISTINCT name FROM `Group`";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				Group group = new Group();
				group.setName(rS.getString("name"));
				groupNames.add(group);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rS.close();
				pS.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return groupNames;
	}

	/*
	public static void removeGroup(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM Note " + "WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void setVisible(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Note SET visible = " + note.isVisible()
					+ " WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static ArrayList<Note> getAllNotes() {
		ArrayList<Note> noteList = new ArrayList<Note>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;

		String query = "SELECT * FROM Note";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				Note note = new Note();
				note.setId(rS.getLong("id"));
				note.setTitle(rS.getString("title"));
				note.setDate(rS.getDate("date"));
				note.setDescription(rS.getString("description"));
				note.setVisible(rS.getBoolean("visible"));
				note.setDone(rS.getBoolean("done"));
				noteList.add(note);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rS.close();
				pS.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return noteList;
	}

	public static ArrayList<String> getAllGroupNames() {
		ArrayList<String> groupNames = new ArrayList<String>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;

		String query = "SELECT DISTINCT name FROM Group";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				groupNames.add(rS.getString("name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rS.close();
				pS.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return groupNames;
	}
	 */
}