package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;

import ToDoNotes.Bean.Note;
import ToDoNotes.Database.NoteQuerys;

/**
 * The class which is the bean for done.xhtml.
 */
@ManagedBean( name = "DoneController", eager = true)
@ViewScoped
@Entity
public class DoneController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Note> notesList;

    @PostConstruct
    public void init(){
    	ArrayList<Note> tempList = NoteQuerys.getAllNotes();
        this.notesList = NoteQuerys.selectDoneNotes(tempList, true);
    }

    /**
     * The getter of the name of the DoneController.
     * @return The name of the DoneController.
     */
    public String getName() {
        return name;
    }

    /**
     * The setter of the name of the DoneController.
     * @param name The string the name of the DoneController is set to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the status of the note <code>note</code> to done.
     * @param note The note which you want to set to done.
     */
    public void setDone(Note note) {
        note.setDone(!note.isDone());
        NoteQuerys.setDone(note);
    }

    /**
     * The getter of the ArrayList of notes <code>notesList</code>.
     * @return The ArrayList of notes, which currently are saved in <code>notesList</code>.
     */
    public ArrayList<Note> getNotesList() {
        return notesList;
    }

    /**
     * The setter for the ArrayList of notes <code>notesList</code>.
     * @param notesList The list of notes, to which you want to set the notesList of the DoneController.
     */
    public void setNotesList(ArrayList<Note> notesList) {
        this.notesList = notesList;
    }
}
