package repository;

import model.Patient;

import java.sql.*;
import java.util.*;

public class PatientsRepository {
    // ptr conexiunea cu baza de date
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "root";

    // cod SQL
    private static final String GET_ALL_PEOPLE = "SELECT * FROM patients";
    private static final String CREATE_NEW_PERSON = "INSERT INTO patients(id_patient, patient_name, disease, room, " +
            "hospitalization_date, age, id_doctor, id_prescription)" +
            " values (?,?,?,?,?,?,NULL,NULL)";
    private static final String DELETE_PERSON = "DELETE FROM patients WHERE patient_name = ?";
    private static final String LAST_ID = "SELECT id_patient FROM patients ORDER BY id_patient DESC LIMIT 1";

    // functia pentru conectare
    public static Connection getDbConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }

    // date despre pacienti
    private List<Patient> patients = new ArrayList<>();

    public PatientsRepository() {
        try {
            patients = getPeople();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Patient> getPeople() throws  SQLException {
        List<Patient> patients = new ArrayList<>();

        Statement stat = getDbConnection().createStatement();
        ResultSet rs = stat.executeQuery(GET_ALL_PEOPLE);
        while (rs.next()) {
            Patient patient = new Patient(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            patients.add(patient);
        }
        rs.close();
        stat.close();
        return patients;
    }

    public boolean addPatient(Patient pat) throws SQLException{
        // gasesc ultimul id introdus
        Statement stat = getDbConnection().createStatement();
        ResultSet rs = stat.executeQuery(LAST_ID);
        rs.next();
        int last_id = rs.getInt(1) + 1;
        rs.close();
        stat.close();

        // introduc datele
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(CREATE_NEW_PERSON);
        preparedStatement.setInt(1, last_id);
        preparedStatement.setString(2, pat.getPatientName());
        preparedStatement.setString(3, pat.getDisease());
        preparedStatement.setString(4, pat.getRoom());
        preparedStatement.setString(5, pat.getHospitalizationDate());
        preparedStatement.setInt(6, pat.getAge());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deletePatient(String patientName) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PERSON);
        preparedStatement.setString(1, patientName);
        return preparedStatement.executeUpdate() > 0;
    }
}
