package model;

import contracts.HospitalContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Hospital implements HospitalContract {
    // data
    private String hospitalName;
    private String hospitalLocation;

    private Stack<Doctor> doctors = new Stack<>();
    private List<Nurse> nurses = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    private Prescription prescription;

    /// constructors
    public Hospital(String hospitalName, String hospitalLocation, Stack<Doctor> doctors, List<Nurse> nurses, List<Patient> patients, Prescription prescription) {
        this.hospitalName = hospitalName;
        this.hospitalLocation = hospitalLocation;
        this.doctors = doctors;
        this.nurses = nurses;
        this.patients = patients;
        this.prescription = prescription;
    }

    /// setters and getter
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Stack<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /// methods
    @Override
    public void addDoctor(Doctor doctor) {
        this.doctors.push(doctor);
        System.out.println(">>>>>>>>>>> successfully added <<<<<<<<<<<<<<");
    }

    @Override
    public void addNurse(Nurse nurse) {
        this.nurses.add(nurse);
        System.out.println(">>>>>>>>>>> successfully added <<<<<<<<<<<<<<");
    }

    @Override
    public void addPatient(Patient patient) {
        this.patients.add(patient);
        System.out.println(">>>>>>>>>>> successfully added <<<<<<<<<<<<<<");
    }


    @Override
    public void printDoctors() {
        System.out.println(doctors);
    }

    @Override
    public void printNurses() {
        System.out.println(nurses);
    }

    @Override
    public void printStaff() {
        System.out.println(doctors);
        System.out.println(nurses);
    }

    @Override
    public void printPatients() {
        System.out.println(patients);
    }

    @Override
    public void printPatientsUnder(int age) {
        for(Patient patient: patients){
            if(patient.getAge() > age) return;
            System.out.println(patient);
        }
    }

    @Override
    public void printPrescription() {
        System.out.println(prescription);
    }

    @Override
    public void eraseDoctor() {
        doctors.pop();
        System.out.println(">>>>>>>>>>> successfully erased <<<<<<<<<<<<<<");
    }
}
