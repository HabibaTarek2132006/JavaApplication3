import javax.swing.*;
import java.awt.*;

public class EmployeeDashboard extends JFrame {

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder());
    }

    User currentUser;

    JButton addCustomerBtn, showCustomerBtn, searchCustomerBtn, deleteCustomerBtn;
    JButton makeOrderBtn, cancelOrderBtn, showOrdersBtn;
    JButton billingBtn;
    JButton logoutBtn;
    JButton updateInfoBtn;
    JButton profileBtn;
    JButton marketingBtn, loyaltyBtn, rewardBtn;

    JTextArea output;

    public EmployeeDashboard(User user) {

        this.currentUser = user;

        setTitle("Employee Dashboard");
        setSize(730, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 40));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Restaurant Management System");
        title.setBounds(180, 0, 500, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title);

        // ===== إنشاء الأزرار =====
        addCustomerBtn    = new JButton("Add Customer");
        showCustomerBtn   = new JButton("Show Customers");
        searchCustomerBtn = new JButton("Search Customer");
        deleteCustomerBtn = new JButton("Delete Customer");

        makeOrderBtn   = new JButton("Make Order");
        cancelOrderBtn = new JButton("Cancel Order");
        showOrdersBtn  = new JButton("Show Orders");
        billingBtn     = new JButton("Billing");

        profileBtn    = new JButton("Customer Profile");
        updateInfoBtn = new JButton("Update Info");
        logoutBtn     = new JButton("Logout");

        marketingBtn = new JButton("Marketing Program");
        loyaltyBtn   = new JButton("Loyalty Program");
        rewardBtn    = new JButton("Reward Program");

        // ===== تنسيق الأزرار =====
        styleButton(addCustomerBtn);
        styleButton(showCustomerBtn);
        styleButton(searchCustomerBtn);
        styleButton(deleteCustomerBtn);

        styleButton(makeOrderBtn);
        styleButton(cancelOrderBtn);
        styleButton(showOrdersBtn);
        styleButton(billingBtn);

        styleButton(profileBtn);
        styleButton(updateInfoBtn);
        styleButton(logoutBtn);

        styleButton(marketingBtn);
        styleButton(loyaltyBtn);
        styleButton(rewardBtn);

        // ===== ROW 1 =====
        addCustomerBtn.setBounds(20, 50, 160, 35);
        showCustomerBtn.setBounds(200, 50, 160, 35);
        searchCustomerBtn.setBounds(380, 50, 160, 35);
        deleteCustomerBtn.setBounds(560, 50, 160, 35);

        // ===== ROW 2 =====
        makeOrderBtn.setBounds(20, 100, 160, 35);
        cancelOrderBtn.setBounds(200, 100, 160, 35);
        showOrdersBtn.setBounds(380, 100, 160, 35);
        billingBtn.setBounds(560, 100, 160, 35);

        // ===== ROW 3 =====
        profileBtn.setBounds(20, 150, 160, 35);
        updateInfoBtn.setBounds(200, 150, 160, 35);
        logoutBtn.setBounds(380, 150, 160, 35);

        // ===== ROW 4 - Programs =====
        marketingBtn.setBounds(20, 200, 160, 35);
        loyaltyBtn.setBounds(200, 200, 160, 35);
        rewardBtn.setBounds(380, 200, 160, 35);

        // ===== Output Area + ScrollPane ===== ✅
        output = new JTextArea();
        output.setBackground(new Color(45, 45, 60));
        output.setForeground(Color.WHITE);
        output.setFont(new Font("Consolas", Font.PLAIN, 16));
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBounds(20, 255, 700, 280);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(45, 45, 60));

        // ===== إضافة الأزرار للـ Frame =====
        add(addCustomerBtn);
        add(showCustomerBtn);
        add(searchCustomerBtn);
        add(deleteCustomerBtn);
        add(makeOrderBtn);
        add(cancelOrderBtn);
        add(showOrdersBtn);
        add(billingBtn);
        add(profileBtn);
        add(updateInfoBtn);
        add(logoutBtn);
        add(marketingBtn);
        add(loyaltyBtn);
        add(rewardBtn);
        add(scrollPane); // ✅ scrollPane مش output

        // ================= ADD CUSTOMER =================
        addCustomerBtn.addActionListener(e -> {
            String name = InputValidator.getNameOnly(this, "Customer Name:");
            if (name == null) return;

            Customer c = new Customer(DataStore.customers.size() + 1, name);
            DataStore.customers.add(c);
            FileManager.saveAll();

            output.setText("✔ Customer Added");
        });

        // ================= SHOW CUSTOMERS =================
        showCustomerBtn.addActionListener(e -> {
            String data = "";
            for (Customer c : DataStore.customers) {
                data += c.id + " - " + c.name +
                        " | Payments: " + c.totalPayments +
                        " | Points: " + c.loyaltyPoints + "\n";
            }
            output.setText(data);
        });

        // ================= SEARCH CUSTOMER =================
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

            DataStore.orders.removeIf(o -> o.customer.id == id);
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

        // ================= CANCEL ORDER =================
        cancelOrderBtn.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(
                    JOptionPane.showInputDialog("Enter Customer ID")
                );

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

                String ordersInfo = customer.getOrdersInfo();
                if (ordersInfo == null || ordersInfo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No orders found");
                    return;
                }

                JOptionPane.showMessageDialog(this, ordersInfo);

                int orderId = Integer.parseInt(
                    JOptionPane.showInputDialog("Enter Order ID to cancel")
                );

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

            String giftsText = "";
            if (customer.gifts.isEmpty()) {
                giftsText = "  None";
            } else {
                for (String g : customer.gifts) giftsText += "  🎁 " + g + "\n";
            }

            output.setText(
                "=== BILLING ===\n" +
                "Name    : " + customer.name + "\n" +
                "Payments: " + customer.totalPayments + "\n" +
                "Points  : " + customer.loyaltyPoints + "\n\n" +
                "--- Gifts ---\n" + giftsText
            );
        });

        // ================= PROFILE =================
        profileBtn.addActionListener(e -> {
            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {

                    // ===== GIFTS =====
                    String giftsText = "";
                    if (c.gifts.isEmpty()) {
                        giftsText = "  None\n";
                    } else {
                        for (String g : c.gifts) giftsText += "  🎁 " + g + "\n";
                    }

                    // ===== OFFERS =====
                    String offersText = "";
                    if (c.offers.isEmpty()) {
                        offersText = "  None\n";
                    } else {
                        for (String o : c.offers) offersText += "  🏷 " + o + "\n";
                    }

                    // ===== ORDERS =====
                    String ordersText = c.getOrdersInfo().isEmpty() ? "  No Orders\n" : c.getOrdersInfo();

                    // ===== PROGRAMS =====
                    String programs =
                        "  Marketing : " + (c.marketingProgram ? "✔ Joined" : "✘ Not Joined") + "\n" +
                        "  Loyalty   : " + (c.loyaltyProgram   ? "✔ Joined" : "✘ Not Joined") + "\n" +
                        "  Reward    : " + (c.rewardProgram    ? "✔ Joined" : "✘ Not Joined") + "\n";

                    output.setText(
                        "=== CUSTOMER PROFILE ===\n" +
                        "Name    : " + c.name + "\n" +
                        "ID      : " + c.id   + "\n\n" +
                        "Payments: " + c.totalPayments + "\n" +
                        "Points  : " + c.loyaltyPoints + "\n\n" +
                        "--- Orders ---\n"   + ordersText  + "\n" +
                        "--- Gifts ---\n"    + giftsText   + "\n" +
                        "--- Offers ---\n"   + offersText  + "\n" +
                        "--- Programs ---\n" + programs
                    );

                    // ✅ يرجع الـ scroll لفوق عشان يبدأ من الأول
                    output.setCaretPosition(0);
                    return;
                }
            }
            output.setText("❌ Not Found");
        });

        // ================= UPDATE INFO =================
        updateInfoBtn.addActionListener(e -> {
            String name     = InputValidator.getNameOnly(this, "New Name:");
            String username = InputValidator.getText(this, "New Username:");
            String password = InputValidator.getText(this, "New Password:");

            if (name == null || username == null || password == null) return;

            currentUser.name     = name;
            currentUser.username = username;
            currentUser.password = password;

            for (Employee emp : DataStore.employees) {
                if (emp.id == currentUser.id) {
                    emp.name     = name;
                    emp.username = username;
                    emp.password = password;
                    break;
                }
            }

            FileManager.saveAll();
            output.setText("✔ Updated Successfully");
        });

        // ================= MARKETING PROGRAM =================
        marketingBtn.addActionListener(e -> {
            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {
                    c.registerMarketing();
                    FileManager.saveAll();
                    output.setText("✔ Customer joined Marketing Program");
                    return;
                }
            }
            output.setText("❌ Customer Not Found");
        });

        // ================= LOYALTY PROGRAM =================
        loyaltyBtn.addActionListener(e -> {
            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {
                    c.registerLoyalty();
                    FileManager.saveAll();
                    output.setText("✔ Customer joined Loyalty Program");
                    return;
                }
            }
            output.setText("❌ Customer Not Found");
        });

        // ================= REWARD PROGRAM =================
        rewardBtn.addActionListener(e -> {
            Integer id = InputValidator.getInt(this, "Customer ID:");
            if (id == null) return;

            for (Customer c : DataStore.customers) {
                if (c.id == id) {
                    c.registerReward();
                    FileManager.saveAll();
                    output.setText("✔ Customer joined Reward Program");
                    return;
                }
            }
            output.setText("❌ Customer Not Found");
        });

        // ================= LOGOUT =================
        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }
}
