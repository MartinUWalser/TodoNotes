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
	private static final long serialVersionUID = 1L;
	private Group group;
	private ArrayList<Group> groupList;
    private ArrayList<Group> shortList;


	@PostConstruct
	public void init(){
		this.group = new Group();
		this.groupList = GroupQuerys.getAllGroups();
        ArrayList<Group> tempshortList = GroupQuerys.getAllGroups();
        tempshortList.remove(0);
        this.shortList = tempshortList;
	}

    /**
     * The method, which is used to create a new group in the database.
     */
    public void newGroup() {
        GroupQuerys.insertGroup(group);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("group.xhtml");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * The method, which is used to delete a group from the database.
     * @param group The group which you want to delete.
     */
    public void deleteGroup(Group group) {
		GroupQuerys.removeGroup(group);
		groupList.remove(group);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

    /**
     * Saves the changes to the group in the database.
     * @param group The group which you want to save.
     */
	public void saveGroup(Group group) {
		GroupQuerys.updateGroup(group);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("group.xhtml");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

    /**
     * The getter of the shortList of groups.
     * @return The shortList which does not contain the empty group.
     */
    public ArrayList<Group> getShortList() {
        return shortList;
    }

    /**
     * The setter of the shortList of groups.
     * @param groups The list of groups you want to set as shortList.
     */
    public void setShortList(ArrayList<Group> groups) {
        this.shortList = groups;
    }

    /**
     * The getter of the current group.
     * @return The group of GroupController.
     */
	public Group getGroup() {
		return this.group;
	}

    /**
     * The setter of the current group of the controller.
     * @param group The group you want to change.
     */
	public void setGroup(Group group) {
		this.group = group;
	}
}