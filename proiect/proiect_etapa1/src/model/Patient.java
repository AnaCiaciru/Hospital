package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String patientName;
    private String disease;
    private String room;
    private String hospitalizationDate;
    private int age;


    private List<Prescription> prescriptions = new ArrayList<>();

    public Patient(String patientName, String disease, String room, String hospitalizationDate, int age) {
        this.patientName = patientName;
        this.disease = disease;
        this.room = room;
        this.hospitalizationDate = hospitalizationDate;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int varsta) {
        this.age = varsta;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getHospitalizationDate() {
        return hospitalizationDate;
    }

    public void setHospitalizationDate(String hospitalizationDate) {
        this.hospitalizationDate = hospitalizationDate;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public String toString() {
        return "\nPatient{" +
                "patientName='" + patientName + '\'' +
                ", disease='" + disease + '\'' +
                ", room='" + room + '\'' +
                ", hospitalizationDate='" + hospitalizationDate + '\'' +
                ", age=" + age +
                ", prescriptions=" + prescriptions +
                '}';
    }
}
