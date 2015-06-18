package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;
import ToDoNotes.Database.GroupQuerys;
import ToDoNotes.Database.NoteQuerys;

@ManagedBean
@RequestScoped
public class NewController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String groupName;
	private Note note;
	private ArrayList<Group> groupNamesList; 

	public NewController(){
	}
	
	public String newNote() {
		NoteQuerys.insertNote(this.note);
		GroupQuerys.setIsInRelation(this.note, this.groupName);
		return "<success>";
	}
	

	@PostConstruct
	public void initd(){
		Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.groupName = params.get("group");
		this.note = new Note();
		this.groupNamesList = GroupQuerys.getAllGroupNames();
	}

	public ArrayList<Group> getGroupNamesList() {
		return groupNamesList;
	}

	public void setGroupNamesList(ArrayList<Group> groupNamesList) {
		this.groupNamesList = groupNamesList;
	}
	
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
