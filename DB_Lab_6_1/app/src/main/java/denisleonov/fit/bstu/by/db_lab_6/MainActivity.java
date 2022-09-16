package denisleonov.fit.bstu.by.db_lab_6;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> users;
    private ArrayAdapter<User> adapter;

    private EditText searchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadList();

        searchStr = findViewById(R.id.findStr);
        searchStr.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void searchItem(String textToSearch){
        List<User> foundUsers = new ArrayList<>();
        for(User user:users){
            if(user.Surname.contains(textToSearch)){
                foundUsers.add(user);
            }
            if(user.Name.contains(textToSearch) && !foundUsers.contains(user)){
                foundUsers.add(user);
            }
        }
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foundUsers);
        ListView listView = findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        List<User> users = JSONHelper.importFromJSON(this);
        if(users == null){
            users = new ArrayList<>();
        }
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        ListView listView = findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }





    private void loadList(){
        users = JSONHelper.importFromJSON(this);
        if(users == null){
            users = new ArrayList<>();
        }
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        ListView listView = findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
    }



    public void addNewContactBtn(View view) {
        Intent intent = new Intent(this,NewContact.class);
        startActivity(intent);

    }
}