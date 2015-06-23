package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;

import ToDoNotes.Database.NoteQueries;
import ToDoNotes.POJO.Note;

/**
 * The class which is the bean for done.xhtml.
 */
@ManagedBean( name = "DoneController", eager = true)
@ViewScoped
@Entity
public class DoneController implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Note> notesList;

    @PostConstruct
    public void init(){
    	ArrayList<Note> tempList = NoteQueries.getAllNotes();
        this.notesList = NoteQueries.selectDoneNotes(tempList, true);
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
