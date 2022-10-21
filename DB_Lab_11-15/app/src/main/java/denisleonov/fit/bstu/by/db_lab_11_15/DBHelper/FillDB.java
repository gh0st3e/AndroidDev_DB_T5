package denisleonov.fit.bstu.by.db_lab_11_15.DBHelper;

public class FillDB {
    // Скрипты для создания факультетов
    public static String AddFacultyFit = "INSERT INTO " + Constants.TABLE_NAME_FACULTY + " ("
            +Constants.ID_FACULTY_COL + ", " + Constants.FACULTY_COL + ", " + Constants.DEAN_COL
            + ", " + Constants.TIME_DEAN_COL + ") VALUES (1,'FIT','Shiman','8-17')";
    public static String AddFacultyIef = "INSERT INTO " + Constants.TABLE_NAME_FACULTY + " ("
            +Constants.ID_FACULTY_COL + ", " + Constants.FACULTY_COL + ", " + Constants.DEAN_COL
            + ", " + Constants.TIME_DEAN_COL + ") VALUES (2,'IEF','Leonov','8-17')";
    public static String AddFacultyTov = "INSERT INTO " + Constants.TABLE_NAME_FACULTY + " ("
            +Constants.ID_FACULTY_COL + ", " + Constants.FACULTY_COL + ", " + Constants.DEAN_COL
            + ", " + Constants.TIME_DEAN_COL + ") VALUES (3,'TOV','Bich','8-17')";

    // Скрипты для создания групп
    public static String AddGroupOneFit = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (1,1,3,'POIBMS','Kotovich')";
    public static String AddGroupTwoFit = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (2,1,3,'ISIT','Rudman')";
    /*public static String AddGroupThreeFit = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (3,1,3,'POIT','Hovanskiy')";
    public static String AddGroupFourFit = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (4,1,3,'DEIVI','Gummennikova')"; */
    public static String AddGroupOneIef = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (3,2,3,'EKONOM','Kohnovich')";
    /*public static String AddGroupTwoIef = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (6,2,3,'BUSINESS','Golub')"; */
    public static String AddGroupOneTov = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (4,3,3,'CHEMISTRY','Bragilevskaya')";
    /*public static String AddGroupTwoTov = "INSERT INTO " + Constants.TABLE_NAME_GROUP + " ("
            +Constants.ID_GROUP_COL + ", " + Constants.ID_FACULTY_COL + ", " + Constants.COURSE_COL
            + ", " + Constants.NAME_COL + ", " + Constants.HEAD_COL + ") VALUES (8,3,3,'MECH','Bachura')"; */

    // Скрипты для создания студентов
    public static String AddStudentOnePoibms = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(1,1,'Kotovich','17-05-2002','Slonim')";
    public static String AddStudentTwoPoibms = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(2,1,'Leonov','08-04-2003','Gomel')";
    public static String AddStudentThreePoibms = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(3,1,'Bich','27-06-2003','Mosty')";
    public static String AddStudentOneIsit = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(4,2,'Rudman','25-05-2003','Volchie Nory')";
    public static String AddStudentTwoIsit = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(5,2,'Matataras','01-01-1945','Tarakanovka')";
    public static String AddStudentThreeIsit = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(6,2,'Gorbach','01-01-1945','Minsk')";
    public static String AddStudentOneEkonom = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(7,3,'Kohnovich','20-08-2003','Grodno')";
    public static String AddStudentTwoEkonom = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(8,3,'Golub','01-05-2002','Minsk')";
    public static String AddStudentOneChemistry = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(9,4,'Bragilevskaya','19-08-2002','Gomel')";
    public static String AddStudentTwoChemistry = "INSERT INTO " + Constants.TABLE_NAME_STUDENT + " ("
            +Constants.ID_STUDENT_COL + ", " + Constants.ID_GROUP_COL + ", " + Constants.NAME_COL
            + ", " + Constants.BIRTHDAY_COL + ", " + Constants.ADDRESS_COL + ") VALUES(10,4,'Bachura','19-10-2002','Gomel')";

    //Скрипты для создания предметов
    public static String AddSubjectOne = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(1,'OAIP')";
    public static String AddSubjectTwo = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(2,'BD')";
    public static String AddSubjectThree = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(3,'OOP')";
    public static String AddSubjectFour = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(4,'BUSINESS')";
    public static String AddSubjectFive = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(5,'FIZRA')";
    public static String AddSubjectSix = "INSERT INTO " + Constants.TABLE_NAME_SUBJECT + " ("
            +Constants.ID_SUBJECT_COL + ", " + Constants.SUBJECT_COL + ") VALUES(6,'CHEMISTRY')";

    //Скрипты для создания прогресса
    public static String AddProgressFitOaip = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(1,1,1,'31-12-2021',10,'Beloded'),(2,2,1,'31-12-2021',8,'Beloded'),(3,3,1,'31-12-2021',6,'Beloded') "
            +",(4,4,1,'31-12-2021',4,'Beloded'),(5,5,1,'31-12-2021',8,'Beloded'),(6,6,1,'31-12-2021',7,'Beloded')";
    public static String AddProgressFitBd = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(7,1,2,'30-12-2021',6,'Blinova'),(8,2,2,'30-12-2021',5,'Blinova'),(9,3,2,'30-12-2021',10,'Blinova') "
            +",(10,4,2,'27-12-2021',3,'Blinova'),(11,5,2,'27-12-2021',9,'Blinova'),(12,6,2,'27-12-2021',7,'Blinova')";
    public static String AddProgressFitOop = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(13,1,3,'25-12-2021',2,'Patsei'),(14,2,3,'25-12-2021',3,'Patsei'),(15,3,3,'25-12-2021',5,'Patsei') "
            +",(16,4,3,'30-12-2021',4,'Patsei'),(17,5,3,'30-12-2021',6,'Patsei'),(18,6,3,'30-12-2021',7,'Patsei')";
    public static String AddProgressIefBusiness = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(19,7,4,'25-11-2021',10,'Lojechkin'),(20,8,4,'25-11-2021',8,'Lojechkin')";
    public static String AddProgressTovChemistry = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(21,9,6,'23-12-2021',4,'Mamaev'),(22,10,6,'23-12-2021',4,'Mamaev')";
    public static String AddProgressFizra = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(23,7,5,'01-01-2021',2,'Baykov'),(24,8,5,'01-01-2021',3,'Baykov'),(25,9,5,'01-01-2021',5,'Baykov') "
            +",(26,10,5,'01-01-2021',4,'Baykov')";
    public static String AddTestProgress = "INSERT INTO " + Constants.TABLE_NAME_PROGRESS + " ("
            +Constants.ID_PROGRESS_COL + ", " + Constants.ID_STUDENT_COL + ", " + Constants.ID_SUBJECT_COL + ", "
            +Constants.EXAMDATE_COL + ", " + Constants.MARK_COL + ", " + Constants.TEACHER_COL
            +") VALUES(101,1,1,'31-12-2021',10,'Beloded'),(102,2,1,'31-12-2021',8,'Beloded'),(103,3,1,'31-12-2021',6,'Beloded') "
            +",(104,4,1,'31-12-2021',4,'Beloded'),(105,5,1,'31-12-2021',8,'Beloded'),(106,6,1,'31-12-2021',7,'Beloded')";
}
