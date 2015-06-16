package ToDoNotes.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;

@ManagedBean( name = "Group", eager = true)
@RequestScoped
@Entity
public class Group  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Note> notesList;
	
	public Group(String name){
		this.name = name;
	}
	
	public Group(String name, ArrayList<Note> notesList){
		this.name = name;
		this.notesList = notesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Note> getNotesList() {
		return notesList;
	}

	public void setNotesList(ArrayList<Note> notesList) {
		this.notesList = notesList;
	}

}
