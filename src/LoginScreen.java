import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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

        // ================= LOGIN =================
        loginBtn.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // ================= EMPLOYEE LOGIN =================
            for (Employee emp : DataStore.employees) {

                if (emp.username.equals(username) && emp.password.equals(password)) {

                    JOptionPane.showMessageDialog(this, "Welcome " + emp.name);

                    User user = new User(
                            emp.id,
                            emp.name,
                            emp.username,
                            emp.password,
                            Role.EMPLOYEE
                    );

                    new EmployeeDashboard(user).setVisible(true);
                    this.dispose();
                    return;
                }
            }

            // ================= ADMIN LOGIN =================
            if (username.equals("admin") && password.equals("1234")) {

                User admin = new User(
                        0,
                        "Admin",
                        "admin",
                        "1234",
                        Role.ADMIN
                );

                new AdminDashboard(admin).setVisible(true);
                this.dispose();
                return;
            }

            JOptionPane.showMessageDialog(this, "Invalid Login");
        });

        setVisible(true);
    }
}