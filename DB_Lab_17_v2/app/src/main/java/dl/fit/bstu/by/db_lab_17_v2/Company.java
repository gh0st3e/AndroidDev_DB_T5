package dl.fit.bstu.by.db_lab_17_v2;

public class Company {
    public int ID;
    public String Name;
    public int Employees;
    public int Money;

    public Company(int ID, String name, int employees, int money) {
        this.ID = ID;
        Name = name;
        Employees = employees;
        Money = money;
    }

    @Override
    public String toString() {
        return Name + "\nEmployees count: " + Employees + "\nCompany money: " + Money;
    }
}
