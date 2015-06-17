package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;

import ToDoNotes.Bean.Note;
import ToDoNotes.Database.NoteQuerys;

@ManagedBean( name = "IndexController", eager = true)
@RequestScoped
@Entity
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Note> notesList;
	
	public IndexController(){
		this.notesList = NoteQuerys.getAllNotes();
	}
	
	public IndexController(ArrayList<Note> notesList){
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

	public void setDone(Note note) {
		note.setDone(!note.isDone());
		NoteQuerys.setDone(note);
	}

	public ArrayList<Note> getNotesList() {
		return notesList;
	}

	public void setNotesList(ArrayList<Note> notesList) {
		this.notesList = notesList;
	}

}
