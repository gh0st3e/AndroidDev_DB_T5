package denisleonov.fit.bstu.by.db_lab_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddGroup extends AppCompatActivity {

    private EditText idGroup;
    private EditText facultyGroup;
    private EditText courseGroup;
    private EditText nameGroup;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        idGroup = findViewById(R.id.groupId);
        facultyGroup = findViewById(R.id.facultyGroup);
        courseGroup = findViewById(R.id.courseGroup);
        nameGroup = findViewById(R.id.nameGroup);

        db = new DBHelper(AddGroup.this);
    }

    public void insertGroupBtn(View view) {

        int id = Integer.parseInt(idGroup.getText().toString());
        String faculty = facultyGroup.getText().toString();
        int course = Integer.parseInt(courseGroup.getText().toString());
        String name = nameGroup.getText().toString();

        Group group = new Group(id,faculty,course,name,"");
        db.AddGroup(group);

    }
}