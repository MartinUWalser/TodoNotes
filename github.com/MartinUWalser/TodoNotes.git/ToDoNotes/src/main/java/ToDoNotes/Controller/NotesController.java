package ToDoNotes.Controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ToDoNotes.Database.NoteQuerys;

@ManagedBean
@RequestScoped
public class NotesController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String test;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public void testDB(){
		if(NoteQuerys.dbTest()){
			this.test = "Funktioniert";
		}else{
			this.test = "nicht";
		}
	}
	
	@PostConstruct
	private void postInit(){
		this.test = "Hallo";
	}
}
