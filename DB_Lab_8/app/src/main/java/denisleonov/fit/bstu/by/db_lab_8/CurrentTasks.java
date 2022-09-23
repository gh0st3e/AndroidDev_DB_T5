package denisleonov.fit.bstu.by.db_lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import denisleonov.fit.bstu.by.db_lab_8.Entity.Task;

public class CurrentTasks extends AppCompatActivity {

    private List<String> categories;
    private String date;
    private String category;
    private Task task;
    private List<Task> CurrentTasksList;

    private ArrayAdapter<Task> adapter;
    private ListView tasks;

    String fname = "notes.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_tasks);

        Intent thisIntent = getIntent();
        date = thisIntent.getStringExtra("date");
        TextView textViewDate = findViewById(R.id.date);
        textViewDate.setText(date);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                category = (String)parent.getItemAtPosition(position);
                loadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

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

        Spinner cats = findViewById(R.id.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, catsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cats.setAdapter(adapter);
        cats.setOnItemSelectedListener(itemSelectedListener);
        category = catsArray[0];

        tasks = findViewById(R.id.TasksList);
        tasks.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                task = CurrentTasksList.get(position); // по позиции получаем выбранный элемент
                taskInfo();
            }
        });
        loadData();
    }

    private void taskInfo(){
        try{
            Intent intent1 = new Intent(this,TaskInfo.class);
            intent1.putExtra("task",  task);
            startActivity(intent1);
        }
        catch (Exception e){
            Log.d("lab_04",e.getMessage());
        }
    }

    private void loadData(){
        List<Task> TaskList;
        XMLHelper xml = new XMLHelper(fname);
        TaskList = xml.readNoteFromInternal(this);

        CurrentTasksList = new ArrayList<>();

        if(TaskList==null){
            TaskList = new ArrayList<>();
        }

        for(Task task : TaskList){
            if(task.Date.equals(date) && task.Category.equals(category)){
               CurrentTasksList.add(task);
            }
        }

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CurrentTasksList);
        ListView listView = findViewById(R.id.TasksList);
        listView.setAdapter(adapter);
    }

    public void newTaskBtn(View view) {
        Intent newTask = new Intent(this, CreateTask.class);
        newTask.putExtra("date",date);
        startActivity(newTask);
    }

    public void curTasksBackBtn(View view) {
        this.finish();
    }
}