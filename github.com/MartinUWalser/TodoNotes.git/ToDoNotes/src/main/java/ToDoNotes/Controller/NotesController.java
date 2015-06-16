package ToDoNotes.Controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ToDoNotes.Database.NoteQuerys;

@ManagedBean(eager = true)
@ViewScoped
public class NotesController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String testDB(){
		return "Hallo";
	}
}

