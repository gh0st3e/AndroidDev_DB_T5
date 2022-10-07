package denisleonov.fit.bstu.by.db_lab_9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Lab9.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "MyTable";

    private static final String ID_COL = "id";
    private static final String FLOAT_COL = "FloatValue";
    private static final String TEXT_COL = "TextValue";





    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FLOAT_COL + " FLOAT,"
                + TEXT_COL + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void Insert(String id,String f, String t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(!id.isEmpty()){
            values.put(ID_COL,id);
        }
        values.put(FLOAT_COL,f);
        values.put(TEXT_COL,t);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void Update(String id,String f,String t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FLOAT_COL,f);
        values.put(TEXT_COL,t);

        db.update(TABLE_NAME,values,"id=?",new String[]{id});
        db.close();
    }

    public void Delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{id});
        db.close();
    }

    public DBValues Select(String id){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME,new String[]{ID_COL,FLOAT_COL,TEXT_COL},"id = ?",
                    new String[]{id},null,null,null);

            DBValues values = new DBValues();
            if(cursor.moveToFirst()){
                values.id = cursor.getInt(0);
                values.floatValue = cursor.getFloat(1);
                values.stringValue = cursor.getString(2);
            }

            cursor.close();
            return values;
        }
        catch (Exception ex){
            return null;
        }
    }

    public DBValues SelectRaw(String id){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE id=%s",TABLE_NAME,id),null);
            DBValues values = new DBValues();
            if(cursor.moveToFirst()){
                values.id = cursor.getInt(0);
                values.floatValue = cursor.getFloat(1);
                values.stringValue = cursor.getString(2);
            }

            cursor.close();
            return values;
        }
        catch (Exception ex){
            return null;
        }
    }
}
