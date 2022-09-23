package denisleonov.fit.bstu.by.db_lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import denisleonov.fit.bstu.by.db_lab_8.Entity.Task;

public class CreateTask extends AppCompatActivity {

    private String date;
    private List<String> categories;
    private String category;

    private List<Task> Tasks;

    String fname = "notes.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

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

        Spinner cats = findViewById(R.id.spinner);
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

        Intent thisIntent = getIntent();
        date = thisIntent.getStringExtra("date");

        TextView currentDate = findViewById(R.id.newTaskDate);
        currentDate.setText(date);
    }

    public void saveNewTaskBtn(View view) {
        EditText editTaskText = findViewById(R.id.taskText);
        String taskText = editTaskText.getText().toString();

        Task task = new Task(taskText,category,date);

        List<Task> TaskList;
        XMLHelper xml = new XMLHelper(fname);
        TaskList = xml.readNoteFromInternal(this);

        if(TaskList==null){
            TaskList = new ArrayList<>();
        }

        if(TaskList.size()>19){
            Toast.makeText(this, "Нельзя создать больше 20 задач", Toast.LENGTH_LONG).show();
        }

        int i = 0;
        for(Task var: TaskList){
            if(var.Date.equals(date)){
                i++;
            }
            if(i==5){
                Toast.makeText(this, "Нельзя создать больше 5 задач на одну дату", Toast.LENGTH_LONG).show();
                return;
            }
        }

        TaskList.add(task);

        XMLHelper xmlW = new XMLHelper(fname);
        xmlW.writeNoteToInternal(this, TaskList);

        this.finish();
    }

    public void backFromNewBtn(View view) {
        this.finish();
    }
}