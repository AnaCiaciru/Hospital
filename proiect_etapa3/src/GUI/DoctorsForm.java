package GUI;

import model.Doctor;
import repository.DoctorsRepository;
import repository.PatientsRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DoctorsForm extends JFrame {
    private JPanel contentPane;
    private static DoctorsRepository doctorsRepository = new DoctorsRepository();

    public static void main(String[] args) throws SQLException {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DoctorsForm frame = new DoctorsForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

        public DoctorsForm() throws SQLException {
            JFrame jFrame = new JFrame("Doctors");

            JLabel welcomeMessageLabel = new JLabel("Doctors");
            welcomeMessageLabel.setBounds(400, 10, 200, 30);
            welcomeMessageLabel.setFont(new Font("Courier New", Font.BOLD, 30));
            jFrame.add(welcomeMessageLabel);

            // add
            JLabel nameLabel = new JLabel("Name: ");
            JTextField nameTextField = new JTextField();
            nameLabel.setBounds(10, 100, 200, 30);
            nameTextField.setBounds(55, 100, 200, 30);

            JLabel roomLabel = new JLabel("Room: ");
            JTextField roomTextField = new JTextField();
            roomLabel.setBounds(10, 150, 200, 30);
            roomTextField.setBounds(55, 150, 200, 30);

            JButton jButtonAdd = new JButton("Save Doctor");
            jButtonAdd.setBounds(55, 400, 150, 30);

            // crearea listei
            JList<Doctor> jList = new JList<>();
            jList.setBounds(300, 100, 400, 200);
            jList.setListData(getDoctorsAsArray());

            jButtonAdd.addActionListener(addActionListener(nameTextField, roomTextField, jList));

            // delete
            JButton deletePersonButton = new JButton("Delete Doctor");
            deletePersonButton.setBounds(400, 400, 200, 30);
            deletePersonButton.addActionListener(deletePersonActionListener(jList));

            // back
            JButton backButton = new JButton("Back");
            backButton.addActionListener(goBackActionListener());
            backButton.setBounds(10, 10, 100, 30);
            jFrame.add(backButton);

            // adaugam ce am creat
            jFrame.add(nameLabel);
            jFrame.add(nameTextField);
            jFrame.add(roomLabel);
            jFrame.add(roomTextField);
            jFrame.add(jButtonAdd);
            jFrame.add(deletePersonButton);
            jFrame.add(jList);

            jFrame.setBounds(200, 200, 1000, 500);
            jFrame.setLayout(null);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }


    private static ActionListener addActionListener(JTextField doctorNameField, JTextField doctorRoomField, JList jList) {
        ActionListener actionListener = action -> {
            String name = doctorNameField.getText();
            String room = doctorRoomField.getText();

            Doctor doctor = new Doctor(name, room);
            try {
                doctorsRepository.addDoctor(doctor);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jList.setListData(getDoctorsAsArray());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Person was successfully added!!!");
        };
        return actionListener;
    }

    private static ActionListener deletePersonActionListener(JList<Doctor> jList) {
        ActionListener actionListener = actionEvent -> {
            try {
                doctorsRepository.deleteDoctor(jList.getSelectedValue().getEmployeeName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                jList.setListData(getDoctorsAsArray());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Person was deleted!!!");
        };
        return actionListener;
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

    private static Doctor[] getDoctorsAsArray() throws SQLException{
        return doctorsRepository.getPeople().toArray(new Doctor[0]);
    }
}
