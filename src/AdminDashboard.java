import javax.swing.*;

public class AdminDashboard extends JFrame {

    User currentUser;

    JButton addEmpBtn, showEmpBtn, deleteEmpBtn, updateEmpBtn, searchEmpBtn;
    JButton addMealBtn, showMealBtn, updateMealBtn, searchMealBtn;
    JButton addOfferBtn, employeeReportBtn, mealReportBtn;
    JButton updateAdminInfoBtn;
    JButton logoutBtn;

    JTextArea output;

    public AdminDashboard(User user) {

        this.currentUser = user;

        setTitle("Admin Dashboard");
        setSize(700, 550);
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

        // ================= ADMIN INFO =================
        updateAdminInfoBtn = new JButton("Update Admin Info");
        updateAdminInfoBtn.setBounds(410, 100, 160, 30);

        // ================= LOGOUT =================
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(540, 100, 120, 30);

        // ================= OUTPUT =================
        output = new JTextArea();
        output.setBounds(20, 150, 640, 350);

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

        add(updateAdminInfoBtn);
        add(logoutBtn);

        add(output);

        // ================= EMPLOYEES =================

        addEmpBtn.addActionListener(e -> {

            String name = InputValidator.getNameOnly(this, "Name:");
            String username = InputValidator.getText(this, "Username:");
            String password = InputValidator.getText(this, "Password:");

            if (name == null || username == null || password == null) return;

            DataStore.employees.add(new Employee(
                    DataStore.employees.size() + 1,
                    name, username, password
            ));

            FileManager.saveAll();
            output.setText("✔ Employee Added");
        });

        showEmpBtn.addActionListener(e -> {

            String text = "";

            for (Employee emp : DataStore.employees) {
                text += emp.id + " - " + emp.name + " - " + emp.username + "\n";
            }

            output.setText(text);
        });

        deleteEmpBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "ID:");
            if (id == null) return;

            DataStore.employees.removeIf(emp -> emp.id == id);
            FileManager.saveAll();

            output.setText("✔ Deleted");
        });

        updateEmpBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "ID:");
            if (id == null) return;

            for (Employee emp : DataStore.employees) {
                if (emp.id == id) {

                    String name = InputValidator.getNameOnly(this, "New Name:");
                    String username = InputValidator.getText(this, "New Username:");
                    String password = InputValidator.getText(this, "New Password:");

                    if (name == null || username == null || password == null) return;

                    emp.name = name;
                    emp.username = username;
                    emp.password = password;

                    FileManager.saveAll();
                    output.setText("✔ Updated");
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        searchEmpBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "Search ID:");
            if (id == null) return;

            for (Employee emp : DataStore.employees) {
                if (emp.id == id) {
                    output.setText(emp.id + " - " + emp.name + " - " + emp.username);
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        // ================= MEALS =================

        addMealBtn.addActionListener(e -> {

            String name = InputValidator.getText(this, "Meal:");
            Double price = InputValidator.getDouble(this, "Price:");

            if (name == null || price == null) return;

            DataStore.meals.add(new Meal(
                    DataStore.meals.size() + 1,
                    name,
                    price
            ));

            FileManager.saveAll();
            output.setText("✔ Meal Added");
        });

        showMealBtn.addActionListener(e -> {

            String text = "";

            for (Meal m : DataStore.meals) {
                text += m.id + " - " + m.name + " - " + m.price + "\n";
            }

            output.setText(text);
        });

        updateMealBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "ID:");
            if (id == null) return;

            for (Meal m : DataStore.meals) {
                if (m.id == id) {

                    String name = InputValidator.getText(this, "New Name:");
                    Double price = InputValidator.getDouble(this, "New Price:");

                    if (name == null || price == null) return;

                    m.name = name;
                    m.price = price;

                    FileManager.saveAll();
                    output.setText("✔ Updated");
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        searchMealBtn.addActionListener(e -> {

            String name = InputValidator.getText(this, "Search Meal:");
            if (name == null) return;

            for (Meal m : DataStore.meals) {
                if (m.name.equalsIgnoreCase(name)) {
                    output.setText(m.id + " - " + m.name + " - " + m.price);
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        // ================= OFFERS =================

        addOfferBtn.addActionListener(e -> {

            String offer = InputValidator.getText(this, "Offer:");
            if (offer == null) return;

            DataStore.offers.add(offer);

            for (Customer c : DataStore.customers) {
                c.addOffer(offer);
            }

            FileManager.saveAll();
            output.setText("✔ Offer Added");
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

        // ================= ADMIN UPDATE =================

        updateAdminInfoBtn.addActionListener(e -> {

            String name = InputValidator.getNameOnly(this, "New Name:");
            String username = InputValidator.getText(this, "New Username:");
            String password = InputValidator.getText(this, "New Password:");

            if (name == null || username == null || password == null) return;

            currentUser.name = name;
            currentUser.username = username;
            currentUser.password = password;

            FileManager.saveAll();

            output.setText("✔ Admin Updated");
        });

        // ================= LOGOUT =================

        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }
}