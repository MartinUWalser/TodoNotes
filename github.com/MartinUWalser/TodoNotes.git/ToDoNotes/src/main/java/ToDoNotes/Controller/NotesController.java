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
	
	private String test;
	
	public String getTest() {
		return test;
	}
	
	public void setTest(String test) {
		this.test = test;
	}
	
	public String dbTest(){
		String back;
		if(NoteQuerys.dbTest()){
			this.test = "Funktioniert";
			back = "success";
		}else{
			this.test = "nicht";
			back = "failure";
		}
		return back;
	}
	
	@PostConstruct
	private void postInit(){
		this.test = "Hallo";
	}
}
