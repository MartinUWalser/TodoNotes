package ToDoNotes.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;

import ToDoNotes.Bean.Note;
import ToDoNotes.Database.NoteQuerys;

@ManagedBean( name = "IndexController", eager = true)
@ViewScoped
@Entity
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Note> notesList;

	@PostConstruct
	public void init(){
		ArrayList<Note> tempList = NoteQuerys.getAllNotes();
		tempList = NoteQuerys.selectVisibleNotes(tempList, true);
		tempList = NoteQuerys.selectDoneNotes(tempList, false);
		this.notesList = tempList;
	}


	public void deleteNote(Note note) {
		NoteQuerys.removeNote(note);
		notesList.remove(note);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
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
