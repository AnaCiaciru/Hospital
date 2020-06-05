package model;

public class Drug {
    private String drugName;
    private int amount;

    public Drug(String drugName, int amount) {
        this.drugName = drugName;
        this.amount = amount;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugName='" + drugName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
