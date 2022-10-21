package denisleonov.fit.bstu.by.db_lab_11_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.Constants;
import denisleonov.fit.bstu.by.db_lab_11_15.DBHelper.DBHelper;
import denisleonov.fit.bstu.by.db_lab_11_15.Entities.Progress;
import denisleonov.fit.bstu.by.db_lab_11_15.Provider.StudentProvider;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(MainActivity.this);
        db.getWritableDatabase();
        db.FillDB();
        //db.TestFirstTrigger();
    }

    private void TestSelect(){

        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.provider/groupslist");
        Cursor cursor = getContentResolver().query(test,null,null,null,null);
        String result = "";
        if(cursor.moveToFirst()){
            do {
                result += cursor.getString(0) + cursor.getString(2) + cursor.getString(3);

            } while(cursor.moveToNext());

        }

        Log.d("result",result);
    }

    private void TestInsertGroup(){
        ContentValues cv = new ContentValues();
        cv.put(Constants.ID_GROUP_COL,100);
        cv.put(Constants.ID_FACULTY_COL,1);
        cv.put(Constants.COURSE_COL,4);
        cv.put(Constants.NAME_COL,"TEST_GROUP");
        cv.put(Constants.HEAD_COL,"TEST_HEAD");
        Uri test = Uri.parse("content://com.denisleonov.fit.bstu.by.db_lab_11_15/GroupsList");
        Uri newUri = getContentResolver().insert(test,cv);
        Log.d("Lab11-15", "insert, result Uri : " + newUri.toString());
    }

    private void TestInsertStudent(){
        ContentValues cv = new ContentValues();
        cv.put(Constants.ID_STUDENT_COL,100);
        cv.put(Constants.ID_GROUP_COL,1);
        cv.put(Constants.NAME_COL,"TEST STUDENT");
        cv.put(Constants.BIRTHDAY_COL,"01-01-2000");
        cv.put(Constants.ADDRESS_COL,"TEST_ADRESS");
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.db_lab_11_15.Provider.providers.GroupList/studentlist");
        Uri newUri = getContentResolver().insert(test,cv);
        Log.d("Lab11-15", "insert, result Uri : " + newUri.toString());
    }

    private void TestDelete(){
        Uri test = Uri.parse("content://com.denisleonov.fit.bstu.by.db_lab_11_15/GroupsList");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().delete(uri,null,null);
        Log.d("Lab11-15", "delete, count = " + cnt);
    }

    private void TestDeleteStudent(){
        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.db_lab_11_15.Provider.providers.GroupList/studentlist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().delete(uri,null,null);
        Log.d("Lab11-15", "delete, count = " + cnt);
    }

    private void TestUpdateGroup(){
        ContentValues cv = new ContentValues();
        cv.put(Constants.ID_FACULTY_COL,1);
        cv.put(Constants.COURSE_COL,4);
        cv.put(Constants.NAME_COL,"TEST_GROUP_UPDATED");
        cv.put(Constants.HEAD_COL,"TEST_HEAD_UPDATED");

        Uri test = Uri.parse("content://com.denisleonov.fit.bstu.by.db_lab_11_15/GroupsList");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().update(uri,cv,null);
        Log.d("Lab11-15", "update, count = " + cnt);
    }

    private void TestUpdateStudent(){
        ContentValues cv = new ContentValues();
        cv.put(Constants.ID_STUDENT_COL,100);
        cv.put(Constants.ID_GROUP_COL,1);
        cv.put(Constants.NAME_COL,"TEST STUDENT");
        cv.put(Constants.BIRTHDAY_COL,"08-04-2000");
        cv.put(Constants.ADDRESS_COL,"TEST_ADRESS");

        Uri test = Uri.parse("content://denisleonov.fit.bstu.by.db_lab_11_15.Provider.providers.GroupList/studentlist");
        Uri uri = ContentUris.withAppendedId(test,100);
        int cnt = getContentResolver().update(uri,cv,null);
        Log.d("Lab11-15", "update, count = " + cnt);
    }

    public void showGroupsBtn(View view) {
        TestSelect();
        Intent intent = new Intent(this,GroupsActivity.class);
        startActivity(intent);

    }

    public void showBestBtn(View view) {
        Intent intent = new Intent(this,BestStudentsActivity.class);
        startActivity(intent);
    }

    public void showLowBtn(View view) {
        Intent intent = new Intent(this,LowStudentActivity.class);
        startActivity(intent);
    }

    public void showGroupStatBtn(View view) {
        Intent intent = new Intent(this,GroupStatActivity.class);
        startActivity(intent);
    }

    public void showFacultyStatBtn(View view) {
        Intent intent = new Intent(this,FacultyStatActivity.class);
        startActivity(intent);
    }

    public void showAverageGroupBtn(View view) {
        Intent intent = new Intent(this,GroupsAverageActivity.class);
        startActivity(intent);
    }
}