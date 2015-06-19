package ToDoNotes.Bean;

public class Group {
	private String name;
	private long id;
	
	public Group(){}

	public Group(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) { this.name = name; }

	public long getId () { return this.id;}

	public void setId(long id) { this.id = id; }
}
