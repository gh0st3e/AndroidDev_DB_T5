package denisleonov.fit.bstu.by.db_lab_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private RandomAccessFile file;
    private int Lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String fname = "HashTable_Lab5.txt";

    private boolean isFileExists(String fname){
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


        }
        return rc;
    }

    private boolean checkFileRows()  {
        try {
            Lines = 0;
            File f = new File(super.getFilesDir(),fname);
            file = new RandomAccessFile(f.getAbsolutePath(),"r");
            while(true){
                if (file.readLine() != null){
                    Lines++;
                }
                else{
                    break;
                }
            }
            Log.d("Log_05",Integer.toString(Lines));
            if (Lines<20){
                return true;
            }
            else{
                Toast.makeText(this, "В файле уже 10 записей... удаляем одну", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }



    private void CreateFile(String fname){ // Создание файла
        File f = new File(super.getFilesDir(),fname);
        try{
            f.createNewFile();
            Log.d("Log_02","Файл " + fname + "создан");
            Toast.makeText(this, "Файл создан", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.d("Log_02","Файл " + fname + " не создан");
            Toast.makeText(this, "Файл не создан", Toast.LENGTH_SHORT).show();
        }
    }

    private void delPair(){
        File f = new File(super.getFilesDir(),fname);

        StringBuilder newStr = new StringBuilder();
        try {
            file = new RandomAccessFile(f.getAbsolutePath(),"rw");
            String str =file.readLine();
            for(int i =0;i<2;i++){
                str = file.readLine();
            }
            while (str!=null){
                newStr.append(str).append("\n");
                str = file.readLine();
            }

            EditText edt_k = findViewById(R.id.keyInput);
            EditText edt_v = findViewById(R.id.valueInput);

            String key = edt_k.getText().toString();
            String value = edt_v.getText().toString();

            int k = key.hashCode();
            String Hashkey = Integer.toString(k);

            String s = Hashkey+"\n"+value+"\n";

            file.seek(0);
            file.writeBytes(newStr.toString());
            file.writeBytes(s);
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ChangeValue(String key, String newValue){ // меняеют значение ключа на новое
        File f = new File(super.getFilesDir(),fname);

        try {
            StringBuilder newStr = new StringBuilder();
            file = new RandomAccessFile(f.getAbsolutePath(),"rw");
            String str =  "";

            while(str != null){
                if(str.equals(key)){
                    newStr.append(str).append("\n");
                    newStr.append(newValue).append("\n");
                    str = file.readLine();
                    continue;
                }
                str=file.readLine();
                if(str!=null && !str.equals(key)){
                    newStr.append(str).append("\n");
                }
            }
            file.seek(0);
            file.writeBytes(newStr.toString());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isRepeat(String key, String Value){ // Проверяет есть ли такой ключ
        File f = new File(super.getFilesDir(),fname);
        try {
            file = new RandomAccessFile(f.getAbsolutePath(),"rw");
            String str =  file.readLine();
            while(true){
                if (str != null){
                    if(str.equals(key)){
                        ChangeValue(key,Value);
                        return true;
                    }
                    else{
                        str = file.readLine();
                    }
                }
                else{
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void WriteInfo(String fname){ // Записывает данные в файл
        FileOutputStream fos = null;
        try{

            EditText edt_k = findViewById(R.id.keyInput);
            EditText edt_v = findViewById(R.id.valueInput);

            String key = edt_k.getText().toString();
            String value = edt_v.getText().toString();

            int k = key.hashCode();
            String Hashkey = Integer.toString(k);

            String s = Hashkey+"\n"+value+"\n";

            if(!isRepeat(Hashkey,value)){
                fos = openFileOutput(fname, MODE_APPEND);
                fos.write(s.getBytes());
            }
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.d("Log_02","Файл " + fname + " не открыт");
        }
    }

    public void saveBtn(View view) {
        if(!isFileExists(fname)){
            CreateFile(fname);
        }

        if (checkFileRows()){
            WriteInfo(fname);
        }
        else{
            delPair();
            
        }
    }

    public void getValueBtn(View view) { // Получить значение по ключу
        EditText edt_k = findViewById(R.id.keyOutput);
        int key = edt_k.getText().toString().hashCode();

        File f = new File(super.getFilesDir(),fname);

        try {
            file = new RandomAccessFile(f.getAbsolutePath(),"r");
            String str =  file.readLine();
            while(true){
                if (str != null){
                    if(str.equals(Integer.toString(key))){
                        EditText edt_v = findViewById(R.id.valueOutput);
                        str = file.readLine();
                        edt_v.setText(str);
                        break;
                    }
                    else{
                        str = file.readLine();
                    }
                }
                else{
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}