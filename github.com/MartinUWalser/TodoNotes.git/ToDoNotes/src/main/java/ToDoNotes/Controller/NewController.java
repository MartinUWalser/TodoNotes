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
	private Note note;
	String groupName;
	private ArrayList<Group> groupNamesList; 

	public NewController(){
	}
	
	public String newNote() {
		Date utilDate = new Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		NoteQuerys.insertNote(this.note.getTitle(), this.note.getDescription(), date, this.note.isVisible(),
				this.note.isDone());
		GroupQuerys.setIsInRelation(this.note, this.groupName);
		return "<success>";
	}
	

	@PostConstruct
	public void initd(){
		this.groupName = "";
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
