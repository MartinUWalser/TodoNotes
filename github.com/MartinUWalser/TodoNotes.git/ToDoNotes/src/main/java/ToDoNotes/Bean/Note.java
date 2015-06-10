package ToDoNotes.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;


@ManagedBean( name = "Note", eager = true)
@RequestScoped
@Entity
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id = 0;

	private String title;
	private String description;
	private Date date;
	private boolean visible;
	private boolean done;
	
	public Note(){}
	
	public Note(String title, String description, boolean visible, boolean done, Date date){
		this.title = title;
		this.description = description;
		this.visible = visible;
		this.done = done;
		this.date = date;
	}
	@PostConstruct
	private void postInit(){
		this.title = "TestNote";
		this.description = "Eduards liebligsfarbe ist Rosa :D, somit kann er alles was Rosa ist gebraucht. Macht mal Vorschläge!";
		this.visible = true;
		this.done = false;
	}
	
	public String getTitle(){
		return this.title;
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

	public long getId() {
		return id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate(){
		return this.date;
	}
	
}
