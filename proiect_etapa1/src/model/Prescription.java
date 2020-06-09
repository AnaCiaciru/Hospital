package model;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private String presDate;
    private List<Drug> drugs = new ArrayList<>();

    public Prescription(String presDate, List<Drug> drugs) {
        this.presDate = presDate;
        this.drugs = drugs;
    }

    public String getPresDate() {
        return presDate;
    }

    public void setPresDate(String presDate) {
        this.presDate = presDate;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "presDate='" + presDate + '\'' +
                ", drugs=" + drugs +
                '}';
    }
}
