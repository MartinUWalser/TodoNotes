package ToDoNotes.Modle;

import java.util.Date;

public class Note {
	private final int id = 0;
	private String title;
	private Date date;
	private String description;
	private boolean visible;
	private boolean done;
	
	public Note(String title, String description, boolean visible, boolean done, Date date){
		this.title = title;
		this.description = description;
		this.visible = visible;
		this.done = done;
		this.date = date;
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

	public int getId() {
		return id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate(){
		return this.date;
	}
	
}
