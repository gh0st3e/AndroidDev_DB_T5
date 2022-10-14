package denisleonov.fit.bstu.by.db_lab_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Lab10.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME_GROUPS = "Groups";
    private static final String TABLE_NAME_STUDENTS = "Students";
    //Group
    private static final String ID_GROUP_COL = "ID_Group";
    private static final String FACULTY_COL = "Faculty";
    private static final String COURSE_COL = "Course";
    private static final String NAME_COL = "Name";
    private static final String HEAD_COL = "Head";
    //Student
    private static final String ID_STUDENT_COL = "ID_Student";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_groups = "CREATE TABLE " + TABLE_NAME_GROUPS + " ("
                + ID_GROUP_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FACULTY_COL + " TEXT,"
                + COURSE_COL + " INTEGER,"
                + NAME_COL + " TEXT UNIQUE,"
                + HEAD_COL + " TEXT)";

        String query_students = "CREATE TABLE " + TABLE_NAME_STUDENTS + " ("
                + ID_STUDENT_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_COL + " TEXT,"
                + ID_GROUP_COL + " INTEGER , "
                + " FOREIGN KEY(ID_Group) REFERENCES Groups(ID_Group) ON DELETE CASCADE)";

        db.execSQL(query_groups);
        db.execSQL(query_students);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STUDENTS);
        onCreate(db);
    }

    public void AddGroup(Group group){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_GROUP_COL,group.Id);
        values.put(FACULTY_COL,group.Faculty);
        values.put(COURSE_COL,group.Course);
        values.put(NAME_COL,group.Name);

        db.insert(TABLE_NAME_GROUPS,null,values);
        db.close();
    }

    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_STUDENT_COL,student.Id);
        values.put(NAME_COL,student.Name);
        values.put(ID_GROUP_COL,student.GroupId);

        db.insert(TABLE_NAME_STUDENTS,null,values);
        db.close();
    }

    public List<Group> GetGroups(){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor groupCursor = db.rawQuery(String.format("SELECT * FROM %s",TABLE_NAME_GROUPS),null);
            List<Group> Groups = new ArrayList<>();

            if(groupCursor.moveToFirst()){
                do{
                    Groups.add(new Group(
                            groupCursor.getInt(0),
                            groupCursor.getString(1),
                            groupCursor.getInt(2),
                            groupCursor.getString(3),
                            groupCursor.getString(4)
                    ));
                } while (groupCursor.moveToNext());
            }

            groupCursor.close();
            db.close();
            return Groups;
        }
        catch (Exception e){
            Log.d("DBHelper.GetGroups",e.getMessage());
            return null;
        }
    }

    public List<Student> GetStudents(int groupId){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor studentCursor = db.rawQuery(String.format("SELECT * FROM %s WHERE ID_Group=%s",TABLE_NAME_STUDENTS,String.valueOf(groupId)),null);
            List<Student> Students = new ArrayList<>();

            if(studentCursor.moveToFirst()){
                do{
                    Students.add(new Student(
                            studentCursor.getInt(0),
                            studentCursor.getString(1),
                            studentCursor.getInt(2)
                    ));
                }while (studentCursor.moveToNext());
            }

            studentCursor.close();
            db.close();
            return Students;
        }
        catch (Exception e){
            Log.d("DBHelper.GetStudents",e.getMessage());
            return null;
        }
    }

    public void UpdateGroup(Group group){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();


            values.put(FACULTY_COL,group.Faculty);
            values.put(COURSE_COL,group.Course);
            values.put(NAME_COL,group.Name);
            values.put(HEAD_COL,group.Head);

            db.update(TABLE_NAME_GROUPS,values,"ID_Group=?",new String[]{String.valueOf(group.Id)});
            db.close();
        }
        catch (Exception e){
            Log.d("DBHelper.UpdateGroup",e.getMessage());
        }
    }

    public void DeleteGroup(int id){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME_GROUPS,"ID_Group=?",new String[]{String.valueOf(id)});
            db.delete(TABLE_NAME_STUDENTS,"ID_Group=?",new String[]{String.valueOf(id)});
            db.close();
        }
        catch (Exception e){
            Log.d("DBHelper.DeleteGroup",e.getMessage());
        }
    }
}
