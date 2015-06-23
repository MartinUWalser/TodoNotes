package ToDoNotes.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.util.ResultSetUtil;

import ToDoNotes.Bean.Note;

/**
 * Creates queries for the notes. Inhibits the methods to communicate with the catabase.
 */
public class NoteQuerys {

	/**
	 * The method to get a note using an id.
	 * @param id The id of the note you want to get.
	 * @return The note you want to get.
	 */
	public static Note getNote(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		Note note = new Note();
		String query = "SELECT * FROM Note WHERE id = " + id;
		try {
			pS = conn.prepareStatement(query);
			rS = pS.executeQuery();
			while (rS.next()) {
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

    /**
     * The method to get all notes from a list of notes whose visibility is <code>visible</code>.
     * @param list The list of notes you want to filter using the attribute <code>visible</code>.
     * @param visible <code>true</code> if you want to get all visible notes <code>false</code> to get the invisible
     * ones.
     * @return The list of notes filtered by visibility.
     */
	public static ArrayList<Note> selectVisibleNotes(ArrayList<Note> list, boolean visible) {
		ArrayList<Note> resultList = new ArrayList<Note>();

		if (visible) {
			for (Note e : list) {
				if (e.isVisible()) {
					resultList.add(e);
				}
			}
		} else {
			for (Note e : list) {
				if (!e.isVisible()) {
					resultList.add(e);
				}
			}
		}
		return resultList;
	}

    /**
     * The method to get all notes from a list of notes whose visibility is <code>done</code>.
     * @param list The list of notes you want to filter using the attribute <code>done</code>.
     * @param done <code>true</code> if you want to get all done notes <code>false</code> to get the ones which are
     * not done.
     * @return The list of notes filtered by the state of done.
     */
	public static ArrayList<Note> selectDoneNotes(ArrayList<Note> list, boolean done) {
		ArrayList<Note> resultList = new ArrayList<Note>();

		if (done) {
			for (Note e : list) {
				if (e.isDone()) {
					resultList.add(e);
				}
			}
		} else {
			for (Note e : list) {
				if (!e.isDone()) {
					resultList.add(e);
				}
			}
		}
		return resultList;
	}

    /**
     * The method to insert a note in the database.
     * @param note The note you want to insert.
     */
	public static void insertNote(Note note) {
		Connection conn = MySQLDAO.getConnection();
		Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "INSERT INTO Note (title, description, date, visible, done) VALUES (?, ?, ?, ?, ?);";

		try {
			pS = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pS.setString(1, note.getTitle());
			pS.setString(2, note.getDescription());
			pS.setDate(3, (date));
			pS.setBoolean(4, note.isVisible());
			pS.setBoolean(5, note.isDone());
			pS.execute();
			rS = pS.getGeneratedKeys();

			if (rS.next()) {
				note.setId(rS.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pS.close();
				rS.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
	}

    /**
     * The method to update the attributes of a note in the database.
     * @param note The note you want to update.
     */
	public static void updateNote(Note note) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "UPDATE Note SET title= ?, description= ?, visible= ?, done= ? WHERE id = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, note.getTitle());
			pS.setString(2, note.getDescription());
			pS.setBoolean(3, note.isVisible());
			pS.setBoolean(4, note.isDone());
			pS.setLong(5, note.getId());
			pS.execute();
			pS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * The method to remove a note from the database.
     * @param note The note you want to delete.
     */
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

    /**
     * The method to change the visibility of a note.
     * @param note The note whose visibility you want to change.
     */
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

    /**
     * The method to change the done-attribute of a note.
     * @param note The note whose done-attribute you want to change.
     */
	public static void setDone(Note note) {
		Connection conn = MySQLDAO.getConnection();
		long id = note.getId();
		try {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Note SET done = " + note.isDone()
					+ " WHERE id = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * The method to get all the notes of the database.
     * @return The ArrayList of notes of the database.
     */
	public static ArrayList<Note> getAllNotes() {
		ArrayList<Note> noteList = new ArrayList<Note>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM Note";

		try {
			pS = conn.prepareStatement(query);
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

    /**
     * The method to set the group-attribute of a note.
     * @param note The note whose group-attribute you want to set.
     */
	public static void setGroup(Note note) {
		Connection groupConn = MySQLDAO.getConnection();
		PreparedStatement groupPS = null;
		ResultSet groupRS = null;
		String groupQuery = "SELECT * FROM isin WHERE note_id = ?";
		try {
			groupPS = groupConn.prepareStatement(groupQuery);
			groupPS.setLong(1, note.getId());
			groupRS = groupPS.executeQuery();
			while (groupRS.next())
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