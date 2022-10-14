package denisleonov.fit.bstu.by.db_lab_10;

import java.io.Serializable;

public class Group implements Serializable {
    public int Id;
    public String Faculty;
    public int Course;
    public String Name;
    public String Head;

    public Group(){};

    public Group(int id, String faculty, int course, String name, String head) {
        Id = id;
        Faculty = faculty;
        Course = course;
        Name = name;
        Head = head;
    }

    @Override
    public String toString() {
        return Faculty + " " + Course + " курс"
                +"\nГруппа: " + Name
                +"\nНомер группы: " + Id
                +"\nСтароста: " + Head;
    }
}
