package dl.fit.bstu.by.db_lab_16;

import java.io.Serializable;

public class ContactEntity implements Serializable {
    public String ID;
    public String Name;
    public String Phone;

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return Phone;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.Phone = phoneNumber;
    }

    @Override
    public String toString() {
        return ID + " " + Name + "\n" + Phone;
    }
}
