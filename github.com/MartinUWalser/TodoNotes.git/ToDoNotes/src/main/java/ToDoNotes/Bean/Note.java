package ToDoNotes.Bean;

import ToDoNotes.Database.NoteQuerys;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;

@ManagedBean(name = "Note", eager = true)
@RequestScoped
@Entity
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String description;
	private Date date;
	private boolean visible;
	private boolean done;

	public Note() {
	}

	public String newNote() {
		Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		NoteQuerys.insertNote(this.title, this.description, date, this.visible,
				this.done);
		return "<success>";
	}

	public String getTitle() {
		return this.title;
	}

	public void setId(long id) {this.id = id;}
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
	//	NoteQuerys.setVisible(this);
		this.visible = visible;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
