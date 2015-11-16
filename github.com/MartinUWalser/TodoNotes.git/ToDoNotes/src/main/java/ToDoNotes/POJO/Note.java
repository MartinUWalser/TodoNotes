package ToDoNotes.POJO;

import ToDoNotes.Database.GroupQueries;

import java.util.Date;

/**
 * The class note, which is needed to save notes in the database.
 */
public class Note {
	private long id;
	private String title;
	private String description;
	private Date date;
	private boolean visible;
	private boolean done;
	private Group group;
	private String groupName;

    /**
     * The public constructor of the class note which initializes it and sets the default value of visible to true.
     */
	public Note() {
		this.visible = true;
	}

    /**
     * The getter of the id of the note.
     * @return The id of the note.
     */
    public long getId() {
        return id;
    }
    /**
     * The setter of the id of the note.
     * @param id The long the id of the group is set to.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The getter of the string title.
     * @return The title of the note.
     */
	public String getTitle() {
		return this.title;
	}

    /**
     * The setter of the title of the note.
     * @param title The string the title of the note is set to.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The getter of the description of the note.
     * @return The description of the note.
     */
	public String getDescription() {
		return description;
	}

    /**
     * The setter of the description of the note.
     * @param description The string the description of the note is set to.
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * The getter of the date of the note.
     * @return The date of the note.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * The setter of the date of the note.
     * @param date The value to which the date of the note is set to.
     */
	public void setDate(Date date) {
		this.date = date;
	}

    /**
     * Checks if the note is visible or not.
     * @return <code>true</code> if the note is visible <code>false</code> otherwise.
     */
	public boolean isVisible() {
		return visible;
	}

    /**
     * The setter of the boolean visible of the note.
     * @param visible The boolean to which visible of the note is set to.
     */
	public void setVisible(boolean visible) {
			this.visible = visible;
	}

    /**
     * Checks if the note is done or not.
     * @return <code>true</code> if the note is done <code>false</code> otherwise.
     */
	public boolean isDone() {
		return done;
	}

    /**
     * The setter of the boolean done of the note.
     * @param done The boolean to which done of the note is set to.
     */
	public void setDone(boolean done) {
		this.done = done;
	}

    /**
     * The getter for the group of the note.
     * @return The group to which the note belongs to. If no group is assigned returns <code>null</code>.
     */
	public Group getGroup() {
		return group;
	}

    /**
     * The setter for the group of the note.
     * @param group The group to which the note is set to.
     */
	public void setGroup(Group group) {
		groupName = group.getName();
		this.group = group;
	}

    /**
     * The setter for the string of the name of the group.
     * @param group The string of the name of the group the note belongs to.
     */
	public void setGroup(String group) {
		this.groupName = group;
		this.group = GroupQueries.getGroup(group);
	}

    /**
     * The setter for the name of the group to which the note belongs to.
     * @param name The group-name the note belongs to.
     */
	public void setGroupName(String name) {
		groupName = name;
		this.setGroup(name);
	}

    /**
     * The getter for the name of the group of the note.
     * @return The name of the group of the note.
     */
	public String getGroupName() {
		return groupName;
	}
}
