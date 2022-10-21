package denisleonov.fit.bstu.by.db_lab_11_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.DBHelper;

public class GroupStatActivity extends AppCompatActivity {

    String[] GroupsArr = { "ПОИБМС", "ИСИТ", "ЭКОНОМ", "ХИМИЧЕСКИЙ"};
    int Group;

    String[] SubjectsArr = {"OAIP","BD","OOP","BUSINESS","FIZRA","CHEMISTRY"};
    int Subject;

    private DBHelper db;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_stat);

        Spinner spinnerGa = findViewById(R.id.spinnerGSA);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, GroupsArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGa.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String group = (String)parent.getItemAtPosition(position);
                switch(group){
                    case "ПОИБМС":
                        Group = 1;
                        break;
                    case "ИСИТ":
                        Group = 2;
                        break;
                    case "ЭКОНОМ":
                        Group = 3;
                        break;
                    case "ХИМИЧЕСКИЙ":
                        Group = 4;
                        break;
                    default:
                        Group = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerGa.setOnItemSelectedListener(itemSelectedListenerDay);

        Spinner spinnerSubjectGSA = findViewById(R.id.spinnerSubjectGSA);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, SubjectsArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubjectGSA.setAdapter(adapter2);

        AdapterView.OnItemSelectedListener SubjectSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String subject = (String)parent.getItemAtPosition(position);
                switch(subject){
                    case "OAIP":
                        Subject = 1;
                        break;
                    case "BD":
                        Subject = 2;
                        break;
                    case "OOP":
                        Subject = 3;
                        break;
                    case "BUSINESS":
                        Subject = 4;
                        break;
                    case "FIZRA":
                        Subject = 5;
                        break;
                    case "CHEMISTRY":
                        Subject = 6;
                        break;
                    default:
                        Subject = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerSubjectGSA.setOnItemSelectedListener(SubjectSelectedListenerDay);

        db = new DBHelper(GroupStatActivity.this);
    }

    public void showWithDateBtn(View view) {

        EditText edFromDate = findViewById(R.id.dateFrom);
        EditText edToDate = findViewById(R.id.dateTo);

        String fromDate = edFromDate.getText().toString();
        String toDate = edToDate.getText().toString();
        if(fromDate.length()!=10 || toDate.length()!=10){
            Toast.makeText(this, "Введите корректную дату в формате dd-mm-yyyy", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> progress = db.GetGroupStat(Group,fromDate,toDate,Subject);
        ListView progressLV = findViewById(R.id.groupsListGSA);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, progress);
        progressLV.setAdapter(adapter);
    }
}