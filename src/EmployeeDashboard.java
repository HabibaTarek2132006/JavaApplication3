/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 */
import javax.swing.*;

public class EmployeeDashboard extends JFrame {

    JButton addCustomerBtn, showCustomerBtn, searchCustomerBtn;
    JButton makeOrderBtn, cancelOrderBtn, showOrdersBtn;
    JButton billingBtn;

    JTextArea output;

    public EmployeeDashboard() {

        setTitle("Employee Dashboard");
        setSize(650, 450);
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

        output = new JTextArea();
        output.setBounds(20, 150, 590, 230);
        add(output);

        add(addCustomerBtn);
        add(showCustomerBtn);
        add(searchCustomerBtn);
        add(makeOrderBtn);
        add(cancelOrderBtn);
        add(showOrdersBtn);
        add(billingBtn);

        // ================= CUSTOMER =================

        addCustomerBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Customer Name:");
            Customer c = new Customer(DataStore.customers.size() + 1, name);
            DataStore.customers.add(c);
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

            // ✅ Order creation
            Order order = new Order(customer);
            order.customer = customer;
            order.meals.add(selectedMeal);
            order.calculateTotal();

            DataStore.orders.add(order);

            // ✅ IMPORTANT: update customer profile properly
            customer.addOrder(order);
            customer.addPayment(order.totalPrice);

            JOptionPane.showMessageDialog(this,
                    "Order Created ✔ Total: " + order.totalPrice
            );
        });

        cancelOrderBtn.addActionListener(e -> {

            int id = Integer.parseInt(JOptionPane.showInputDialog("Order ID"));

            DataStore.orders.removeIf(o -> o.id == id);

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
                    "\nGifts = " + customer.gifts
            );
        });
    }
}