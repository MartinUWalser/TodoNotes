package ToDoNotes.Controller;

import ToDoNotes.Database.GroupQueries;
import ToDoNotes.Database.NoteQueries;
import ToDoNotes.POJO.Group;
import ToDoNotes.POJO.Note;

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
    private Note note;
    private ArrayList<Group> groupNamesList;

    @PostConstruct
    private void init(){
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idString = params.get("id");
        this.groupNamesList = GroupQueries.getAllGroups();
        if (!idString.equals("") || !idString.equals(null)) {
           try {
               long id = Integer.parseInt(idString);
               note = NoteQueries.getNote(id);
               NoteQueries.setGroup(note);
           } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
     */
    public void saveNote() {
        NoteQueries.updateNote(note);
        if(note.getGroup() != null)
            GroupQueries.updateGroupName(this.note, note.getGroup());
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * The getter for the list of names of groups which are currently existing.
     * @return The ArrayList of Groups which are currently existing.
     */
    public ArrayList<Group> getGroupNamesList() {
        return groupNamesList;
    }
}
