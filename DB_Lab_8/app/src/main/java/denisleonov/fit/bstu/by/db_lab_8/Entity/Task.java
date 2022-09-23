package denisleonov.fit.bstu.by.db_lab_8.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    public String Date;
    public String Value;
    public String Category;

    public Task(String value, String category, String date) {
        Value = value;
        Category = category;
        Date = date;
    }

    public Task(){}

    @Override
    public String toString() {
        return Value;
    }

    public String getDate() {
        return Date;
    }

    public String getValue() {
        return Value;
    }

    public String getCategory() {
        return Category;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(Date, task.Date) && Objects.equals(Value, task.Value) && Objects.equals(Category, task.Category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Date, Value, Category);
    }
}
