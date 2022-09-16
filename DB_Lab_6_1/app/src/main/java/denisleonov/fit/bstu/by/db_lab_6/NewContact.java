package denisleonov.fit.bstu.by.db_lab_6;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NewContact extends AppCompatActivity {

    private Boolean access = false;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        RadioButton rb = findViewById(R.id.openFalse);

    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.openTrue:
                if (checked){
                    access = true;
                }
                break;
            case R.id.openFalse:
                if (checked){
                    access = false;
                }
                break;
            default:
                access = false;
        }
    }

    public void AddBtn(View view) {
        EditText editName = findViewById(R.id.name);
        EditText editSurname = findViewById(R.id.surname);
        EditText editPhone = findViewById(R.id.phone);
        EditText editBirth = findViewById(R.id.birth);

        String name = editName.getText().toString();
        if(name.length()<1){
            Toast.makeText(this, "Недостаточная длина имени", Toast.LENGTH_SHORT).show();
            editName.setText("");
            return;
        }
        String surname = editSurname.getText().toString();
        if(surname.length()<3){
            Toast.makeText(this, "Недостаточная длина фамилии", Toast.LENGTH_SHORT).show();
            editSurname.setText("");
            return;
        }
        String phone = editPhone.getText().toString();
        try{
            Integer.parseInt(phone);
            if(phone.length()<3){
                Toast.makeText(this, "Введите правильный номер", Toast.LENGTH_SHORT).show();
                editPhone.setText("");
                return;
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Введите  номер", Toast.LENGTH_SHORT).show();
            editPhone.setText("");
            return;

        }

        String birth = editBirth.getText().toString();
        if(birth.length()<7){
            Toast.makeText(this, "Введите корректную дату", Toast.LENGTH_SHORT).show();
            editBirth.setText("");
            return;
        }

        User user = new User(name,surname,phone,birth, access);
        users = JSONHelper.importFromJSON(this);
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(user);
        JSONHelper.exportToJSON(this,users);

        this.finish();

    }

    public void FinBtn(View view) {
        this.finish();
    }
}