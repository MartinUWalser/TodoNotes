package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ToDoNotes.Bean.Group;
import ToDoNotes.Database.GroupQuerys;

@ManagedBean
@RequestScoped
public class GroupController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Group groupName;
	private ArrayList<Group> groupNamesList; 

	public GroupController() {}
	
	public String newGroup() {
		GroupQuerys.insertGroup(groupName);
		return "<success>";
	}
	

	@PostConstruct
	public void initd(){
		this.groupName = new Group();
		this.groupNamesList = GroupQuerys.getAllGroupNames();
	}

	public ArrayList<Group> getGroupNamesList() {
		return groupNamesList;
	}

	public void setGroupNamesList(ArrayList<Group> groupNames) {
		this.groupNamesList = groupNames;
	}
	
	public Group getGroupName() {
		return this.groupName;
	}

	public void setGroupName(Group groupName) {
		this.groupName = groupName;
	}
	
}