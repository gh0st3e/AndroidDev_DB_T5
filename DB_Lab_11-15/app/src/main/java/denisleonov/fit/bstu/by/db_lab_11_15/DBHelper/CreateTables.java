package denisleonov.fit.bstu.by.db_lab_11_15.DBHelper;

public class CreateTables {
    public static String CreateFacultyTable(){
        return "CREATE TABLE " + Constants.TABLE_NAME_FACULTY + " ("
                + Constants.ID_FACULTY_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.FACULTY_COL + " TEXT,"
                + Constants.DEAN_COL + " TEXT,"
                + Constants.TIME_DEAN_COL + " TEXT)";
    }
    public static String CreateGroupTable(){
        return "CREATE TABLE " + Constants.TABLE_NAME_GROUP + " ("
                + Constants.ID_GROUP_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_FACULTY_COL + " INTEGER,"
                + Constants.COURSE_COL + " INTEGER,"
                + Constants.NAME_COL + " TEXT,"
                + Constants.HEAD_COL + " TEXT,"
                + " FOREIGN KEY (id_Faculty) REFERENCES Faculty(id_Faculty) ON DELETE CASCADE)";
    }
    public static String CreateStudentTable(){
        return "CREATE TABLE " + Constants.TABLE_NAME_STUDENT + " ("
                + Constants.ID_STUDENT_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_GROUP_COL + " INTEGER,"
                + Constants.NAME_COL + " TEXT,"
                + Constants.BIRTHDAY_COL + " TEXT,"
                + Constants.ADDRESS_COL + " TEXT,"
                + " FOREIGN KEY (id_Group) REFERENCES Groups(id_Group) ON DELETE CASCADE)";
    }
    public static String CreateSubjectTable(){
        return "CREATE TABLE " + Constants.TABLE_NAME_SUBJECT + " ("
                + Constants.ID_SUBJECT_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.SUBJECT_COL + " TEXT)";
    }
    public static String CreateProgressTable(){
        return "CREATE TABLE " + Constants.TABLE_NAME_PROGRESS + " ("
                + Constants.ID_PROGRESS_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_STUDENT_COL + " INTEGER,"
                + Constants.ID_SUBJECT_COL + " INTEGER,"
                + Constants.EXAMDATE_COL + " TEXT,"
                + Constants.MARK_COL + " INTEGER,"
                + Constants.TEACHER_COL + " TEXT,"
                + " FOREIGN KEY (id_Student) REFERENCES Student(id_Student) ON DELETE CASCADE,"
                + " FOREIGN KEY (id_Subject) REFERENCES Subject(id_Subject) ON DELETE CASCADE)";
    }
}