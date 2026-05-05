import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EmployeeDashboard extends JFrame {

    User currentUser;

    JButton addCustomerBtn, showCustomerBtn, searchCustomerBtn;
    JButton makeOrderBtn, cancelOrderBtn, showOrdersBtn;
    JButton billingBtn;
    JButton logoutBtn;
    JButton updateInfoBtn;

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
        add(output);

        // ================= CUSTOMER =================

        addCustomerBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Customer Name:");
            Customer c = new Customer(DataStore.customers.size() + 1, name);
            DataStore.customers.add(c);
            FileManager.saveAll();
            JOptionPane.showMessageDialog(this, "Customer Added");
        });

        showCustomerBtn.addActionListener(e -> {

            String data = "";

            for (Customer c : DataStore.customers) {
                data += c.id + " - " + c.name +
                        " | Payments: " + c.totalPayments +
                        " | Points: " + c.loyaltyPoints + "\n";
            }

            output.setText(data);
        });

        searchCustomerBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Search Name:");

            for (Customer c : DataStore.customers) {
                if (c.name.equalsIgnoreCase(name)) {
                    output.setText("Found: " + c.name);
                    return;
                }
            }

            output.setText("Not Found");
        });

        // ================= ORDERS =================

        makeOrderBtn.addActionListener(e -> {

            int customerId = Integer.parseInt(JOptionPane.showInputDialog("Customer ID"));

            Customer customer = null;

            for (Customer c : DataStore.customers) {
                if (c.id == customerId) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                JOptionPane.showMessageDialog(this, "Customer Not Found");
                return;
            }

            String mealsText = "";

            for (Meal m : DataStore.meals) {
                mealsText += m.id + " - " + m.name + " - " + m.price + "\n";
            }

            JOptionPane.showMessageDialog(this, mealsText);

            int mealId = Integer.parseInt(JOptionPane.showInputDialog("Enter Meal ID"));

            Meal selectedMeal = null;

            for (Meal m : DataStore.meals) {
                if (m.id == mealId) {
                    selectedMeal = m;
                    break;
                }
            }

            if (selectedMeal == null) {
                JOptionPane.showMessageDialog(this, "Meal Not Found");
                return;
            }

            Order order = new Order(customer);
            order.meals.add(selectedMeal);
            order.calculateTotal();

            DataStore.orders.add(order);

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this,
                    "Order Created ✔ Total: " + order.totalPrice);
        });

        cancelOrderBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("Order ID"));

            DataStore.orders.removeIf(o -> o.id == id);

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this, "Order Cancelled");
        });

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

            int customerId = Integer.parseInt(JOptionPane.showInputDialog("Customer ID"));

            Customer customer = null;

            for (Customer c : DataStore.customers) {
                if (c.id == customerId) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) return;

            JOptionPane.showMessageDialog(this,
                    "Total Payments = " + customer.totalPayments +
                            "\nPoints = " + customer.loyaltyPoints +
                            "\nGifts = " + customer.gifts);
        });

        // ================= UPDATE INFO (FIXED) =================

        updateInfoBtn.addActionListener(e -> {

            String newName = JOptionPane.showInputDialog("New Name:");
            String newUsername = JOptionPane.showInputDialog("New Username:");
            String newPassword = JOptionPane.showInputDialog("New Password:");

            if (newName == null || newUsername == null || newPassword == null) {
                return;
            }

            // 1) update current session
            currentUser.name = newName;
            currentUser.username = newUsername;
            currentUser.password = newPassword;

            // 2) IMPORTANT: update real DataStore (fix login problem)
            for (Employee emp : DataStore.employees) {
                if (emp.id == currentUser.id) {
                    emp.name = newName;
                    emp.username = newUsername;
                    emp.password = newPassword;
                    break;
                }
            }

            FileManager.saveAll();

            JOptionPane.showMessageDialog(this,
                    "Information Updated Successfully ✔");
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