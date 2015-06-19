package ToDoNotes.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ToDoNotes.Bean.Group;
import ToDoNotes.Bean.Note;
import ToDoNotes.Database.GroupQuerys;
import ToDoNotes.Database.NoteQuerys;

@ManagedBean
@ViewScoped
public class NewController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Note note;
	private ArrayList<Group> groupList;

	public NewController(){
	}
	
	public String newNote() {
		NoteQuerys.insertNote(this.note);
		if(note.getGroup() != null) {
			GroupQuerys.setIsInRelation(this.note, note.getGroup());
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return "<success>";
	}
	

	@PostConstruct
	public void init(){
		this.note = new Note();
		Map<String,String> params =
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		note.setGroup(params.get("group"));
		this.groupList = GroupQuerys.getAllGroups();
	}

	public ArrayList<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<Group> groupNamesList) {
		this.groupList = groupNamesList;
	}
	
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}
