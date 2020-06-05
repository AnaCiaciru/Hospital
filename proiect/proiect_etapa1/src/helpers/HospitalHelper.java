package helpers;

import contracts.HospitalHelperContract;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HospitalHelper extends HospitalHelperContract {
    @Override
    public Hospital buildHospitalWithInitialData() {
        // add doctors
        Doctor doctor1 = new Doctor("Popa Andrei", "A4");
        Doctor doctor2 = new Doctor("Ionescu Alexandra", "D3");
        Doctor doctor3 = new Doctor("Alexandrescu Mirela",  "C2");

        Stack<Doctor> doctors = new Stack<>();

        doctors.push(doctor1);
        doctors.push(doctor2);
        doctors.push(doctor3);

        // add nurses
        Nurse nurse1 = new Nurse("Pelican Ioana",   "A4");
        Nurse nurse2 = new Nurse("Ciocoi Andreea",  "D3");
        Nurse nurse3 = new Nurse("Mirun Ana",  "C2");

        List<Nurse> nurses = new ArrayList<>();

        nurses.add(nurse1);
        nurses.add(nurse2);
        nurses.add(nurse3);

        // add patients
        Patient patient1 = new Patient("Sukhmani Rayner", "Cancer", "C2", "04/23/2016", 20);
        Patient patient2 = new Patient("Lianne Cortez", "Alzheimer", "A4", "11/30/2017", 30);
        Patient patient3 = new Patient("Kanye Brook", "Cancer", "C2", "06/27/2018", 45);
        Patient patient4 = new Patient("Alex Ayala", "Covid-19", "D3", "07/13/2018", 62);

        List<Patient> patients = new ArrayList<>();

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);

        // add prescriptions
        List<Drug> drugs = new ArrayList<>();
        Drug drug1 = new Drug("ampicilina", 10);
        Drug drug2 = new Drug("paracetamol", 20);
        Drug drug3 = new Drug("strepsils", 13);

        drugs.add(drug1);
        drugs.add(drug2);
        drugs.add(drug3);

        String prescripDate = "4/3/2020";

        Prescription prescription = new Prescription(prescripDate, drugs);

        return new Hospital(
            "Mihai Sadoveanu",
            "Bucuresti",
                doctors,
                nurses,
                patients,
                prescription
        );
    }
}
