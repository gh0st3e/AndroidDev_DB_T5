package denisleonov.fit.bstu.by.db_lab_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class AddStudent extends AppCompatActivity {

    private EditText studentId;
    private EditText studentName;
    private Spinner studentGroup;



    private List<Group> Groups;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentId = findViewById(R.id.studentId);
        studentName = findViewById(R.id.studentName);
        studentGroup = findViewById(R.id.groupSpinner);

        db = new DBHelper(AddStudent.this);

        loadSpinner();
    }

    private void loadSpinner(){
        Groups = db.GetGroups();
        int length = Groups.size();
        String[] GroupsId = new String[length];
        int i =0;
        for(Group group : Groups){
            GroupsId[i] = String.valueOf(group.Id);
            i++;
        }


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GroupsId);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentGroup.setAdapter(adapter);

    }

    public void insertStudentBtn(View view) {
        int id = Integer.parseInt(studentId.getText().toString());
        String name = studentName.getText().toString();
        int groupId = Integer.parseInt(studentGroup.getSelectedItem().toString());

        Student student = new Student(id,name,groupId);

        db.AddStudent(student);
    }
}