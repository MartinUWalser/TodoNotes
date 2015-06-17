package ToDoNotes.Controller;

import java.io.Serializable;
import java.lang.reflect.Array;
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
	private ArrayList<Note> notesList;
	private ArrayList<String> groupNames;
	
	public Controller(){
		this.notesList = NoteQuerys.getAllNotes();
	}
	
	public String deleteNote(Note note) {
		NoteQuerys.removeNote(note);
		notesList.remove(note);
		return null;
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

	public ArrayList<String> getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(ArrayList<String> groupNames) {
		this.groupNames = groupNames;
	}
}
