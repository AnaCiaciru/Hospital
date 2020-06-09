package model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Doctor extends Employee {
    public Doctor(String employeeName, String room) {
        super(employeeName, room);
        this.setSalary(3000);
    }

    public void givePrescription(@NotNull Patient patient, Prescription pres){
        // atribui Pacientului p o prescriptie
        // intruce numele doctorului si a pacientului
        // i se afiseaza o lista de medicamente
        // alege ce medicament si cat ii prescrie
        // in fuctie doar ii atribui

        patient.addPrescription(this, pres);
    }

}
