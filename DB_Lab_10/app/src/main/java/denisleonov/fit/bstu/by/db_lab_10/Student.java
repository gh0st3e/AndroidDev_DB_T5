package denisleonov.fit.bstu.by.db_lab_10;

public class Student {
    public int Id;
    public String Name;
    public int GroupId;

    public Student(){};

    public Student(int id,String name, int groupid){
        Id=id;
        Name = name;
        GroupId = groupid;
    }

    @Override
    public String toString() {
        return "Студент: " + Name
                +"\nНомер студента: " + Id
                +"\nГруппа студента: " + GroupId;
    }
}
