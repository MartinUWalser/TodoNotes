package ToDoNotes.Bean;

import ToDoNotes.Database.GroupQuerys;

import java.util.Date;

public class Note {

	private long id;

	private String title;
	private String description;
	private Date date;
	private boolean visible;
	private boolean done;
	private Group group;
	private String groupName;


	public Note() {
		this.visible = true;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		groupName = group.getName();
		this.group = group;
	}
	public void setGroup(String group) {
		this.groupName = group;
		this.group = GroupQuerys.getGroup(group);
	}

	public void setGroupName(String name) {
		groupName = name;
		this.setGroup(name);
	}

	public String getGroupName() {
		return groupName;
	}

}
