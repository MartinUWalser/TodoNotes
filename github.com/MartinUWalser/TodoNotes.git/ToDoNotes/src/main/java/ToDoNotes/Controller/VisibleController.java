package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;

import ToDoNotes.Bean.Note;
import ToDoNotes.Database.NoteQueries;

/**
 * The class which is the bean for visible.xhtml
 */
@ManagedBean( name = "VisibleController", eager = true)
@ViewScoped
@Entity
public class VisibleController implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Note> notesList;

    @PostConstruct
    public void init(){
        ArrayList<Note> tempList = NoteQueries.getAllNotes();
        this.notesList = NoteQueries.selectVisibleNotes(tempList, false);
    }

    /**
     * The getter for the notesList of the class VisibleController.
     * @return The current notesList of the class.
     */
    public ArrayList<Note> getNotesList() {
        return notesList;
    }

    /**
     * The setter for the notesList of the class.
     * @param notesList The notesList to which you want to set the current notesList of the class to.
     */
    public void setNotesList(ArrayList<Note> notesList) {
        this.notesList = notesList;
    }
}
