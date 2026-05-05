import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AdminDashboard extends JFrame {

    User currentUser;

    JButton addEmpBtn, showEmpBtn, deleteEmpBtn, updateEmpBtn, searchEmpBtn;
    JButton addMealBtn, showMealBtn, updateMealBtn, searchMealBtn;
    JButton addOfferBtn, employeeReportBtn, mealReportBtn;
    JButton logoutBtn;

    JTextArea output;

    public AdminDashboard(User user) {

        this.currentUser = user;

        setTitle("Admin Dashboard");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= EMPLOYEES =================
        addEmpBtn = new JButton("Add Emp");
        showEmpBtn = new JButton("Show Emp");
        deleteEmpBtn = new JButton("Delete Emp");
        updateEmpBtn = new JButton("Update Emp");
        searchEmpBtn = new JButton("Search Emp");

        addEmpBtn.setBounds(20, 20, 120, 30);
        showEmpBtn.setBounds(150, 20, 120, 30);
        deleteEmpBtn.setBounds(280, 20, 120, 30);
        updateEmpBtn.setBounds(410, 20, 120, 30);
        searchEmpBtn.setBounds(540, 20, 120, 30);

        // ================= MEALS =================
        addMealBtn = new JButton("Add Meal");
        showMealBtn = new JButton("Show Meals");
        updateMealBtn = new JButton("Update Meal");
        searchMealBtn = new JButton("Search Meal");

        addMealBtn.setBounds(20, 60, 120, 30);
        showMealBtn.setBounds(150, 60, 120, 30);
        updateMealBtn.setBounds(280, 60, 120, 30);
        searchMealBtn.setBounds(410, 60, 120, 30);

        // ================= OFFERS =================
        addOfferBtn = new JButton("Add Offer");
        employeeReportBtn = new JButton("Emp Report");
        mealReportBtn = new JButton("Meal Report");

        addOfferBtn.setBounds(20, 100, 120, 30);
        employeeReportBtn.setBounds(150, 100, 120, 30);
        mealReportBtn.setBounds(280, 100, 120, 30);

        // ================= LOGOUT =================
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(540, 100, 120, 30);

        // ================= OUTPUT =================
        output = new JTextArea();
        output.setBounds(20, 150, 640, 300);
        add(output);

        // ================= ADD =================
        add(addEmpBtn);
        add(showEmpBtn);
        add(deleteEmpBtn);
        add(updateEmpBtn);
        add(searchEmpBtn);

        add(addMealBtn);
        add(showMealBtn);
        add(updateMealBtn);
        add(searchMealBtn);

        add(addOfferBtn);
        add(employeeReportBtn);
        add(mealReportBtn);
        add(logoutBtn);

        // ================= EMPLOYEES =================

        addEmpBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Name:");
            String username = JOptionPane.showInputDialog("Username:");
            String password = JOptionPane.showInputDialog("Password:");

            Employee emp = new Employee(
                    DataStore.employees.size() + 1,
                    name, username, password
            );

            DataStore.employees.add(emp);
            FileManager.saveAll();

            JOptionPane.showMessageDialog(this, "Employee Added");
        });

        showEmpBtn.addActionListener(e -> {

            String text = "";

            for (Employee emp : DataStore.employees) {
                text += emp.id + " - " + emp.name + " - " + emp.username + "\n";
            }

            output.setText(text);
        });

        deleteEmpBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
            DataStore.employees.removeIf(emp -> emp.id == id);

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this, "Deleted");
        });

        updateEmpBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));

            for (Employee emp : DataStore.employees) {
                if (emp.id == id) {

                    emp.name = JOptionPane.showInputDialog("New Name:");
                    emp.username = JOptionPane.showInputDialog("New Username:");
                    emp.password = JOptionPane.showInputDialog("New Password:");

                    FileManager.saveAll();

                    JOptionPane.showMessageDialog(this, "Updated");
                    return;
                }
            }
        });

        searchEmpBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("Search ID:"));

            for (Employee emp : DataStore.employees) {
                if (emp.id == id) {
                    output.setText(emp.id + " - " + emp.name + " - " + emp.username);
                    return;
                }
            }

            output.setText("Not Found");
        });

        // ================= MEALS =================

        addMealBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Meal:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Price:"));

            DataStore.meals.add(new Meal(DataStore.meals.size() + 1, name, price));

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this, "Meal Added");
        });

        showMealBtn.addActionListener(e -> {

            String text = "";

            for (Meal m : DataStore.meals) {
                text += m.id + " - " + m.name + " - " + m.price + "\n";
            }

            output.setText(text);
        });

        updateMealBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));

            for (Meal m : DataStore.meals) {
                if (m.id == id) {

                    m.name = JOptionPane.showInputDialog("New Name:");
                    m.price = Double.parseDouble(JOptionPane.showInputDialog("New Price:"));

                    FileManager.saveAll();

                    JOptionPane.showMessageDialog(this, "Updated");
                    return;
                }
            }
        });

        searchMealBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Search Meal:");

            for (Meal m : DataStore.meals) {
                if (m.name.equalsIgnoreCase(name)) {
                    output.setText(m.id + " - " + m.name + " - " + m.price);
                    return;
                }
            }

            output.setText("Not Found");
        });

        // ================= OFFERS =================

        addOfferBtn.addActionListener(e -> {

            String offer = JOptionPane.showInputDialog("Offer:");

            DataStore.offers.add(offer);

            for (Customer c : DataStore.customers) {
                c.addOffer(offer);
            }

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this, "Offer Added");
        });

        // ================= REPORTS =================

        employeeReportBtn.addActionListener(e -> {

            String report = "Employees Report:\n";

            for (Employee emp : DataStore.employees) {
                report += emp.id + " - " + emp.name + "\n";
            }

            output.setText(report);
        });

        mealReportBtn.addActionListener(e -> {

            String report = "Meals Report:\n";

            for (Meal m : DataStore.meals) {
                report += m.id + " - " + m.name + " - " + m.price + "\n";
            }

            output.setText(report);
        });

        // ================= LOGOUT =================

        logoutBtn.addActionListener(e -> {

            JOptionPane.showMessageDialog(this,
                    currentUser.name + " logged out successfully");

            new LoginScreen().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }
}