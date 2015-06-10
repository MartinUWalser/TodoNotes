package ToDoNotes.Bean;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Note> notesList;
	
	public Group(String name){
		this.name = name;
	}
	
	public Group(String name, ArrayList<Note> notesList){
		this.name = name;
		this.notesList = notesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Note> getNotesList() {
		return notesList;
	}

	public void setNotesList(ArrayList<Note> notesList) {
		this.notesList = notesList;
	}

}
