package model;

import contracts.HospitalContract;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Hospital implements HospitalContract {
    // data
    private String hospitalName;
    private String hospitalLocation;
    private String auditFile;

    private Stack<Doctor> doctors = new Stack<>();
    private List<Nurse> nurses = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    private List<Drug> drugs = new ArrayList<>();

    /// constructors
    public Hospital(String auditFile, String hospitalName, String hospitalLocation, Stack<Doctor> doctors, List<Nurse> nurses, List<Patient> patients, List<Drug> drugs) {
        this.auditFile = auditFile;
        this.hospitalName = hospitalName;
        this.hospitalLocation = hospitalLocation;
        this.doctors = doctors;
        this.nurses = nurses;
        this.patients = patients;
        this.drugs = drugs;
    }

    /// setters and getter

    public String getAuditFile() {
        return auditFile;
    }

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

    // FileWriter
    @Override
    public void printDoctors() {
        System.out.println(doctors);
        String filePath = "src/files/doctors.csv";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // header
            fileWriter.write("Nume si prenume" + "," + "Salariu" + "," + "Sala" + '\n');
            // continutul
            doctors.forEach((line) -> {
                try {
                    fileWriter.write(line.toStringFile() + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FileOutputStream
    @Override
    public void printNurses() {
        System.out.println(nurses);
        String filePath = "src/files/nurses.csv";
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath))) {
            // header
            fileOutputStream.write(("Nume si prenume" + "," + "Salariu" + "," + "Sala" + "\n").getBytes());
            // continut
            nurses.forEach((line) ->
                    {
                        try {
                            fileOutputStream.write((line.toStringFile() + "\n").getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printStaff() {
        System.out.println(doctors);
        System.out.println(nurses);
    }

    // BufferWriter
    @Override
    public void printPatients() {
        System.out.println(patients);
        String filePath = "src/files/patients.csv";
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
            bufferedWriter.write("Nume si prenume" + "," + "Diagnostic" + "," + "Sala" + "Data internarii" + "Varsta" + "Prescriptii" + "\n");
            patients.forEach((line) ->
                    {
                        try {
                            bufferedWriter.write(line.toStringFile() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
       }catch (IOException e) {
           e.printStackTrace();
       }

    }

    @Override
    public void printPatientsUnder(int age) {
        for (Patient patient : patients) {
            if (patient.getAge() > age) return;
            System.out.println(patient);
        }
    }


    @Override
    public void printDrugs() {
        System.out.println(drugs);
    }

    @Override
    public void eraseDoctor() {
        doctors.pop();
        System.out.println(">>>>>>>>>>> successfully erased <<<<<<<<<<<<<<");
    }

    @Override
    public Doctor findDoctor(String name){
        for (Doctor doctor : this.doctors) {
            if((doctor.getEmployeeName()).equals(name))
                return doctor;
        }
        return null;
    }

    @Override
    public Patient findPatient(String name){
        for (Patient patient : this.patients) {
            if((patient.getPatientName()).equals(name))
                return patient;
        }
        return null;
    }
}
