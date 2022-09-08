package denisleonov.fit.bstu.by.db_lab_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public String path = "Base_Lab.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private boolean ExistBase(String fname)
    {
        boolean rc = false;
        File f = new File(super.getFilesDir(), fname);
        if (rc = f.exists()){
            //Log.d("Log_02",f.getAbsolutePath());
            Log.d("Log_02","Файл " + fname + " существует");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Файл уже существует!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Log.d("Log_02","Файл " + fname + " не найден");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Файл не найден, создаем новый!", Toast.LENGTH_SHORT);
            toast.show();

            ShowMessage(path);
            CreateFile(path);
        }
        return rc;
    }

    private void ShowMessage(String fname){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Создается файл " + fname).setPositiveButton("Да",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("Log_02","Создание файла " + fname);
                    }
                });
        AlertDialog ad = b.create();
        ad.show();
    }

    private void CreateFile(String fname){
        File f = new File(super.getFilesDir(),fname);
        try{
            f.createNewFile();
            Log.d("Log_02","Файл " + fname + "создан");

        } catch (IOException e) {
            Log.d("Log_02","Файл " + fname + " не создан");
        }
    }

    private void CreateStream(String fname){

        FileOutputStream fos = null;
        try{
            Log.d("Log_02","Файл " + fname + "открыт");

            EditText edt_s = findViewById(R.id.surname_field);
            EditText edt_n = findViewById(R.id.name_field);

            String surname = edt_s.getText().toString();
            String name = edt_n.getText().toString();
            String s = surname+";"+name+";"+"\r\n";

            fos = openFileOutput(path, MODE_APPEND);
            fos.write(s.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.d("Log_02","Файл " + fname + " не открыт");

        }
    }


    public void ConfirmBtn(View view) {
            ExistBase(path);
            CreateStream(path);
    }

    public void DelBtn(View view) {
        File f = new File(super.getFilesDir(),path);
        f.delete();
        TextView textView = findViewById(R.id.info);
        textView.setText("");
        Toast.makeText(this, "Файл удален", Toast.LENGTH_SHORT).show();
    }

    public void ShowBtn(View view) {
        FileInputStream fin = null;
        TextView textView = findViewById(R.id.info);
        try {
            fin = openFileInput(path);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}