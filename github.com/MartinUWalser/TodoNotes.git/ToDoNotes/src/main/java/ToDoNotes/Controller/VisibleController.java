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
