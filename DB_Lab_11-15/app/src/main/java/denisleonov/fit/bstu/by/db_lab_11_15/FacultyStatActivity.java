package denisleonov.fit.bstu.by.db_lab_11_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.DBHelper;

public class FacultyStatActivity extends AppCompatActivity {

    private DBHelper db;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_stat);

        db = new DBHelper(FacultyStatActivity.this);
        loadData();
    }

    private void loadData(){
        List<String> progress = db.GetFacultyStat();
        ListView progressLV = findViewById(R.id.groupsListFSA);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, progress);
        progressLV.setAdapter(adapter);
    }
}