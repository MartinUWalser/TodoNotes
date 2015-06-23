package ToDoNotes.Controller;

import java.io.Serializable;
import java.time.temporal.TemporalUnit;
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
 * The class which is the bean for visible.xhtml
 */
@ManagedBean( name = "VisibleController", eager = true)
@ViewScoped
@Entity
public class VisibleController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Note> notesList;

    @PostConstruct
    public void init(){
        ArrayList<Note> tempList = NoteQuerys.getAllNotes();
        this.notesList = NoteQuerys.selectVisibleNotes(tempList, false);
    }

    /**
     * The getter for the name of the note.
     * @return The name of the note.
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for the name of the note.
     * @param name The name to which you want to change the name of the note to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method to change the visible-state of the note.
     * @param note The note, whose visible-state you want to change.
     */
    public void setVisible(Note note) {
        note.setVisible(!note.isVisible());
        NoteQuerys.setVisible(note);
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
