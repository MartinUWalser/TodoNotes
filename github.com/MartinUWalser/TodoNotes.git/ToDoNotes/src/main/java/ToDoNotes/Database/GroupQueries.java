package ToDoNotes.Database;

import java.sql.*;
import java.util.ArrayList;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;

/**
 * Creates queries for groups of notes. Inhibits the methods to communicate with the mysql database.
 */
public class GroupQueries {

	/**
	 * The method to insert a group in the database.
	 * @param group The group you want to insert.
	 */
	public static void insertGroup(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "INSERT INTO `Group` (`name`) VALUES (?);";

		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
			pS.execute();
			pS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method to check if a note already has a group.
	 * @param note The note whose group-status you want to check.
	 * @return <code>true</code> if the note has a group <code>false</code> otherwise.
	 */
	private static boolean hasGroup(Note note) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "SELECT * FROM isin WHERE note_id = ?";
		int i = 0;

		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, note.getId());
			ResultSet rS = pS.executeQuery();

			while(rS.next() && i < 1) {
				++i;
			}
			pS.execute();
			pS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return i >= 1;
    }

    /**
     * The method to update the relation between a note and a group.
     * @param note The note you want to connect to a group.
     * @param group The group you want the note to be connected with.
     */
	public static void updateGroupName(Note note, Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query;
		Boolean deletedRelation = false;

		if (group.getName() != null && group.getName().equals("") && hasGroup(note)) {
			query = "DELETE FROM isin WHERE note_id = ?";
			deletedRelation = true;
		} else if(hasGroup(note)) {
			query = "UPDATE isin SET group_id = ? WHERE note_id = ? ";
		} else {
		query =	"INSERT INTO isin (group_id, note_id) VALUES (?, ?);";
		}
		try {
		    pS = conn.prepareStatement(query);

			if (!deletedRelation) {
				pS.setLong(1, group.getId());
				pS.setLong(2, note.getId());
			} else {
				pS.setLong(1, note.getId());
			}
            pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
			}
	}

    /**
     * The method used to create a new relation between a note and a group.
     * @param note The note you want to connect with a group.
     * @param group The group you want the note to be connected with.
     */
	public static void setIsInRelation(Note note, Group group){
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "INSERT INTO isin (note_id, group_id) VALUES (?, ?);";

		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, note.getId());
			pS.setLong(2, group.getId());
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * The method to get all existing groups of the database including an empty group.
     * @return The ArrayList of all groups.
     */
	public static ArrayList<Group> getAllGroups() {
		ArrayList<Group> groupNames = new ArrayList<>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		Group empty = new Group();
		empty.setName("");
		groupNames.add(empty);
		String query = "SELECT * FROM `Group`";

		try {
			pS = conn.prepareStatement(query);
			rS = pS.executeQuery();

			while (rS.next()) {
				Group group = new Group();
				group.setName(rS.getString("name"));
				group.setId(rS.getLong("id"));
				groupNames.add(group);
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
		return groupNames;
	}

    /**
     * The method to remove a group from the database.
     * @param group The group you want to be removed.
     */
	public static void removeGroup(Group group) {
		ArrayList<Note> notesList = getAllEntryIds(group);
		deleteRelations(group);
		deleteEntries(notesList);
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "DELETE FROM `Group` WHERE `name` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
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
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

    /**
     * The method to delete all entries from a list of notes.
     * @param notesList The list of notes you want to be removed.
     */
	private static void deleteEntries(ArrayList<Note> notesList) {
        notesList.forEach(NoteQueries::removeNote);
	}

    /**
     * The method to get all ids of all notes from a specific group.
     * @param group The group whose note-ids you want to get.
     * @return An ArrayList including all the notes of the group.
     */
	private static ArrayList<Note> getAllEntryIds(Group group) {
		ArrayList<Note>	noteList = new ArrayList<>();
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `isin` WHERE `group_id` = ?";

		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, group.getId());
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

    /**
     * The method to delete all relations from the table isin with the specified group.
     * @param group The group whose relations you want to delete.
     */
	private static void deleteRelations(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "DELETE FROM `isin` WHERE `group_id` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, group.getId());
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
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

    /**
     * The method to delete the relation using the id of a note.
     * @param id The id of a note whose relation you want to delete.
     */
	public static void deleteRelation(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
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

    /**
     * The method to update the name of a group in the database.
     * @param group The group whose name you want to be updated.
     */
	public static void updateGroup(Group group) {
        updateGroupName(group);
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "UPDATE `Group` SET `name` = ? WHERE `id` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
			pS.setLong(2, group.getId());
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * The method to update the name of a group in the database.
     * @param group The group you are using to change its name in.
     */
	private static void updateGroupName(Group group) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS;
		String query = "UPDATE `Group` SET `name` = ? WHERE `id` = ?";
		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, group.getName());
			pS.setLong(2, group.getId());
			pS.execute();
			pS.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * The method to get the group by using its id.
     * @param id The id of the group you want to get.
     * @return The group you need.
     */
	public static Group getGroup(long id) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `Group` WHERE id = ?";

		try {
			pS = conn.prepareStatement(query);
			pS.setLong(1, id);
			rS = pS.executeQuery();

			if(rS.next()){
				Group group = new Group();
				group.setId(id);
				group.setName(rS.getString("name"));
				return group;
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
		return null;
	}

    /**
     * The method to get the group using its name.
     * @param name The name of the group you want to get.
     * @return The group you need.
     */
	public static Group getGroup(String name) {
		Connection conn = MySQLDAO.getConnection();
		PreparedStatement pS = null;
		ResultSet rS = null;
		String query = "SELECT * FROM `Group` WHERE `name` = ?";

		try {
			pS = conn.prepareStatement(query);
			pS.setString(1, name);
			rS = pS.executeQuery();

			if(rS.next()) {
				Group group = new Group();
				group.setId(rS.getLong("id"));
				group.setName(rS.getString("name"));
				return group;
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
		return null;
	}
}