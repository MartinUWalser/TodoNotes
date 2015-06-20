package ToDoNotes.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ToDoNotes.Bean.Group;
import ToDoNotes.Database.GroupQuerys;

@ManagedBean
@ViewScoped
public class GroupController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Group group;
	private ArrayList<Group> groupList;
    private ArrayList<Group> shortList;

	
	public void newGroup() {
		GroupQuerys.insertGroup(group);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("group.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	

	@PostConstruct
	public void init(){
		this.group = new Group();
		this.groupList = GroupQuerys.getAllGroups();
        ArrayList<Group> tempshortList = GroupQuerys.getAllGroups();
        tempshortList.remove(0);
        this.shortList = tempshortList;
	}

	public String deleteGroup(Group group) {
		GroupQuerys.removeGroup(group);
		groupList.remove(group);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}

	public void saveGroup(Group group) {
		GroupQuerys.updateGroup(group);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("group.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

	public ArrayList<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList<Group> groups) {
		this.groupList = groups;
	}

    public ArrayList<Group> getShortList() {
        return shortList;
    }

    public void setShortList(ArrayList<Group> groups) {
        this.shortList = groups;
    }
	
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}