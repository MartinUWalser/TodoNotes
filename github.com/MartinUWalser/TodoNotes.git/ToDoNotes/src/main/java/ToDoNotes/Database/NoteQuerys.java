package ToDoNotes.Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ToDoNotes.Bean.Note;

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

	public static void insertNote(Note note) {
		Connection conn = MySQLDAO.getConnection();
		Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "INSERT INTO Note (title, description, date, visible, done) VALUES (?, ?, ?, ?, ?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pS.setString(1, note.getTitle());
			pS.setString(2, note.getDescription());
			pS.setDate(3, (date) );
			pS.setBoolean(4, note.isVisible());
			pS.setBoolean(5, note.isDone());

			// Ausführen
			pS.execute();
			
			rS = pS.getGeneratedKeys();
			if(rS.next()) {
				note.setId(rS.getLong(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pS.close();
				rS.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		long id = note.getId();
		GroupQuerys.deleteRelation(id);
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "DELETE FROM Note WHERE id = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, id);
			pS.execute();
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
				setGroup(note);
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

	public static void setGroup (Note note) {
		Connection groupConn = MySQLDAO.getConnection();
		PreparedStatement groupPS = null;
		ResultSet groupRS = null;
		String groupQuery = "SELECT * FROM isin WHERE note_id = ?";
		try {
			groupPS = groupConn.prepareStatement(groupQuery);
			groupPS.setLong(1, note.getId());
			groupRS = groupPS.executeQuery();
			while(groupRS.next())
				note.setGroup(GroupQuerys.getGroup(groupRS.getLong("group_id")));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				groupPS.close();
				groupRS.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}