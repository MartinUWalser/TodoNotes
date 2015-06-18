package ToDoNotes.Controller;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;
import ToDoNotes.Database.GroupQuerys;
import ToDoNotes.Database.NoteQuerys;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@ManagedBean( name = "EditController", eager = true)
@ViewScoped
@Entity
public class EditController implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private Note note;
    private ArrayList<Group> groupNamesList;

    @PostConstruct
    private void init(){
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idString = params.get("id");
        this.groupNamesList = GroupQuerys.getAllGroupNames();
        if (!idString.equals("") || !idString.equals(null)) {
           try {
               id = Integer.parseInt(idString);
               note = NoteQuerys.getNote(id);
               NoteQuerys.setGroup(note);
           } catch (Exception e) {
                System.out.println("Fehler beim parse.");
            }
        }
    }

    public long getId() {
        return id;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String saveNote() {
        NoteQuerys.updateNote(note);
        GroupQuerys.updateIsInRelation(this.note, note.getGroupName());
        return "<success>";
    }

    public ArrayList<Group> getGroupNamesList() {
        return groupNamesList;
    }
}
