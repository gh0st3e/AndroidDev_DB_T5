package denisleonov.fit.bstu.by.db_lab_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button editBtn;
    private Button delBtn;

    private CalendarView cv;
    private EditText tf;
    public String selectedDate;

    private List<Task> Tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadTasks();

        cv = findViewById(R.id.calendarView);
        tf = findViewById(R.id.textField);



        addBtn = findViewById(R.id.add);
        editBtn = findViewById(R.id.edit);
        delBtn = findViewById(R.id.del);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                selectedDate = String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year);

                for(Task task:Tasks){
                    if(task.Date.equals(selectedDate)){
                        addBtn.setEnabled(false);
                        editBtn.setEnabled(true);
                        delBtn.setEnabled(true);
                        tf.setText(task.Text);
                        break;
                    }
                    else{
                        addBtn.setEnabled(true);
                        editBtn.setEnabled(false);
                        delBtn.setEnabled(false);
                        tf.setText("");
                    }
                }
            }
        });


    }



    private void loadTasks(){
        Tasks = JSONHelper.importFromJSON(this);
        if(Tasks == null){
            Tasks = new ArrayList<>();
        }

    }

    private void reloadActivity(){
        cv = findViewById(R.id.calendarView);
        tf = findViewById(R.id.textField);
        tf.setText("");

        cv.setDate(cv.getDate());
    }

    public void addBtn(View view) {
        if(Tasks.size()>=10){
            Toast.makeText(this, "Кол-во записей превышено, удалите что-нибудь", Toast.LENGTH_LONG).show();
            return;
        }
        tf = findViewById(R.id.textField);
        Task task = new Task(selectedDate,tf.getText().toString());
        loadTasks();
        Tasks.add(task);
        JSONHelper.exportToJSON(this,Tasks);
        reloadActivity();
    }

    public void editBtn(View view) {
        tf = findViewById(R.id.textField);
        for(Task task:Tasks){
            if(task.Date.equals(selectedDate)){
                Tasks.remove(task);
                Tasks.add(new Task(selectedDate,tf.getText().toString()));
                JSONHelper.exportToJSON(this,Tasks);
                reloadActivity();
            }
            else{

            }
        }

    }

    public void delBtn(View view) {
        for(Task task:Tasks){
            if(task.Date.equals(selectedDate)){
                Tasks.remove(task);
                JSONHelper.exportToJSON(this,Tasks);
                reloadActivity();
                break;
            }
            else{

            }
        }
    }
}