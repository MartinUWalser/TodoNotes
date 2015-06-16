package ToDoNotes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

import ToDoNotes.Bean.Note;

public class NoteQuerys {

	public static void insertNote(int id, String name, String description,
			java.sql.Date sqlDate, boolean visible, boolean done) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO Note (id, title, description, date, visible, done) VALUES (?, ?, ?, ?, ?, ?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setInt(1, id);
			pS.setString(2, name);
			pS.setString(3, description);
			pS.setDate(4, (java.sql.Date) sqlDate);
			pS.setBoolean(5, visible);
			pS.setBoolean(6, done);

			// Ausführen
			pS.execute();

			pS.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Note> getAllNote() {
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
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return noteList;
	}
}