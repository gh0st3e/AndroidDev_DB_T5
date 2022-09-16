package denisleonov.fit.bstu.by.db_lab_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<User> users;
    private List<User> foundUsers;
    private ArrayAdapter<User> adapter;
    private String birth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findBtn(View view) {
        users = JSONHelper.importFromJSON(this);
        foundUsers = new ArrayList<>();
        if(users == null){
            users = new ArrayList<>();
        }

        EditText editBirth = findViewById(R.id.searchStr);
        birth = editBirth.getText().toString();

        try{
            for(User user:users){
                if(user.Birth.equals(birth) && user.IsOpen){
                    foundUsers.add(user);
                }
            }
        }
        catch(Exception e){
            Log.d("lab_06",e.getMessage());
        }


        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foundUsers);
        ListView listView = findViewById(R.id.foundedUsers);
        listView.setAdapter(adapter);
    }
}