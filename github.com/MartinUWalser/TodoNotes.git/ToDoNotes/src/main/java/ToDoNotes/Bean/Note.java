package ToDoNotes.Bean;

import java.util.Date;

public class Note {

	private long id;

	private String title;
	private String description;
	private Date date;
	private boolean visible;
	private boolean done;
	private String groupName;

	public Note() {
	}

	public String getTitle() {
		return this.title;
	}

	public void setId(long id) {
		this.id = id;
		}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
			this.visible = visible;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
