import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    User currentUser;

    JButton addEmpBtn, showEmpBtn, deleteEmpBtn, updateEmpBtn, searchEmpBtn;
    JButton addMealBtn, showMealBtn, updateMealBtn, searchMealBtn;
    JButton addOfferBtn, employeeReportBtn, mealReportBtn;
    JButton updateAdminInfoBtn, logoutBtn;
    JButton setMarketingBtn, setLoyaltyBtn, setRewardBtn, showProgramsBtn;


    JButton addOfferBtn, employeeReportBtn, mealReportBtn; JButton deleteMealBtn, customerReportBtn;
    JButton updateAdminInfoBtn;
    JButton logoutBtn;
 
    // ✅ أزرار البرامج الجديدة
    JButton enableMarketingBtn, enableLoyaltyBtn, enableRewardBtn, showProgramsBtn;
 

    JTextArea output;

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder());
    }

    public AdminDashboard(User user) {

        this.currentUser = user;

        setTitle("Admin Dashboard");
        setSize(700, 620);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 40));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Admin Dashboard");
        title.setBounds(250, 0, 300, 35);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);

        // ================= EMPLOYEES =================
        addEmpBtn    = new JButton("Add Emp");
        showEmpBtn   = new JButton("Show Emp");
        deleteEmpBtn = new JButton("Delete Emp");
        updateEmpBtn = new JButton("Update Emp");
        searchEmpBtn = new JButton("Search Emp");

        addEmpBtn.setBounds(20, 40, 120, 30);
        showEmpBtn.setBounds(150, 40, 120, 30);
        deleteEmpBtn.setBounds(280, 40, 120, 30);
        updateEmpBtn.setBounds(410, 40, 120, 30);
        searchEmpBtn.setBounds(540, 40, 120, 30);

        // ================= MEALS =================
        addMealBtn    = new JButton("Add Meal");
        showMealBtn   = new JButton("Show Meals");
        updateMealBtn = new JButton("Update Meal");
        searchMealBtn = new JButton("Search Meal");

        deleteMealBtn = new JButton("Delete Meal");
         customerReportBtn = new JButton("Customer Report");
 
        addMealBtn.setBounds(20, 80, 120, 30);
        showMealBtn.setBounds(150, 80, 120, 30);
        updateMealBtn.setBounds(280, 80, 120, 30);
        searchMealBtn.setBounds(410, 80, 120, 30);


        deleteMealBtn.setBounds(540, 80, 120, 30);
        customerReportBtn.setBounds(440, 120, 120, 30);
 
        // ================= OFFERS / REPORTS =================
        addOfferBtn       = new JButton("Add Offer");
        employeeReportBtn = new JButton("Emp Report");
        mealReportBtn     = new JButton("Meal Report");

        addOfferBtn.setBounds(20, 120, 120, 30);
        employeeReportBtn.setBounds(150, 120, 120, 30);
        mealReportBtn.setBounds(280, 120, 120, 30);

        // ================= ADMIN INFO / LOGOUT =================
        updateAdminInfoBtn = new JButton("Update Admin Info");
        logoutBtn          = new JButton("Logout");

        updateAdminInfoBtn.setBounds(410, 120, 150, 30);
        logoutBtn.setBounds(570, 120, 100, 30);

        // ================= PROGRAMS =================
        setMarketingBtn = new JButton("Marketing % 🏷");
        setLoyaltyBtn   = new JButton("Loyalty Pts ⭐");
        setRewardBtn    = new JButton("Set Reward 🎁");
        showProgramsBtn = new JButton("Show Programs");

        setMarketingBtn.setBounds(20, 160, 150, 30);
        setLoyaltyBtn.setBounds(180, 160, 150, 30);
        setRewardBtn.setBounds(340, 160, 150, 30);
        showProgramsBtn.setBounds(500, 160, 150, 30);

        // ================= OUTPUT =================
        output = new JTextArea();
        output.setBackground(new Color(45, 45, 60));
        output.setForeground(Color.WHITE);
        output.setFont(new Font("Consolas", Font.PLAIN, 15));
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBounds(20, 205, 645, 370);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(45, 45, 60));

        // ===== تنسيق =====
        styleButton(addEmpBtn);    styleButton(showEmpBtn);
        styleButton(deleteEmpBtn); styleButton(updateEmpBtn); styleButton(searchEmpBtn);
        styleButton(addMealBtn);   styleButton(showMealBtn);
        styleButton(updateMealBtn);styleButton(searchMealBtn);
        styleButton(addOfferBtn);  styleButton(employeeReportBtn); styleButton(mealReportBtn);
        styleButton(updateAdminInfoBtn); styleButton(logoutBtn);
        styleButton(setMarketingBtn); styleButton(setLoyaltyBtn);
        styleButton(setRewardBtn);    styleButton(showProgramsBtn);

        // ===== إضافة =====
        add(addEmpBtn); add(showEmpBtn); add(deleteEmpBtn); add(updateEmpBtn); add(searchEmpBtn);
        add(addMealBtn); add(showMealBtn); add(updateMealBtn); add(searchMealBtn);
        add(addOfferBtn); add(employeeReportBtn); add(mealReportBtn);
        add(updateAdminInfoBtn); add(logoutBtn);
        add(setMarketingBtn); add(setLoyaltyBtn); add(setRewardBtn); add(showProgramsBtn);
        add(scrollPane);

        output.setBounds(20, 205, 645, 370);
 
        // ===== تنسيق كل الأزرار =====
        styleButton(addEmpBtn);
        styleButton(showEmpBtn);
        styleButton(deleteEmpBtn);
        styleButton(updateEmpBtn);
        styleButton(searchEmpBtn);
 
        styleButton(addMealBtn);
        styleButton(showMealBtn);
        styleButton(updateMealBtn);
        styleButton(searchMealBtn);
        styleButton(deleteMealBtn);
        styleButton(customerReportBtn);
 
        styleButton(addOfferBtn);
        styleButton(employeeReportBtn);
        styleButton(mealReportBtn);
 
        styleButton(updateAdminInfoBtn);
        styleButton(logoutBtn);
 
        styleButton(enableMarketingBtn); // ✅
        styleButton(enableLoyaltyBtn);   // ✅
        styleButton(enableRewardBtn);    // ✅
        styleButton(showProgramsBtn);    // ✅
 
        // ===== إضافة كل الأزرار للـ Frame =====
        add(addEmpBtn);
        add(showEmpBtn);
        add(deleteEmpBtn);
        add(updateEmpBtn);
        add(searchEmpBtn);
 
        add(addMealBtn);
        add(showMealBtn);
        add(updateMealBtn);
        add(searchMealBtn);
        add(deleteMealBtn);
        add(customerReportBtn);
 
        add(addOfferBtn);
        add(employeeReportBtn);
        add(mealReportBtn);
 
        add(updateAdminInfoBtn);
        add(logoutBtn);
 
        add(enableMarketingBtn); // ✅
        add(enableLoyaltyBtn);   // ✅
        add(enableRewardBtn);    // ✅
        add(showProgramsBtn);    // ✅
 
        add(output);
 
        // ================= EMPLOYEES ACTIONS =================

        addEmpBtn.addActionListener(e -> {
            String name     = InputValidator.getNameOnly(this, "Name:");
            String username = InputValidator.getText(this, "Username:");
            String password = InputValidator.getText(this, "Password:");
            if (name == null || username == null || password == null) return;
            DataStore.employees.add(new Employee(DataStore.employees.size() + 1, name, username, password));
            FileManager.saveAll();
            output.setText("✔ Employee Added");
        });

        showEmpBtn.addActionListener(e -> {
            String text = "";
            for (Employee emp : DataStore.employees)
                text += emp.id + " - " + emp.name + " - " + emp.username + "\n";
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
                    String name     = InputValidator.getNameOnly(this, "New Name:");
                    String username = InputValidator.getText(this, "New Username:");
                    String password = InputValidator.getText(this, "New Password:");
                    if (name == null || username == null || password == null) return;
                    emp.name = name; emp.username = username; emp.password = password;
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

        // ================= MEALS ACTIONS =================

        addMealBtn.addActionListener(e -> {
            String name  = InputValidator.getText(this, "Meal:");
            Double price = InputValidator.getDouble(this, "Price:");
            if (name == null || price == null) return;
            DataStore.meals.add(new Meal(DataStore.meals.size() + 1, name, price));
            FileManager.saveAll();
            output.setText("✔ Meal Added");
        });

        showMealBtn.addActionListener(e -> {
            String text = "";
            for (Meal m : DataStore.meals)
                text += m.id + " - " + m.name + " - " + m.price + "\n";
            output.setText(text);
        });

        updateMealBtn.addActionListener(e -> {
            Integer id = InputValidator.getInt(this, "ID:");
            if (id == null) return;
            for (Meal m : DataStore.meals) {
                if (m.id == id) {
                    String name  = InputValidator.getText(this, "New Name:");
                    Double price = InputValidator.getDouble(this, "New Price:");
                    if (name == null || price == null) return;
                    m.name = name; m.price = price;
                    FileManager.saveAll();
                    output.setText("✔ Updated");
                    return;
                }
            }
            output.setText("❌ Not Found");
        });

        deleteMealBtn.addActionListener(e -> {

    Integer id = InputValidator.getInt(this, "Meal ID:");
    if (id == null) return;

    DataStore.meals.removeIf(m -> m.id == id);
    FileManager.saveAll();

    output.setText("✔ Meal Deleted");
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
            for (Customer c : DataStore.customers) c.addOffer(offer);
            FileManager.saveAll();
            output.setText("✔ Offer Added & Sent to All Customers");
        });

        // ================= REPORTS =================

        employeeReportBtn.addActionListener(e -> {
            String report = "Employees Report:\n";
            for (Employee emp : DataStore.employees)
                report += emp.id + " - " + emp.name + "\n";
            output.setText(report);
        });
            for (Meal m : DataStore.meals)
                report += m.id + " - " + m.name + " - " + m.price + "\n";
            output.setText(report);
        });

        // ================= ADMIN UPDATE =================

        updateAdminInfoBtn.addActionListener(e -> {
            String name     = InputValidator.getNameOnly(this, "New Name:");
            String username = InputValidator.getText(this, "New Username:");
            String password = InputValidator.getText(this, "New Password:");
            if (name == null || username == null || password == null) return;
            currentUser.name = name; currentUser.username = username; currentUser.password = password;
            FileManager.saveAll();
            output.setText("✔ Admin Updated");
        });

        // ================= LOGOUT =================

        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });

        // ================= SET MARKETING % =================
        setMarketingBtn.addActionListener(e -> {
            String input = InputValidator.getText(this, "Marketing Discount %:\n(e.g. 10 for 10%, 50 for 50%)");
            if (input == null) return;
            try {
                double discount = Double.parseDouble(input.trim());
                if (discount <= 0 || discount > 100) {
                    output.setText("❌ Enter a number between 1 and 100");
                    return;
                }
                DataStore.marketingDiscount = discount;
                FileManager.saveAll();
                output.setText(
                    "✔ Marketing Program Set!\n" +
                    "Discount: " + (int)discount + "% on every order\n\n" +
                    "Example:\n" +
                    "  Order 100  →  After discount: " + (100 - discount) + "\n" +
                    "  Order 200  →  After discount: " + (200 - 200 * discount / 100)
                );
            } catch (NumberFormatException ex) {
                output.setText("❌ Invalid number");
            }
        });

        // ================= SET LOYALTY BONUS POINTS =================
        setLoyaltyBtn.addActionListener(e -> {
            String input = InputValidator.getText(this, "Loyalty Bonus Points per order:\n(e.g. 5 means +5 extra points per order)");
            if (input == null) return;
            try {
                int bonus = Integer.parseInt(input.trim());
                if (bonus <= 0) {
                    output.setText("❌ Enter a positive number");
                    return;
                }
                DataStore.loyaltyBonusPoints = bonus;
                FileManager.saveAll();
                output.setText(
                    "✔ Loyalty Program Set!\n" +
                    "Bonus: +" + bonus + " extra points per order\n\n" +
                    "Example (order = 100):\n" +
                    "  Base Points  : " + (100 / 10) + "\n" +
                    "  Bonus Points : +" + bonus + "\n" +
                    "  Total Points : " + (100 / 10 + bonus)
                );
            } catch (NumberFormatException ex) {
                output.setText("❌ Invalid number");
            }
        });

        // ================= SET REWARD =================
        setRewardBtn.addActionListener(e -> {
            String reward = InputValidator.getText(this, "Reward Program Offer:\n(e.g. Free Dessert every 5 orders)");
            if (reward == null || reward.trim().isEmpty()) return;
            DataStore.rewardReward = reward.trim();
            FileManager.saveAll();
            output.setText("✔ Reward Program Set!\nOffer: " + DataStore.rewardReward);
        });

        // ================= SHOW PROGRAMS =================
        showProgramsBtn.addActionListener(e -> {
            String text =
                "=== Programs Status ===\n\n" +
                "🏷 Marketing : " + (DataStore.marketingDiscount > 0
                    ? (int)DataStore.marketingDiscount + "% Discount per order"
                    : "Not Set") + "\n" +
                "⭐ Loyalty   : " + (DataStore.loyaltyBonusPoints > 0
                    ? "+" + DataStore.loyaltyBonusPoints + " Bonus Points per order"
                    : "Not Set") + "\n" +
                "🎁 Reward    : " + (DataStore.rewardReward.isEmpty()
                    ? "Not Set"
                    : DataStore.rewardReward) + "\n\n" +
                "=== Customers Subscriptions ===\n";

            for (Customer c : DataStore.customers) {
                text += c.name + " → " +
                    "Marketing: " + (c.marketingProgram ? "✔" : "✘") +
                    " | Loyalty: " + (c.loyaltyProgram  ? "✔" : "✘") +
                    " | Reward: "  + (c.rewardProgram   ? "✔" : "✘") + "\n";
            }

            output.setText(text);
            output.setCaretPosition(0);
        });

        setVisible(true);
    }
}
