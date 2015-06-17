package ToDoNotes.Database;

import java.sql.*;
import java.util.ArrayList;

import ToDoNotes.Bean.Note;

public class NoteQuerys {

	public static void insertNote(String name, String description,
			java.sql.Date sqlDate, boolean visible, boolean done) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO Note (title, description, date, visible, done) VALUES (?, ?, ?, ?, ?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, name);
			pS.setString(2, description);
			pS.setDate(3, (java.sql.Date) sqlDate);
			pS.setBoolean(4, visible);
			pS.setBoolean(5, done);

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setGroupRelation(Note note){
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO isin ( visible, done) VALUES (?, ?, ?, ?, ?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeNote(Note note) {
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
}