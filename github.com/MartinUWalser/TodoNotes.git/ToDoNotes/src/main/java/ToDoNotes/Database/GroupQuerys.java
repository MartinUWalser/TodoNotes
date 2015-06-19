package ToDoNotes.Database;

import java.sql.*;
import java.util.ArrayList;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;

public class GroupQuerys {

	public static void insertGroup(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO `Group` (`name`) VALUES (?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean hasGroup(Note note) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "SELECT * FROM isin WHERE note_id = ?";
		int i = 0;
		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, note.getId());
			ResultSet rS = pS.executeQuery();

			while(rS.next() && i < 1) {
				++i;
			}


			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(i < 1) {
			return false;
		}
		return true;
	}

	public static void updateIsInRelation(Note note, Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query;
		Boolean deletedRelation = false;

		if (group.getName().equals("") && hasGroup(note)) {
			query = "DELETE FROM isin WHERE note_id = ?";
			deletedRelation = true;
		} else if(hasGroup(note)) {
			query = "UPDATE isin SET group_id = ? WHERE note_id = ? ";
		} else {
		query =	"INSERT INTO isin (group_id, note_id) VALUES (?, ?);";
		}

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			if (!deletedRelation) {
				pS.setLong(1, group.getId());
				pS.setLong(2, note.getId());
			} else {
				pS.setLong(1, note.getId());
			}

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	public static void setIsInRelation(Note note, Group group){
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "INSERT INTO isin (note_id, group_id) VALUES (?, ?);";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setLong(1, note.getId());
			pS.setLong(2, group.getId());

			// Ausführen
			pS.execute();

			pS.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static ArrayList<Group> getAllGroups() {
		ArrayList<Group> groupNames = new ArrayList<Group>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		Group empty = new Group();
		empty.setName("");
		groupNames.add(empty);
		String query = "SELECT * FROM `Group`";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);

			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				Group group = new Group();
				group.setName(rS.getString("name"));
				group.setId(rS.getLong("id"));
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

	public static void removeGroup(Group group) {
		ArrayList<Note> notesList = getAllEntryIds(group);
		deleteRelations(group);
		deleteEntries(notesList);
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "DELETE FROM `Group` WHERE `name` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
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

	private static void deleteEntries(ArrayList<Note> notesList) {
		for (Note note : notesList) {
			NoteQuerys.removeNote(note);
		}
	}

	public static ArrayList<Note> getAllEntryIds(Group group) {
		ArrayList<Note>	noteList = new ArrayList<Note>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `isin` WHERE `group_id` = ?";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setLong(1, group.getId());

			// Ausführen
			rS = pS.executeQuery();
			while (rS.next()) {
				Note note = new Note();
				note.setId(rS.getLong("note_id"));
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

	public static void deleteRelations(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "DELETE FROM `isin` WHERE `group_id` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, group.getId());
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

	public static void deleteRelation(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "DELETE FROM `isin` WHERE `note_id` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, id);
			pS.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void updateGroup(Group group) {
		updateIsInRelation(group);
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "UPDATE `Group` SET `name` = ? WHERE `group_id` = ?";
		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
			pS.setLong(2, group.getId());

			// Ausführen
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateIsInRelation(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		String query = "UPDATE `isin` SET `group_id` = ? WHERE `group_id` = ?";
		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
			pS.setLong(2, group.getId());

			// Ausführen
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Group getGroup(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `Group` WHERE id = ?";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setLong(1, id);
			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				Group group = new Group();
				group.setId(id);
				group.setName(rS.getString("name"));
				return group;
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
		return null;
	}


	public static Group getGroup(String name) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `Group` WHERE `name` = ?";

		try {
			// Query erstellen
			pS = conn.prepareStatement(query);
			pS.setString(1, name);
			// Ausführen
			rS = pS.executeQuery();

			while (rS.next()) {
				Group group = new Group();
				group.setId(rS.getLong("id"));
				group.setName(rS.getString("name"));
				return group;
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
		return null;
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