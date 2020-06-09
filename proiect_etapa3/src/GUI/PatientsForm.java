package GUI;

import model.Doctor;
import model.Patient;
import repository.PatientsRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PatientsForm extends JFrame {
    private JPanel contentPane;

    private static PatientsRepository doctorsRepository = new PatientsRepository();

    public static void main(String[] args) throws SQLException {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientsForm frame = new PatientsForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PatientsForm() throws SQLException {
        JFrame jFrame = new JFrame("Patients");

        JLabel welcomeMessageLabel = new JLabel("Patients");
        welcomeMessageLabel.setBounds(400, 10, 200, 30);
        welcomeMessageLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        jFrame.add(welcomeMessageLabel);

        // add
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameTextField = new JTextField();
        nameLabel.setBounds(10, 100, 200, 30);
        nameTextField.setBounds(55, 100, 200, 30);

        JLabel diseaseLabel = new JLabel("Disease: ");
        JTextField diseaseTextField = new JTextField();
        diseaseLabel.setBounds(10, 150, 200, 30);
        diseaseTextField.setBounds(55, 150, 200, 30);

        JLabel roomLabel = new JLabel("Room: ");
        JTextField roomTextField = new JTextField();
        roomLabel.setBounds(10, 200, 200, 30);
        roomTextField.setBounds(55, 200, 200, 30);

        JLabel hDateLabel = new JLabel("Date: ");
        JTextField hDateTextField = new JTextField();
        hDateLabel.setBounds(10, 250, 200, 30);
        hDateTextField.setBounds(55, 250, 200, 30);

        JLabel ageLabel = new JLabel("Age: ");
        JTextField ageTextField = new JTextField();
        ageLabel.setBounds(10, 300, 200, 30);
        ageTextField.setBounds(55, 300, 200, 30);


        JButton jButtonAdd = new JButton("Save Patient");
        jButtonAdd.setBounds(55, 400, 150, 30);

        // crearea listei
        JList<Patient> jList = new JList<>();
        jList.setBounds(300, 100, 600, 300);
        try {
            jList.setListData(getPatientsAsArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        jButtonAdd.addActionListener(addActionListener(nameTextField, diseaseTextField,
                roomTextField, hDateTextField, ageTextField, jList));

        // delete
        JButton deletePersonButton = new JButton("Delete Patient");
        deletePersonButton.setBounds(400, 400, 200, 30);
        deletePersonButton.addActionListener(deletePersonActionListener(jList));

        jFrame.add(deletePersonButton);
        jFrame.add(jList);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(goBackActionListener());
        backButton.setBounds(10, 10, 100, 30);
        jFrame.add(backButton);

        jFrame.setBounds(200, 200, 1000, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // adaugam ce am creat
        jFrame.add(nameLabel);
        jFrame.add(nameTextField);
        jFrame.add(diseaseLabel);
        jFrame.add(diseaseTextField);
        jFrame.add(roomLabel);
        jFrame.add(roomTextField);
        jFrame.add(hDateLabel);
        jFrame.add(hDateTextField);
        jFrame.add(ageLabel);
        jFrame.add(ageTextField);
        jFrame.add(jButtonAdd);


        jFrame.setBounds(200, 200, 1000, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private static ActionListener addActionListener(JTextField patientNameField, JTextField patientDiseaseField,
                                                    JTextField patientRoomField, JTextField patientHDateField,
                                                    JTextField patientAgeField, JList jList) {
        ActionListener actionListener = action -> {
            String name = patientNameField.getText();
            String disease = patientDiseaseField.getText();
            String room = patientRoomField.getText();
            String HDate = patientHDateField.getText();
            String ageStr = patientAgeField.getText();
            int age = Integer.parseInt(ageStr);

            Patient patient = new Patient(name, disease, room, HDate, age);
            try {
                doctorsRepository.addPatient(patient);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jList.setListData(getPatientsAsArray());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Person was successfully added!!!");
        };
        return actionListener;
    }

    private static ActionListener deletePersonActionListener(JList<Patient> jList) {
        ActionListener actionListener = actionEvent -> {
            try {
                doctorsRepository.deletePatient(jList.getSelectedValue().getPatientName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jList.setListData(getPatientsAsArray());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Person was deleted!!!");
        };
        return actionListener;
    }

    private static Patient[] getPatientsAsArray() throws SQLException {
        return doctorsRepository.getPeople().toArray(new Patient[0]);
    }


    private ActionListener goBackActionListener() {
        ActionListener actionListener = actionEvent -> {
            MainForm frame = null;
            frame = new MainForm();
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        };
        return actionListener;
    }

}
