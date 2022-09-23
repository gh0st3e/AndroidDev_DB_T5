package denisleonov.fit.bstu.by.db_lab_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    private CalendarView cv;

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                selectedDate = String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year);
            }
        });
    }

    public void openBtn(View view) {
        Intent CurrentTasks = new Intent(this, denisleonov.fit.bstu.by.db_lab_8.CurrentTasks.class);
        CurrentTasks.putExtra("date",selectedDate);
        startActivity(CurrentTasks);
    }

    public void catMenuBtn(View view) {
        Intent CurrentTasks = new Intent(this, denisleonov.fit.bstu.by.db_lab_8.CategoriesMenu.class);
        startActivity(CurrentTasks);
    }
}