import javax.swing.*;

public class EmployeeDashboard extends JFrame {

    User currentUser;

   JButton addCustomerBtn, showCustomerBtn, searchCustomerBtn, deleteCustomerBtn;
    JButton makeOrderBtn, cancelOrderBtn, showOrdersBtn;
    JButton billingBtn;
    JButton logoutBtn;
    JButton updateInfoBtn;
    JButton profileBtn;

    JTextArea output;

    public EmployeeDashboard(User user) {

        this.currentUser = user;

        setTitle("Employee Dashboard");
        setSize(730, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addCustomerBtn = new JButton("Add Customer");
        showCustomerBtn = new JButton("Show Customers");
        searchCustomerBtn = new JButton("Search Customer");
        deleteCustomerBtn = new JButton("Delete Customer");

        addCustomerBtn.setBounds(20, 20, 160, 35);

         showCustomerBtn.setBounds(190, 20, 160, 35);

       searchCustomerBtn.setBounds(360, 20, 160, 35);

        deleteCustomerBtn.setBounds(530, 20, 160, 35);

        makeOrderBtn = new JButton("Make Order");
        cancelOrderBtn = new JButton("Cancel Order");
        showOrdersBtn = new JButton("Show Orders");
         billingBtn = new JButton("Billing");

        makeOrderBtn.setBounds(20, 70, 160, 35);
        cancelOrderBtn.setBounds(190, 70, 160, 35);
        showOrdersBtn.setBounds(360, 70, 160, 35);
        billingBtn.setBounds(530, 70, 160, 35);
        
        

        updateInfoBtn = new JButton("Update Info");
        logoutBtn = new JButton("Logout");
        profileBtn = new JButton("Customer Profile");
        profileBtn.setBounds(20, 120, 160, 35);
        updateInfoBtn.setBounds(190, 120, 160, 35);
        logoutBtn.setBounds(360, 120, 160, 35);
        

        output = new JTextArea();
        output.setBounds(20, 180, 670, 250);

        add(addCustomerBtn);
        add(showCustomerBtn);
        add(searchCustomerBtn);
        add(deleteCustomerBtn);
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
        
        
        // ================= DELETE CUSTOMER =================
deleteCustomerBtn.addActionListener(e -> {

    Integer id = InputValidator.getInt(this, "Enter Customer ID:");

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

    // 🔥 حذف كل أوردرات العميل
    DataStore.orders.removeIf(o -> o.customer.id == id);

    // 🔥 حذف العميل
    DataStore.customers.remove(customer);

    FileManager.saveAll();

    output.setText("✔ Customer Deleted");
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
            order.checkout();

            DataStore.orders.add(order);
            FileManager.saveAll();

            output.setText("✔ Order Created | Total: " + order.totalPrice);
        });

       

        // ================= SHOW ORDERS =================
        showOrdersBtn.addActionListener(e -> {

            String data = "";

            for (Order o : DataStore.orders) {
                data += "Order " + o.id +
        " | Customer: " + o.customer.name +
        " (ID: " + o.customer.id + ")" +
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
                            "Orders:\n" + c.getOrdersInfo()+ "\n" +
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
        // ================= Cancel =================
        cancelOrderBtn.addActionListener(e -> {
       

    try {
        // 1️⃣ يدخل Customer ID
        int custId = Integer.parseInt(
            JOptionPane.showInputDialog("Enter Customer ID")
        );

        // 2️⃣ نجيب العميل
        Customer customer = null;

        for (Customer c : DataStore.customers) {
            if (c.id == custId) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Customer not found");
            return;
        }

        // 3️⃣ عرض Orders
        String ordersInfo = customer.getOrdersInfo();

        if (ordersInfo == null || ordersInfo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders found");
            return;
        }

        // 4️⃣ عرضهم
        JOptionPane.showMessageDialog(this, ordersInfo);

        // 5️⃣ يدخل Order ID
        int orderId = Integer.parseInt(
            JOptionPane.showInputDialog("Enter Order ID to cancel")
        );

        // 6️⃣ حذف
        boolean removed = customer.cancelOrder(orderId);

if (removed) {
    DataStore.orders.removeIf(o -> o.id == orderId);
    FileManager.saveAll();

    JOptionPane.showMessageDialog(this, "✔ Order cancelled successfully");
} else {
    JOptionPane.showMessageDialog(this, "❌ Order not found");
}

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Invalid input");
    }
});
        
        
        
        
        

        // ================= LOGOUT =================
        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }
}


