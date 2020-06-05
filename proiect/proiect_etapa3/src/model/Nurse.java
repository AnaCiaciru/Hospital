package model;

import java.util.List;

public class Nurse extends Employee{
    public Nurse(String employeeName, String room) {
        super(employeeName,  room);
        this.setSalary(2000);
    }
}
