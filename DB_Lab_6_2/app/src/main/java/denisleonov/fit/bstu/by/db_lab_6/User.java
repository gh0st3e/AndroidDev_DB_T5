package denisleonov.fit.bstu.by.db_lab_6;

public class User {
    public String Name;
    public String Surname;
    public String Phone;
    public String Birth;
    public Boolean IsOpen;

    public User(String name, String surname, String phone, String birth, Boolean isOpen) {
        Name = name;
        Surname = surname;
        Phone = phone;
        Birth = birth;
        IsOpen = isOpen;
    }

    @Override
    public String toString() {
        return Name + " " + Surname
                + "\n" + Phone
                + "\n" + Birth;
    }
}
