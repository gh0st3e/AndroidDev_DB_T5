package denisleonov.fit.bstu.by.db_lab_11_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.DBHelper;

public class LowStudentActivity extends AppCompatActivity {

    String[] GroupsArr = { "ПОИБМС", "ИСИТ", "ЭКОНОМ", "ХИМИЧЕСКИЙ"};

    private DBHelper db;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_student);

        Spinner spinnerGa = findViewById(R.id.spinnerLSA);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GroupsArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGa.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String group = (String)parent.getItemAtPosition(position);
                switch(group){
                    case "ПОИБМС":
                        loadData(1);
                        break;
                    case "ИСИТ":
                        loadData(2);
                        break;
                    case "ЭКОНОМ":
                        loadData(3);
                        break;
                    case "ХИМИЧЕСКИЙ":
                        loadData(4);
                        break;
                    default:
                        loadData(1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerGa.setOnItemSelectedListener(itemSelectedListenerDay);

        db = new DBHelper(LowStudentActivity.this);
        loadData(1);
    }

    private void loadData(int i){
        List<String> progress = db.GetLow(i);
        ListView progressLV = findViewById(R.id.groupsListLSA);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, progress);
        progressLV.setAdapter(adapter);
    }
}