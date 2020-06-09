package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patient {
    private String patientName;
    private String disease;
    private String room;
    private String hospitalizationDate;
    private int age;


    private Map<Doctor, Prescription> prescriptions = new HashMap<>();

    public Patient(String patientName, String disease, String room, String hospitalizationDate, int age) {
        this.patientName = patientName;
        this.disease = disease;
        this.room = room;
        this.hospitalizationDate = hospitalizationDate;
        this.age = age;
    }

    public Map<Doctor, Prescription> getPrescriptions() {
        return prescriptions;
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


    // adauga o prescriptie
    public void addPrescription(Doctor doctor, Prescription pres){
        this.prescriptions.put(doctor, pres);
    }

    @Override
    public String toString() {
        return "\nPatient: " +
                " '" + patientName + '\'' +
                ", '" + disease + '\'' +
                ", room = '" + room + '\'' +'\n' +
                ", hospDate  = '" + hospitalizationDate + '\'' +
                "," + age + " years, " +
                ", prescriptions = " + prescriptions +
                '\n';
    }

    public String toStringFile() {
        return  patientName + ',' +
                disease + ',' +
                room + ',' +
                hospitalizationDate + ',' +
                age + ',' +
                prescriptions;
    }
}
