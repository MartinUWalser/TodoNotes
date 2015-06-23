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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * The class which is the bean for edit.xhtml.
 */
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
        this.groupNamesList = GroupQuerys.getAllGroups();
        if (!idString.equals("") || !idString.equals(null)) {
           try {
               id = Integer.parseInt(idString);
               note = NoteQuerys.getNote(id);
               NoteQuerys.setGroup(note);
           } catch (Exception e) {
                System.out.println("Fehler beim parsen.");
            }
        }
    }

    /**
     * The getter for the id of the note you want to edit.
     * @return The id of the current note.
     */
    public long getId() {
        return id;
    }

    /**
     * The getter for the note you want to edit.
     * @return The note you want to edit.
     */
    public Note getNote() {
        return note;
    }

    /**
     * The setter for the note of the EditController.
     * @param note The note you want the current note to be set to.
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * The method which saves the note after you have edited it.
     * @return <code>"<success>"</code> if the save was successful.
     */
    public String saveNote() {
        NoteQuerys.updateNote(note);
        if(note.getGroup() != null)
            GroupQuerys.updateGroupName(this.note, note.getGroup());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return "<success>";
    }

    /**
     * The getter for the list of names of groups which are currently existing.
     * @return The ArrayList of Groups which are currently existing.
     */
    public ArrayList<Group> getGroupNamesList() {
        return groupNamesList;
    }
}
