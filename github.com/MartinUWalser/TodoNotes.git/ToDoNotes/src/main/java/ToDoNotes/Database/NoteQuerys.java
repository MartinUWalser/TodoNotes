package ToDoNotes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoteQuerys {
	
	public static void printNameList() {
		/*conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT first_name, last_name FROM actor "
						+ "ORDER BY last_name";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					String first_name = result.getString("first_name"); // Alternativ:
																		// result.getString(1);
					String last_name = result.getString("last_name"); // Alternativ:
																		// result.getString(2);
					String name = last_name + ", " + first_name;
					System.out.println(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void insertName(String firstName, String lastName) {
		conn = getInstance();

		if (conn != null) {
			try {

				// Insert-Statement erzeugen (Fragezeichen werden später
				// ersetzt).
				String sql = "INSERT INTO actor(first_name, last_name) "
						+ "VALUES(?, ?)";
				PreparedStatement preparedStatement = conn
						.prepareStatement(sql);
				// Erstes Fragezeichen durch "firstName" Parameter ersetzen
				preparedStatement.setString(1, firstName);
				// Zweites Fragezeichen durch "lastName" Parameter ersetzen
				preparedStatement.setString(2, lastName);
				// SQL ausführen.
				preparedStatement.executeUpdate();

				// Es wird der letzte Datensatz abgefragt
				String lastActor = "SELECT actor_id, first_name, last_name "
						+ "FROM actor " + "ORDER BY actor_id DESC LIMIT 1";
				ResultSet result = preparedStatement.executeQuery(lastActor);

				// Wenn ein Datensatz gefunden wurde, wird auf diesen
				// zugegriffen
				if (result.next()) {
					System.out.println("(" + result.getInt(1) + ")"
							+ result.getString(2) + " " + result.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void updateName(String firstName, String lastName, int actorId) {
		conn = getInstance();

		if (conn != null) {
			try {

				String querySql = "SELECT actor_id, first_name, last_name "
						+ "FROM actor " + "WHERE actor_id = ?";

				// PreparedStatement erzeugen.
				PreparedStatement preparedQueryStatement = conn
						.prepareStatement(querySql);
				preparedQueryStatement.setInt(1, actorId);
				ResultSet result = preparedQueryStatement.executeQuery();

				if (result.next()) {
					// Vorher
					System.out.println("VORHER: (" + result.getInt(1) + ")"
							+ result.getString(2) + " " + result.getString(3));
				}

				// Ergebnistabelle erzeugen und abholen.
				String updateSql = "UPDATE actor "
						+ "SET first_name = ?, last_name = ? "
						+ "WHERE actor_id = ?";
				PreparedStatement preparedUpdateStatement = conn
						.prepareStatement(updateSql);
				// Erstes Fragezeichen durch "firstName" Parameter ersetzen
				preparedUpdateStatement.setString(1, firstName);
				// Zweites Fragezeichen durch "lastName" Parameter ersetzen
				preparedUpdateStatement.setString(2, lastName);
				// Drittes Fragezeichen durch "actorId" Parameter ersetzen
				preparedUpdateStatement.setInt(3, actorId);
				// SQL ausführen
				preparedUpdateStatement.executeUpdate();

				// Es wird der letzte Datensatz abgefragt
				result = preparedQueryStatement.executeQuery();

				if (result.next()) {
					System.out.println("NACHHER: (" + result.getInt(1) + ")"
							+ result.getString(2) + " " + result.getString(3));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
}
