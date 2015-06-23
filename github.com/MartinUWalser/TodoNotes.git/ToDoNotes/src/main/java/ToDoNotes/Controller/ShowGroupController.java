package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;






import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;
import ToDoNotes.Database.GroupQuerys;
import ToDoNotes.Database.NoteQuerys;

/**
 * The class which is the bean for showGroup.xhtml
 */
@ManagedBean
@ViewScoped
public class ShowGroupController implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Group> groupList;
	private ArrayList<Note> notesList;
	private ArrayList<Note> filteredNotesList;
	private String groupName;


	@PostConstruct
	public void init(){
		this.notesList = NoteQuerys.getAllNotes();
		this.filteredNotesList = new ArrayList<Note>();
		this.groupList = GroupQuerys.getAllGroups();
	}

	/**
	 * The method, which changes what notes should be shown, depending on the selected group.
	 */
	public void changeNotesList(){
		this.filteredNotesList.clear();
		for(Note note : this.notesList){
			if(note.getGroupName() != null && note.getGroupName().equals(this.groupName) ){
				this.filteredNotesList.add(note);
			}
		}
	}

	/**
	 * The getter of the list of notes, which you want to view.
	 * @return The ArrayList of notes, which are filtered by the selected group.
	 */
	public ArrayList<Note> getFilteredNotesList() {
		return filteredNotesList;
	}

	/**
	 * The setter for the list of notes, which you want to view.
	 * @param filteredNotesList The ArrayList of notes, which you want to set the filteredNotesList to.
	 */
	public void setFilteredNotesList(ArrayList<Note> filteredNotesList) {
		this.filteredNotesList = filteredNotesList;
	}

    /**
     * The getter for the list of groups of the class ShowGroupController.
     * @return The list of groups, which are currently in the class.
     */
	public ArrayList<Group> getGroupList() {
		return groupList;
	}

    /**
     * The setter for the list of groups of the class ShowGroupController.
     * @param groupsList The list of groups, to which you want to set the groupList to.
     */
	public void setGroupList(ArrayList<Group> groupsList) {
		this.groupList = groupsList;
	}

    /**
     * The getter for the name of a group.
     * @return The name of the group.
     */
	public String getGroupName() {
		return groupName;
	}

    /**
     * The setter for the name of a group.
     * @param groupName The string to which you want to set the name of the group to.
     */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
