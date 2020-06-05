package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeName;
    private int salary;
    private String room;

    public Employee(String employeeName, String room) {
        this.employeeName = employeeName;
        this.room = room;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", room='" + room + '\'' +
                '}';
    }

    public String toStringFile() {
        return  employeeName + ',' +
                salary + ',' +
                room;
    }
}

