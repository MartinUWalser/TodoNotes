package ToDoNotes.Controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ToDoNotes.Bean.Group;
import ToDoNotes.Database.GroupQuerys;

@ManagedBean
@ViewScoped
public class GroupController implements Serializable {
	
	private Group group;
	private ArrayList<Group> groupList;

	
	public String newGroup() {
		GroupQuerys.insertGroup(group);
		return "<success>";
	}
	

	@PostConstruct
	public void init(){
		this.group = new Group();
		this.groupList = GroupQuerys.getAllGroupNames();
	}

	public String deleteGroup(Group group) {
		GroupQuerys.removeGroup(group);
		groupList.remove(group);
		return null;
	}

	public ArrayList<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<Group> groupNames) {
		this.groupList = groupNames;
	}
	
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group groupName) {
		this.group = groupName;
	}

}
