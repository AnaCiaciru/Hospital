package contracts;

import model.Doctor;
import model.Nurse;
import model.Patient;

public interface HospitalContract {
    void addDoctor(Doctor doctor);
    void addNurse(Nurse nurse);
    void addPatient(Patient patient);

    void printDoctors();
    void printNurses();
    void printStaff();
    void printPatients();
    void printPatientsUnder(int age);

    void printDrugs();

    void eraseDoctor();
    Doctor findDoctor(String name);
    Patient findPatient(String name);
}
