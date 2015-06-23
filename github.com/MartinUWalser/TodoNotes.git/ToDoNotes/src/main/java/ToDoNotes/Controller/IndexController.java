package ToDoNotes.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;

import ToDoNotes.Database.NoteQueries;
import ToDoNotes.POJO.Note;

/**
 * The class which is the bean for index.xhtml.
 */
@ManagedBean( name = "IndexController", eager = true)
@ViewScoped
@Entity
public class IndexController implements Serializable {
	private static final long serialVersionUID = 1L;
    private ArrayList<Note> notesList;

	@PostConstruct
	public void init(){
		ArrayList<Note> tempList = NoteQueries.getAllNotes();
		tempList = NoteQueries.selectVisibleNotes(tempList, true);
		tempList = NoteQueries.selectDoneNotes(tempList, false);
		this.notesList = tempList;
	}

	/**
	 * The method to delete a note from the database.
	 * @param note The note which you want to delete.
	 */
	public void deleteNote(Note note) {
		NoteQueries.removeNote(note);
		notesList.remove(note);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

    /**
     * The setter for the visible-state of the current note.
     * @param note The note, whose visible-status you want to change.
     */
	public void setVisible(Note note) {
		note.setVisible(!note.isVisible());
		NoteQueries.setVisible(note);
	}

    /**
     * The setter for the done-state of the note.
     * @param note The note, whose done-status you want to change.
     */
	public void setDone(Note note) {
		note.setDone(!note.isDone());
		NoteQueries.setDone(note);
	}

    /**
     * The getter for the whole list of notes.
     * @return The ArrayList of notes.
     */
	public ArrayList<Note> getNotesList() {
		return notesList;
	}

    /**
     * The setter for the list of notes.
     * @param notesList The ArrayList of notes you want the current notesList to be changed to.
     */
	public void setNotesList(ArrayList<Note> notesList) {
		this.notesList = notesList;
	}
}