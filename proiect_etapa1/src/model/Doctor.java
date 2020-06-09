package model;

import java.util.List;

public class Doctor extends Employee {
    public Doctor(String employeeName, String room) {
        super(employeeName, room);
        this.setSalary(3000);
    }

    public void givePrescription(Patient p){}
}
