package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MainForm extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
		/* It posts an event (Runnable) at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm frame = new MainForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainForm() {
        setTitle("Hospital App");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel welcomeMessageLabel = new JLabel("Welcome!");
        welcomeMessageLabel.setBounds(400, 10, 200, 30);
        welcomeMessageLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        contentPane.add(welcomeMessageLabel);

        JButton doctorsFrame = new JButton("Doctors");
        doctorsFrame.addActionListener(goToDoctorsActionListener());
        doctorsFrame.setBounds(30, 60, 500, 120);
        contentPane.add(doctorsFrame);

        JButton patientsFrame = new JButton("Patients");
        patientsFrame.addActionListener(goToPatientsActionListener());
        patientsFrame.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        patientsFrame.setBounds(30, 190, 500, 120);
        contentPane.add(patientsFrame);


    }

    private ActionListener goToDoctorsActionListener(){
        ActionListener actionListener = actionEvent -> {
            DoctorsForm frame = null;
            try {
                frame = new DoctorsForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        };
        return actionListener;
    }

    private ActionListener goToPatientsActionListener() {
        return actionEvent -> {
            PatientsForm frame = null;
            try {
                frame = new PatientsForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            frame.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        };
    }
}
