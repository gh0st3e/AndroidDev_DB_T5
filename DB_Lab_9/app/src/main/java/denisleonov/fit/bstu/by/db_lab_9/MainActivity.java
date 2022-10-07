package denisleonov.fit.bstu.by.db_lab_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText idEdit;
    private EditText floatEdit;
    private EditText stringEdit;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = findViewById(R.id.idVal);
        floatEdit = findViewById(R.id.floatVal);
        stringEdit = findViewById(R.id.stringVal);

        db = new DBHelper(MainActivity.this);
    }

    private void clearFields(){
        idEdit.setText("");
        floatEdit.setText("");
        stringEdit.setText("");
    }

    private void setValues(DBValues values){
        floatEdit.setText(String.valueOf(values.floatValue));
        stringEdit.setText(values.stringValue);
    }

    public void insertBtn(View view) {
        try {
            if (floatEdit.getText().toString().isEmpty() ||
                    stringEdit.getText().toString().isEmpty()){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            db.Insert(idEdit.getText().toString(),floatEdit.getText().toString(),stringEdit.getText().toString());

            clearFields();
        }
        catch (Exception e){

        }
    }

    public void updateBtn(View view) {
        try {
            if (idEdit.getText().toString().isEmpty() ||
                    floatEdit.getText().toString().isEmpty() ||
                    stringEdit.getText().toString().isEmpty()){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            db.Update(idEdit.getText().toString(),floatEdit.getText().toString(),stringEdit.getText().toString());

            clearFields();
        }
        catch (Exception e){

        }
    }

    public void deleteBtn(View view) {
        try {
            if (idEdit.getText().toString().isEmpty()){
                Toast.makeText(this, "Заполните поле id", Toast.LENGTH_SHORT).show();
                return;
            }

            db.Delete(idEdit.getText().toString());

            clearFields();
        }
        catch (Exception e){

        }
    }

    public void selectBtn(View view) {
        try {
            if (idEdit.getText().toString().isEmpty()){
                Toast.makeText(this, "Заполните поле id", Toast.LENGTH_SHORT).show();
                return;
            }


            DBValues values = db.Select(idEdit.getText().toString());
            if(values.floatValue==0.0){
                return;
            }
            setValues(values);
        }
        catch (Exception e){

        }
    }

    public void selectRawBtn(View view) {
        try {
            if (idEdit.getText().toString().isEmpty()){
                Toast.makeText(this, "Заполните поле id", Toast.LENGTH_SHORT).show();
                return;
            }


            DBValues values = db.SelectRaw(idEdit.getText().toString());
            if(values.floatValue==0.0){
                return;
            }
            setValues(values);
        }
        catch (Exception e){

        }
    }
}