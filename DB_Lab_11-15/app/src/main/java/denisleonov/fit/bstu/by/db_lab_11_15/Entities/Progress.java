package denisleonov.fit.bstu.by.db_lab_11_15.Entities;

public class Progress {
    public int Id_Progress;
    public int Id_Student;
    public int Id_Subject;
    public String ExamDate;
    public int Mark;
    public String Teacher;

    public Progress(int id_Progress, int id_Student, int id_Subject, String examDate, int mark, String teacher) {
        Id_Progress = id_Progress;
        Id_Student = id_Student;
        Id_Subject = id_Subject;
        ExamDate = examDate;
        Mark = mark;
        Teacher = teacher;
    }

    @Override
    public String toString() {
        return Id_Progress + " " + Id_Student + " " + Id_Subject + " " + ExamDate + " " + Mark + " " + Teacher;
    }
}
