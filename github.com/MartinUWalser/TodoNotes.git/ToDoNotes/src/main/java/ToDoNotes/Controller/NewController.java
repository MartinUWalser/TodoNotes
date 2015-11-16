package ToDoNotes.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ToDoNotes.Database.GroupQueries;
import ToDoNotes.Database.NoteQueries;
import ToDoNotes.POJO.Group;
import ToDoNotes.POJO.Note;

/**
 * The class which is the bean for new.xhtml.
 */
@ManagedBean
@ViewScoped
public class NewController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Note note;
	private ArrayList<Group> groupList;

	/**
	 * The constructor of the class NewController.
	 */
	public NewController(){
	}

	@PostConstruct
	public void init(){
		this.note = new Note();
		Map<String,String> params =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		note.setGroup(params.get("group"));
		this.groupList = GroupQueries.getAllGroups();
	}

    /**
     * The method, which is used to create a new note in the database.
     */
    public void newNote() {
        NoteQueries.insertNote(this.note);
        if(note.getGroup() != null) {
            GroupQueries.setIsInRelation(this.note, note.getGroup());
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * The getter for the list of groups, which are currently existing.
     * @return The ArrayList of groups of NewController.
     */
    public ArrayList<Group> getGroupList() {
		return groupList;
	}

    /**
     * The setter for the groupList of NewController.
     * @param groupsList The list of groups to which you want to set the groupList of NewController to.
     */
	public void setGroupList(ArrayList<Group> groupsList) {
		this.groupList = groupsList;
	}

    /**
     * The getter of the note.
     * @return The current note.
     */
	public Note getNote() {
		return note;
	}

    /**
     * The setter for the note of the class NewController.
     * @param note The note to which you want to set the current note to.
     */
	public void setNote(Note note) {
		this.note = note;
	}
}