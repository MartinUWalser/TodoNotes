package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;

import ToDoNotes.Bean.Note;
import ToDoNotes.Database.NoteQuerys;

@ManagedBean( name = "controller", eager = true)
@RequestScoped
@Entity
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Note> notesList;
	
	public Controller(){
		this.notesList = NoteQuerys.getAllNotes();
	}
	
	public Controller(String name, ArrayList<Note> notesList){
		this.name = name;
		this.notesList = notesList;
	}

	public String deleteNote(Note note) {
		NoteQuerys.removeNote(note);
		notesList.remove(note);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVisible(Note note) {
		note.setVisible(!note.isVisible());
		NoteQuerys.setVisible(note);
	}

	public ArrayList<Note> getNotesList() {
		return notesList;
	}

	public void setNotesList(ArrayList<Note> notesList) {
		this.notesList = notesList;
	}

}
