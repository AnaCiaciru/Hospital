import helpers.HospitalHelper;
import model.Doctor;
import model.Hospital;
import model.Nurse;
import model.Patient;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hospital hospital = new HospitalHelper().buildHospitalWithInitialData();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n");
        System.out.println(hospital.getHospitalName() + " Hospital is now on your service!\n");
        String first_name;
        String last_name;
        String room;

        while (true){
            System.out.println("Choose one of the option below to continue: \n");
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
                        System.out.println("5. Exit");
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
                                break;
                            case "2":
                                hospital.eraseDoctor();
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
                                break;
                            case "5":
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
                        System.out.println("5. List patients under 40 years.");
                        System.out.println("6. List prescriptions");
                        System.out.println("7. Exit");
                        String option2 = scanner.next();
                        switch (option2) {
                            case "1":
                                System.out.println("Doctors");
                                hospital.printDoctors();
                                break;
                            case "2":
                                System.out.println("Nurses");
                                hospital.printNurses();
                                break;
                            case "3":
                                System.out.println("Staff");
                                hospital.printStaff();
                                break;
                            case "4":
                                System.out.println("Patients by disease");
                                Collections.sort(hospital.getPatients(),(patient1, patient2) -> patient1.getDisease().compareTo(patient2.getDisease()));
                                hospital.printPatients();
                                break;
                            case "5":
                            System.out.println("Patients under 40 years.");
                            Collections.sort(hospital.getPatients(),(patient1, patient2) -> patient1.getAge() - patient2.getAge() );

                            hospital.printPatientsUnder(40);
                            break;
                            case "6":
                                hospital.printPrescription();
                                break;
                            case "7":
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
