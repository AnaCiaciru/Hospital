import helpers.HospitalHelper;
import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Main {

    public static void main(String[] args) {
        Hospital hospital = new HospitalHelper().buildHospitalWithInitialData();
        Scanner scanner = new Scanner(System.in);

        try{
            Files.write(Paths.get(hospital.getAuditFile()),("History:").getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n");
        System.out.println(hospital.getHospitalName() + " Hospital is now at your service!\n");
        String first_name;
        String last_name;
        String room;
        while (true){
            System.out.println("Choose one of the options below to continue: \n");
            System.out.println("1. Modify Staff and Patients Information");
            System.out.println("2. Staff and Patients Information");
            System.out.println("3. Exit");
            String option1 = scanner.next();
            switch (option1){
                case "1":
                    System.out.println("Modify Staff and Patients Information");
                    boolean ok1 = true;
                    while(ok1) {
                        System.out.println("1. Add new doctor.");
                        System.out.println("2. Erase doctor.");
                        System.out.println("3. Add a new nurse.");
                        System.out.println("4. Add a new patient.");
                        System.out.println("5. Create a prescription");
                        System.out.println("6. Exit");
                        String option2 = scanner.next();
                        switch (option2) {
                            case "1":
                                System.out.println("Add new doctor");

                                System.out.println("First Name: ");
                                first_name = scanner.next();

                                System.out.println("Last Name: ");
                                last_name = scanner.next();


                                System.out.println("Room: ");
                                room = scanner.next();

                                Doctor doctor = new Doctor( first_name + " " + last_name , room);
                                hospital.addDoctor(doctor);

                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "a new doctor has been added").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                hospital.eraseDoctor();
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "a doctor has been removed").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "3":
                                System.out.println("First Name: ");
                                first_name = scanner.next();

                                System.out.println("Last Name: ");
                                last_name = scanner.next();

                                System.out.println("Room: ");
                                room = scanner.next();

                                Nurse nurse = new Nurse( first_name + " " + last_name , room);
                                hospital.addNurse(nurse);

                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "a new nurse has been added").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case "4":
                                System.out.println("First Name: ");
                                first_name = scanner.next();

                                System.out.println("Last Name: ");
                                last_name = scanner.next();

                                System.out.println("Disease: ");
                                String disease = scanner.next();

                                System.out.println("Room: ");
                                room = scanner.next();

                                System.out.println("Age: ");
                                String ageS = scanner.next();

                                System.out.println("Hospitalization date (MM/dd/yyyy): ");
                                String date = scanner.next();
                                int age = Integer.parseInt(ageS);
                                Patient patient = new Patient( first_name + " " + last_name, disease, room, date, age);
                                hospital.addPatient(patient);

                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "a new patient has been added").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "5":
                                System.out.println("Doctor last name: ");
                                String doctorName = scanner.next();
                                System.out.println("Doctor first name: ");
                                doctorName += " " + scanner.next();

                                System.out.println("Patient last name: ");
                                String patientName = scanner.next();
                                System.out.println("Patient first name: ");
                                patientName += " " + scanner.next();

                                Doctor doc = hospital.findDoctor(doctorName);
                                Patient pat = hospital.findPatient(patientName);

                                if(doc == null || pat == null){
                                    System.out.println("Incorrect data!");
                                    break;
                                }

                                System.out.println("The current date (MM/DD/YYYY): ");
                                String datePres = scanner.next();

                                hospital.printDrugs();

                                List<Drug> drugs = new ArrayList<>();
                                while(true) {
                                    System.out.println("Drug name: ");
                                    String drugName = scanner.next();

                                    System.out.println("Amount: ");
                                    String cantitate = scanner.next();

                                    Drug dr = new Drug(drugName, Integer.parseInt(cantitate));
                                    drugs.add(dr);

                                    System.out.println("exit");
                                    System.out.println("continue");

                                    String result = scanner.next();
                                    if(result.equals("exit"))
                                        break;

                                }

                                Prescription pres = new Prescription(datePres, drugs);

                                doc.givePrescription(pat, pres);
                                System.out.println(pat.getPrescriptions());
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "a new prescription has been made").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "6":
                                ok1 = false;
                                break;
                            default:
                                System.out.println("\n");
                                System.out.println("You have chosen an incorrect option, select one of the above.");
                                break;
                        }
                    }
                    break;
                case "2":
                    System.out.println("Staff and Patients Information");
                    boolean ok2 = true;
                    while(ok2) {
                        System.out.println("1. List doctors");
                        System.out.println("2. List nurses.");
                        System.out.println("3. List staff.");
                        System.out.println("4. List patients by disease.");
                        System.out.println("5. List patients under x years.");
                        System.out.println("6. Exit");
                        String option2 = scanner.next();
                        switch (option2) {
                            case "1":
                                System.out.println("Doctors");
                                hospital.printDoctors();
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "doctor list has been displayed").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                System.out.println("Nurses");
                                hospital.printNurses();
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "nurse list has been displayed").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "3":
                                System.out.println("Staff");
                                hospital.printStaff();
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "staff list has been displayed").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "4":
                                System.out.println("Patients by disease");
                                Collections.sort(hospital.getPatients(),(patient1, patient2) -> patient1.getDisease().compareTo(patient2.getDisease()));
                                hospital.printPatients();
                                try{
                                    Files.write(Paths.get(hospital.getAuditFile()),("\n" + "patients by disease list has been displayed").getBytes(),APPEND);
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "5":
                            System.out.println("Patients under x years.");
                            Collections.sort(hospital.getPatients(),(patient1, patient2) -> patient1.getAge() - patient2.getAge() );
                            System.out.println("x = ");
                            String x = scanner.next();
                            hospital.printPatientsUnder(Integer.parseInt(x));
                            try{
                                Files.write(Paths.get(hospital.getAuditFile()),("\n" + "patients under " + x +" years list has been displayed").getBytes(),APPEND);
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                            case "6":
                                ok2 = false;
                                break;
                            default:
                                System.out.println("\n");
                                System.out.println("You have chosen an incorrect option, select one of the above.");
                                break;
                        }
                    }
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n");
                    System.out.println("You have chosen an incorrect option, select one of the above.");
            }
        }
    }
}
