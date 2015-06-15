package ToDoNotes.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ToDoNotes.Bean.Note;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="controller")
@SessionScoped
public class NotesController {
    public List<Note> getAllNotes() {
        List<Note> list = new ArrayList<Note>();
        PreparedStatement prepState = null;
        Connection connect = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root"); //@Martin: eigene Datenbank einbinden
            String sql = "select * from note1";
            prepState = connect.prepareStatement(sql);
            result = prepState.executeQuery();
            while(result.next()) {
                Note note = new Note();
                note.setTitle(result.getString("title"));
                note.setDate(result.getDate("date"));
                note.setDescription(result.getString("description"));
                note.setVisible(result.getBoolean("visible"));
                note.setDone(result.getBoolean("done"));
                list.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
                prepState.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
