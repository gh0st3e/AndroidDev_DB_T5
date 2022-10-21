package denisleonov.fit.bstu.by.db_lab_11_15.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import denisleonov.fit.bstu.by.db_lab_11_15.Entities.Progress;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "STUDENTSDB.db";
    private static final int DB_VERSION = 1;

    private Date fromDDate;
    private Date toDDate;
    private Date curDate;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateTables.CreateFacultyTable());
        db.execSQL(CreateTables.CreateGroupTable());
        db.execSQL(CreateTables.CreateStudentTable());
        db.execSQL(CreateTables.CreateSubjectTable());
        db.execSQL(CreateTables.CreateProgressTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_FACULTY);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_GROUP);
        onCreate(db);
    }

    public List<String> GetGroup(int groupNumber){
        List<String> progressList = new ArrayList<>();
        if(groupNumber==1){
            progressList = GetGroupFromView();
            return progressList;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT Student.name, avg(Progress.mark) FROM %s INNER JOIN %s ON %s.id_Student = %s.id_Student WHERE Student.id_Group=%s GROUP BY Student.name",
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                groupNumber),null);

        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());
            return progressList;
        }
        return null;
    }

    public void AddView(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE VIEW Get_Groups AS SELECT Student.name, avg(Progress.mark) FROM Student INNER JOIN Progress ON Student.id_Student = Progress.id_Student WHERE Student.id_Group=1 GROUP BY Student.name");
    }

    public List<String> GetGroupFromView(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Get_Groups",null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());
            return progressList;
        }
        return null;
    }

    public List<String> GetBest(int groupNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT Student.name, avg(Progress.mark) FROM %s INNER JOIN %s ON %s.id_Student = %s.id_Student WHERE Student.id_Group=%s GROUP BY Student.name ORDER BY avg(Progress.mark) DESC LIMIT 1",
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                groupNumber),null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());
            return progressList;
        }
        return null;
    }

    public List<String> GetLow(int groupNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT Student.name, avg(Progress.mark) FROM %s INNER JOIN %s ON %s.id_Student = %s.id_Student WHERE Student.id_Group=%s AND Progress.mark<4 GROUP BY Student.name",
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                groupNumber),null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());

            return progressList;
        }
        if(progressList.size()==0){
            progressList.add("Нет неуспевающих студентов");
            return progressList;
        }
        return null;
    }

    public List<String> GetGroupStat(int groupNumber, String fromDate, String toDate, int Subject){

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-mm-yyyy");

        try {
            fromDDate= format.parse(fromDate);
            toDDate= format.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT Student.name, Progress.mark, Progress.examdate FROM %s INNER JOIN %s ON %s.id_Student = %s.id_Student WHERE Student.id_Group=%s AND Progress.id_Subject=%s GROUP BY Student.name",
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                Constants.TABLE_NAME_STUDENT,
                Constants.TABLE_NAME_PROGRESS,
                groupNumber,Subject),null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                rt+=" ";
                String Date = cursor.getString(2);
                try {
                    curDate = format.parse(Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(curDate.after(fromDDate) && curDate.before(toDDate)){
                    rt += cursor.getString(2);
                    progressList.add(rt);
                }
            } while(cursor.moveToNext());
        }
        return progressList;
    }

    public List<String> GetFacultyStat(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Faculty.faculty, avg(progress.mark) FROM Faculty" +
                " INNER JOIN Groups ON Faculty.id_Faculty=Groups.id_Faculty" +
                " INNER JOIN Student ON Groups.id_Group = Student.id_Group" +
                " INNER JOIN Progress ON Student.id_Student = Progress.id_Student" +
                " GROUP BY Faculty.faculty ORDER BY avg(progress.mark) DESC",null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());

            return progressList;
        }
        if(progressList.size()==0){
            progressList.add("Нет неуспевающих студентов");
            return progressList;
        }
        return null;
    }

    public List<String> GetGroupAverage(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Groups.name, avg(progress.mark) FROM Groups" +
                " INNER JOIN Student ON Groups.id_Group = Student.id_Group" +
                " INNER JOIN Progress ON Student.id_Student = Progress.id_Student" +
                " GROUP BY Groups.name ORDER BY avg(progress.mark) DESC",null);
        List<String> progressList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String rt = cursor.getString(0);
                rt+=" ";
                rt += String.valueOf(cursor.getFloat(1));
                progressList.add(rt);
            } while(cursor.moveToNext());

            return progressList;
        }
        if(progressList.size()==0){
            progressList.add("Нет неуспевающих студентов");
            return progressList;
        }
        return null;
    }

    public void AddIndex(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("CREATE INDEX IF NOT EXISTS EXAMDATE_INDEX ON PROGRESS(examdate)");
        db.execSQL("CREATE INDEX IF NOT EXISTS STUDENT_ID_INDEX ON PROGRESS(id_Student)");
    }

    public void AddTriggers(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("CREATE TRIGGER IF NOT EXISTS TRIGGER_IF_MORE_SIX_STUDENTS BEFORE INSERT ON Student Begin select RAISE (ABORT, 'Не может быть более 6 студентов в одной группе') WHERE (SELECT count(*) FROM Student current WHERE NEW.id_Group = current.id_Group) >=6; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS TRIGGER_IF_LESS_THREE_STUDENTS BEFORE DELETE ON Student Begin select RAISE (ABORT, 'Не может быть меньше двух студентов') WHERE (SELECT count(*) FROM Student current WHERE OLD.id_Group=current.id_Group)<=2; END");
        db.execSQL("CREATE TRIGGER IF NOT EXISTS TRIGGER_FOR_UPDATE_VIEW AFTER INSERT ON Groups BEGIN UPDATE Progress SET HEAD=new.id_Progress Where Progress.id_Progress; END ");

    }

    public void TestFirstTrigger(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
                +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
                + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(101,1,'Kotovich','17-05-2002','Slonim')");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
                +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
                + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(102,1,'Kotovich','17-05-2002','Slonim')");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
                +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
                + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(103,1,'Kotovich','17-05-2002','Slonim')");
        db.execSQL("INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
                +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
                + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(104,1,'Kotovich','17-05-2002','Slonim')");
    }

    public void TestThirdTrigger(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(FillDB.AddTestProgress);
    }

    public void FillDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.execSQL(FillDB.AddFacultyFit);
            db.execSQL(FillDB.AddFacultyIef);
            db.execSQL(FillDB.AddFacultyTov);

            db.execSQL(FillDB.AddGroupOneFit);
            db.execSQL(FillDB.AddGroupTwoFit);
            db.execSQL(FillDB.AddGroupOneIef);
            db.execSQL(FillDB.AddGroupOneTov);

            db.execSQL(FillDB.AddStudentOnePoibms);
            db.execSQL(FillDB.AddStudentTwoPoibms);
            db.execSQL(FillDB.AddStudentThreePoibms);
            db.execSQL(FillDB.AddStudentOneIsit);
            db.execSQL(FillDB.AddStudentTwoIsit);
            db.execSQL(FillDB.AddStudentThreeIsit);
            db.execSQL(FillDB.AddStudentOneEkonom);
            db.execSQL(FillDB.AddStudentTwoEkonom);
            db.execSQL(FillDB.AddStudentOneChemistry);
            db.execSQL(FillDB.AddStudentTwoChemistry);

            db.execSQL(FillDB.AddSubjectOne);
            db.execSQL(FillDB.AddSubjectTwo);
            db.execSQL(FillDB.AddSubjectThree);
            db.execSQL(FillDB.AddSubjectFour);
            db.execSQL(FillDB.AddSubjectFive);
            db.execSQL(FillDB.AddSubjectSix);

            db.execSQL(FillDB.AddProgressFitOaip);
            db.execSQL(FillDB.AddProgressFitBd);
            db.execSQL(FillDB.AddProgressFitOop);
            db.execSQL(FillDB.AddProgressIefBusiness);
            db.execSQL(FillDB.AddProgressTovChemistry);
            db.execSQL(FillDB.AddProgressFizra);

            AddView();
            //AddIndex();
            //AddTriggers();
        }
        catch (Exception e){

        }
    }

}
