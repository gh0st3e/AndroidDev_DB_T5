package denisleonov.fit.bstu.by.db_lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import denisleonov.fit.bstu.by.db_lab_8.Entity.Task;

public class TaskInfo extends AppCompatActivity {
    String fname = "notes.xml";

    private Task task;
    private List<String> categories;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Bundle arguments = getIntent().getExtras();
        task = (Task) arguments.get("task");
        EditText infoTaskName = findViewById(R.id.infoTaskName);
        infoTaskName.setText(task.Value);

        categories = JSONHelper.importFromJSON(this);
        if(categories==null){
            categories = new ArrayList<>();
        }

        int i = 0;
        String[] catsArray = new String[categories.size()];

        for(String cat : categories){
            catsArray[i] = cat;
            i++;
        }
        category = catsArray[0];

        Spinner cats = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, catsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cats.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                category = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        cats.setOnItemSelectedListener(itemSelectedListener);
    }

    public void delTaskInfoBtn(View view) {
        List<Task> TaskList;
        XMLHelper xml = new XMLHelper(fname);
        TaskList = xml.readNoteFromInternal(this);

        if(TaskList==null){
            TaskList = new ArrayList<>();
        }

        for(Task var : TaskList){
            if(var.equals(task)){
                TaskList.remove(task);
            }
        }

        XMLHelper xmlW = new XMLHelper(fname);
        xmlW.writeNoteToInternal(this, TaskList);

        this.finish();
    }

    public void saveTaskInfoBtn(View view) {
        List<Task> TaskList;
        XMLHelper xml = new XMLHelper(fname);
        TaskList = xml.readNoteFromInternal(this);

        if(TaskList==null){
            TaskList = new ArrayList<>();
        }

        for(Task var : TaskList){
            if(var.equals(task)){
                TaskList.remove(var);
            }
        }

        EditText infoTaskName = findViewById(R.id.infoTaskName);
        String newInfoTaskName = infoTaskName.getText().toString();

        TaskList.add(new Task(newInfoTaskName,category,task.Date));

        XMLHelper xmlW = new XMLHelper(fname);
        xmlW.writeNoteToInternal(this, TaskList);

        this.finish();
    }
}