package denisleonov.fit.bstu.by.db_lab_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView groupListView;
    private ArrayAdapter<Group> adapter;
    private DBHelper db;

    private List<Group> Groups;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupListView = findViewById(R.id.groupsListView);
        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                group = Groups.get(position); // по позиции получаем выбранный элемент
                loadGroupInfo();
            }
        });
        db = new DBHelper(MainActivity.this);

        loadGroupList();
    }

    private void loadGroupList(){
        Groups = db.GetGroups();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Groups);
        groupListView.setAdapter(adapter);
    }

    private void loadGroupInfo(){
        Intent intent = new Intent(this, GroupInfo.class);
        intent.putExtra("Group",group);
        startActivity(intent);
    }

    public void addGroupBtn(View view) {
        Intent intent = new Intent(this, AddGroup.class);
        startActivity(intent);

    }

    public void addStudentBtn(View view) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
}