package repository;

import model.Doctor;

import java.sql.*;
import java.util.*;

public class DoctorsRepository {
    // ptr conexiunea cu baza de date
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "root";

    // cod SQL
    private static final String GET_ALL_PEOPLE = "SELECT * FROM doctors";
    private static final String CREATE_NEW_PERSON = "INSERT INTO doctors(id_doctor,doctor_name,room)" +
            " values (?,?,?)";
    private static final String DELETE_PERSON = "DELETE FROM doctors WHERE doctor_name = ?";
    private static final String LAST_ID = "SELECT id_doctor FROM doctors ORDER BY id_doctor DESC LIMIT 1";

    // functia pentru conectare
    public static Connection getDbConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }

    // date despre doctori
    private List<Doctor> doctors = new ArrayList<>();

    public DoctorsRepository() {
        try {
            doctors = getPeople();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Doctor> getPeople() throws  SQLException {
        List<Doctor> doctors = new ArrayList<>();

        Statement stat = getDbConnection().createStatement();
        ResultSet rs = stat.executeQuery(GET_ALL_PEOPLE);
        while (rs.next()) {
            Doctor doctor = new Doctor(rs.getString(2), rs.getString(3));
            doctors.add(doctor);
        }
        rs.close();
        stat.close();
        return doctors;
    }

    public boolean addDoctor(Doctor doc) throws SQLException{
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
        preparedStatement.setString(2, doc.getEmployeeName());
        preparedStatement.setString(3, doc.getRoom());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteDoctor(String doctorName) throws SQLException{
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(DELETE_PERSON);
        preparedStatement.setString(1, doctorName);
        return preparedStatement.executeUpdate() > 0;
    }
}
