package helpers;

import contracts.HospitalHelperContract;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class HospitalHelper extends HospitalHelperContract {
    @Override
    public Hospital buildHospitalWithInitialData() {
        // add doctors
        Stack<Doctor> doctors = new Stack<>();
        List<Nurse> nurses = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();

        String filePath = "src/helpers/data.csv";

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {

                String[] data = scanner.nextLine().split(",");

                switch(data[0]){
                    case "doctor":
                        Doctor doctor = new Doctor(data[1],data[2]);
                        doctors.push(doctor);
                        break;
                    case "nurse":
                        Nurse nurse = new Nurse(data[1],data[2]);
                        nurses.add(nurse);
                        break;
                    case "patient":
                        Patient patient = new Patient(data[1],data[2],data[3],data[4],Integer.parseInt(data[5]));
                        patients.add(patient);
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        // add prescriptions
        List<Drug> drugs = new ArrayList<>();
        String filePathDrugs = "src/helpers/drugs.csv";

        try (Scanner scanner = new Scanner(new File(filePathDrugs))) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                Drug drug = new Drug(data[0], Integer.parseInt(data[1]));
                drugs.add(drug);
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        String prescripDate = "4/3/2020";

        Prescription prescription = new Prescription(prescripDate, drugs);

        return new Hospital(
             "src/files/auditFile.txt",
            "Mihai Sadoveanu",
            "Bucuresti",
                doctors,
                nurses,
                patients,
                drugs
        );
    }
}
