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

@ManagedBean
@ViewScoped
public class ShowGroupController implements Serializable {

	/**
	 * 
	 */
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
	
	public void changeNotesList(){
		this.filteredNotesList.clear();
		for(Note note : this.notesList){
			if(note.getGroupName() != null && note.getGroupName().equals(this.groupName) ){
				this.filteredNotesList.add(note);
			}
		}
	}

	
	public ArrayList<Note> getNotesList() {
		return this.notesList;
	}

	public void setNotesList(ArrayList<Note> notes) {
		this.notesList = notes;
	}

	public ArrayList<Note> getFilteredNotesList() {
		return filteredNotesList;
	}


	public void setFilteredNotesList(ArrayList<Note> filteredNotesList) {
		this.filteredNotesList = filteredNotesList;
	}


	public ArrayList<Group> getGroupList() {
		return groupList;
	}


	public void setGroupList(ArrayList<Group> groupsList) {
		this.groupList = groupsList;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
