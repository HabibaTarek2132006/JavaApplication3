/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 */
import javax.swing.*;

public class LoginScreen extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn;

    public LoginScreen() {

        setTitle("Login Screen");
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginBtn = new JButton("Login");

        usernameField.setBounds(80, 40, 180, 30);
        passwordField.setBounds(80, 90, 180, 30);
        loginBtn.setBounds(80, 140, 180, 35);

        add(usernameField);
        add(passwordField);
        add(loginBtn);

        // ✅ هنا المكان الصح
        loginBtn.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            for (Employee emp : DataStore.employees) {

                if (emp.username.equals(username) && emp.password.equals(password)) {

                    JOptionPane.showMessageDialog(this, "Welcome " + emp.name);

                    new EmployeeDashboard().setVisible(true);
                    dispose();
                    return;
                }
            }

            if (username.equals("admin") && password.equals("1234")) {

                new AdminDashboard().setVisible(true);
                dispose();
            }

            else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });
    }
}