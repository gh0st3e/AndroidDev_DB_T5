package denisleonov.fit.bstu.by.db_lab_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class GroupInfo extends AppCompatActivity {

    private EditText idGroup;
    private EditText facultyGroup;
    private EditText courseGroup;
    private EditText nameGroup;
    private EditText headGroup;

    private Student student;
    private Group group;
    private List<Student> students;

    private ListView studentsListView;
    private ArrayAdapter<Student> adapter;

    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        idGroup = findViewById(R.id.infoGroupId);
        facultyGroup = findViewById(R.id.infoGroupFaculty);
        courseGroup = findViewById(R.id.infoGroupCourse);
        nameGroup = findViewById(R.id.infoGroupName);
        headGroup = findViewById(R.id.infoGroupHead);

        studentsListView = findViewById(R.id.studentsListView);

        Bundle arguments = getIntent().getExtras();
        group = (Group) arguments.get("Group");

        db = new DBHelper(GroupInfo.this);

        loadInfo();
    }

    private void loadInfo(){
        idGroup.setText(String.valueOf(group.Id));
        facultyGroup.setText(group.Faculty);
        courseGroup.setText(String.valueOf(group.Course));
        nameGroup.setText(group.Name);
        headGroup.setText(group.Head);

        students = db.GetStudents(group.Id);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        studentsListView.setAdapter(adapter);
    }

    public void updateGroupBtn(View view) {
        int id = Integer.parseInt(idGroup.getText().toString());
        String faculty = facultyGroup.getText().toString();
        int course = Integer.parseInt(courseGroup.getText().toString());
        String name = nameGroup.getText().toString();
        String head = headGroup.getText().toString();

        Group group = new Group(id,faculty,course,name,head);

        db.UpdateGroup(group);
        this.finish();
    }

    public void delGroupBtn(View view) {
        db.DeleteGroup(group.Id);
        this.finish();
    }
}