package denisleonov.fit.bstu.by.db_lab_15_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showGroupsBtn(View view) {
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/groupslist");

        Cursor cursor = getApplicationContext().getContentResolver().query(test,null,null,null,null);
        String result = "";
        if(cursor.moveToFirst()){
            do {
                result += cursor.getString(0) + " " + cursor.getString(2) + " " + cursor.getString(3) + "\n";
            } while(cursor.moveToNext());

        }

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(result);
        b.setTitle("Группы").setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ad = b.create();
        ad.show();

        Log.d("result",result);
    }

    public void showStudentsBtn(View view) {
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/studentlist");

        Cursor cursor = getApplicationContext().getContentResolver().query(test,null,null,null,null);
        String result = "";
        if(cursor.moveToFirst()){
            do {
                result += cursor.getString(0) + " " + cursor.getString(2) + " " + cursor.getString(3) + "\n";
            } while(cursor.moveToNext());

        }

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(result);
        b.setTitle("Студенты").setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ad = b.create();
        ad.show();

        Log.d("result",result);
    }

    public void addGroupBtn(View view) {
        ContentValues cv = new ContentValues();
        cv.put("id_Group",100);
        cv.put("id_Faculty",1);
        cv.put("course",4);
        cv.put("name","TEST_GROUP");
        cv.put("head","TEST_HEAD");
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/groupslist");
        Uri newUri = getContentResolver().insert(test,cv);
        Log.d("Lab11-15", "insert, result Uri : " + newUri.toString());
    }

    public void addStudentBtn(View view) {
        ContentValues cv = new ContentValues();
        cv.put("id_Student",100);
        cv.put("id_Group",100);
        cv.put("name","TEST STUDENT");
        cv.put("birthday","01-01-2000");
        cv.put("address","TEST_ADRESS");
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/studentlist");
        Uri newUri = getContentResolver().insert(test,cv);
        Log.d("Lab11-15", "insert, result Uri : " + newUri.toString());
    }

    public void delStudentBtn(View view) {
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/studentlist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().delete(uri,null,null);
        Log.d("Lab11-15", "delete, count = " + cnt);
    }

    public void delGroupBtn(View view) {
        Uri test2 = Uri.parse("content://denisleonov.fit.bstu.by.provider/studentlist");
        Uri uri2 = ContentUris.withAppendedId(test2,100);
        int cnt2 = getContentResolver().delete(uri2,null,null);
        Log.d("Lab11-15", "delete, count = " + cnt2);

        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/groupslist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().delete(uri,null,null);
        Log.d("Lab11-15", "delete, count = " + cnt);
    }

    public void updateGroupBtn(View view) {
        ContentValues cv = new ContentValues();
        cv.put("id_Faculty",1);
        cv.put("course",4);
        cv.put("name","TEST_GROUP_UPDATED");
        cv.put("head","TEST_HEAD_UPDATED");

        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/groupslist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().update(uri,cv,null);
        Log.d("Lab11-15", "update, count = " + cnt);
    }

    public void updateStudentBtn(View view) {
        ContentValues cv = new ContentValues();
        cv.put("id_Group",1);
        cv.put("name","TEST STUDENT");
        cv.put("birthday","08-04-2000");
        cv.put("address","TEST_ADRESS");

        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/studentlist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().update(uri,cv,null);
        Log.d("Lab11-15", "update, count = " + cnt);
    }
}