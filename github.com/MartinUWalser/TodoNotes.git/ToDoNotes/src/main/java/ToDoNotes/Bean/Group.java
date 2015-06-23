package ToDoNotes.Bean;

/**
 * The class group, which is needed to save groups in the database.
 */
public class Group {
	private String name;
	private long id;

    /**
     * The basic constructor of the class group, which initializes it.
     */
	public Group(){}

	/**
     * The getter for the name of the group.
     * @return The name of the group.
     */
	public String getName() {
		return name;
	}

    /**
     * The setter for the name of the group.
     * @param name The string to which the group-name is set to.
     */
	public void setName(String name) { this.name = name; }

    /**
     * The getter for the id of the group.
     * @return The id of the current group.
     */
	public long getId () { return this.id;}

    /**
     * The setter for the id of the group.
     * @param id The long to which the id of the group is set to.
     */
	public void setId(long id) { this.id = id; }
}
