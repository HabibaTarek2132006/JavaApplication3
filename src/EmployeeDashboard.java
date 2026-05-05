import javax.swing.*;

public class EmployeeDashboard extends JFrame {

    User currentUser;

    JButton addCustomerBtn, showCustomerBtn, searchCustomerBtn;
    JButton makeOrderBtn, cancelOrderBtn, showOrdersBtn;
    JButton billingBtn;
    JButton logoutBtn;
    JButton updateInfoBtn;
    JButton profileBtn;

    JTextArea output;

    public EmployeeDashboard(User user) {

        this.currentUser = user;

        setTitle("Employee Dashboard");
        setSize(650, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addCustomerBtn = new JButton("Add Customer");
        showCustomerBtn = new JButton("Show Customers");
        searchCustomerBtn = new JButton("Search Customer");

        addCustomerBtn.setBounds(20, 20, 150, 30);
        showCustomerBtn.setBounds(180, 20, 150, 30);
        searchCustomerBtn.setBounds(340, 20, 150, 30);

        makeOrderBtn = new JButton("Make Order");
        cancelOrderBtn = new JButton("Cancel Order");
        showOrdersBtn = new JButton("Show Orders");

        makeOrderBtn.setBounds(20, 60, 150, 30);
        cancelOrderBtn.setBounds(180, 60, 150, 30);
        showOrdersBtn.setBounds(340, 60, 150, 30);

        billingBtn = new JButton("Billing");
        billingBtn.setBounds(20, 100, 150, 30);

        updateInfoBtn = new JButton("Update Info");
        updateInfoBtn.setBounds(180, 100, 150, 30);

        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(340, 100, 150, 30);

        profileBtn = new JButton("Customer Profile");
        profileBtn.setBounds(500, 100, 150, 30);

        output = new JTextArea();
        output.setBounds(20, 150, 590, 280);

        add(addCustomerBtn);
        add(showCustomerBtn);
        add(searchCustomerBtn);
        add(makeOrderBtn);
        add(cancelOrderBtn);
        add(showOrdersBtn);
        add(billingBtn);
        add(updateInfoBtn);
        add(logoutBtn);
        add(profileBtn);
        add(output);

        // ================= ADD CUSTOMER =================
        addCustomerBtn.addActionListener(e -> {

            String name = InputValidator.getNameOnly(this, "Customer Name:");
            if (name == null) return;

            Customer c = new Customer(DataStore.customers.size() + 1, name);
            DataStore.customers.add(c);
            FileManager.saveAll();

            output.setText("✔ Customer Added");
        });

        // ================= SHOW =================
        showCustomerBtn.addActionListener(e -> {

            String data = "";

            for (Customer c : DataStore.customers) {
                data += c.id + " - " + c.name +
                        " | Payments: " + c.totalPayments +
                        " | Points: " + c.loyaltyPoints + "\n";
            }

            output.setText(data);
        });

        // ================= SEARCH =================
        searchCustomerBtn.addActionListener(e -> {

            String name = InputValidator.getNameOnly(this, "Search Name:");
            if (name == null) return;

            for (Customer c : DataStore.customers) {
                if (c.name.equalsIgnoreCase(name)) {
                    output.setText("✔ Found: " + c.name);
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        // ================= MAKE ORDER =================
        makeOrderBtn.addActionListener(e -> {

            Integer customerId = InputValidator.getInt(this, "Customer ID:");
            if (customerId == null) return;

            Customer customer = null;

            for (Customer c : DataStore.customers) {
                if (c.id == customerId) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                output.setText("❌ Customer Not Found");
                return;
            }

            String mealsText = "";

            for (Meal m : DataStore.meals) {
                mealsText += m.id + " - " + m.name + " - " + m.price + "\n";
            }

            JOptionPane.showMessageDialog(this, mealsText);

            Integer mealId = InputValidator.getInt(this, "Enter Meal ID:");
            if (mealId == null) return;

            Meal selectedMeal = null;

            for (Meal m : DataStore.meals) {
                if (m.id == mealId) {
                    selectedMeal = m;
                    break;
                }
            }

            if (selectedMeal == null) {
                output.setText("❌ Meal Not Found");
                return;
            }

            Order order = new Order(customer);
            order.meals.add(selectedMeal);
            order.calculateTotal();

            DataStore.orders.add(order);
            FileManager.saveAll();

            output.setText("✔ Order Created | Total: " + order.totalPrice);
        });

        // ================= CANCEL =================
        cancelOrderBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "Order ID:");
            if (id == null) return;

            DataStore.orders.removeIf(o -> o.id == id);
            FileManager.saveAll();

            output.setText("✔ Order Cancelled");
        });

        // ================= SHOW ORDERS =================
        showOrdersBtn.addActionListener(e -> {

            String data = "";

            for (Order o : DataStore.orders) {
                data += "Order " + o.id +
                        " | Customer: " + o.customer.name +
                        " | Total: " + o.totalPrice + "\n";
            }

            output.setText(data);
        });

        // ================= BILLING =================
        billingBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            Customer customer = null;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                output.setText("❌ Customer Not Found");
                return;
            }

            output.setText(
                    "Payments: " + customer.totalPayments +
                    "\nPoints: " + customer.loyaltyPoints +
                    "\nGifts: " + customer.gifts
            );
        });

        // ================= PROFILE =================
        profileBtn.addActionListener(e -> {

            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {

                    output.setText(
                            "=== CUSTOMER PROFILE ===\n" +
                            "Name: " + c.name + "\n" +
                            "ID: " + c.id + "\n\n" +
                            "Payments: " + c.totalPayments + "\n" +
                            "Points: " + c.loyaltyPoints + "\n\n" +
                            "Orders: " + c.orders + "\n" +
                            "Gifts: " + c.gifts + "\n" +
                            "Offers: " + c.offers + "\n"
                    );
                    return;
                }
            }

            output.setText("❌ Not Found");
        });

        // ================= UPDATE =================
        updateInfoBtn.addActionListener(e -> {

            String name = InputValidator.getNameOnly(this, "New Name:");
            String username = InputValidator.getText(this, "New Username:");
            String password = InputValidator.getText(this, "New Password:");

            if (name == null || username == null || password == null) return;

            currentUser.name = name;
            currentUser.username = username;
            currentUser.password = password;

            for (Employee emp : DataStore.employees) {
                if (emp.id == currentUser.id) {
                    emp.name = name;
                    emp.username = username;
                    emp.password = password;
                    break;
                }
            }

            FileManager.saveAll();

            output.setText("✔ Updated Successfully");
        });

        // ================= LOGOUT =================
        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }
}