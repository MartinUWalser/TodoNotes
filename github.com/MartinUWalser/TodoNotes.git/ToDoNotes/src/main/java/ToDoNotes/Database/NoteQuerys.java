package ToDoNotes.Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import ToDoNotes.Bean.Note;
import com.mysql.jdbc.*;

public class NoteQuerys {

	public static Note getNote(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		Note note = new Note();
		String query = "SELECT * FROM Note WHERE id = " + id;
		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			// Ausführen
			rS = pS.executeQuery();
			while(rS.next()) {
				note.setId(id);
				note.setTitle(rS.getString("title"));
				note.setDate(rS.getDate("date"));
				note.setDescription(rS.getString("description"));
				note.setVisible(rS.getBoolean("visible"));
				note.setDone(rS.getBoolean("done"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rS.close();
				pS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return note;
	}

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
			e.printStackTrace();
		}
	}

	public static void updateNote(Note note) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "UPDATE Note SET title= ?, description= ?, visible= ?, done= ? WHERE id = ?";
		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, note.getTitle());
			pS.setString(2, note.getDescription());
			pS.setBoolean(3, note.isVisible());
			pS.setBoolean(4, note.isDone());
			pS.setLong(5, note.getId());

			// Ausführen
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeNote(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM Note " +
					"WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setVisible(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Note SET visible = "+ note.isVisible()+" WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	public static void setDone(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Note SET done = "+ note.isDone()+" WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			try {
				rS.close();
				pS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return noteList;
	}
}