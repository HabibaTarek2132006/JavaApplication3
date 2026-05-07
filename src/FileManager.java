import java.io.*;
import java.util.*;

public class FileManager {

    // ================= SAVE ALL =================
    public static void saveAll() {
        saveEmployees();
        saveMeals();
        saveCustomers();
        saveOrders();
        saveAdmin();
    }

    // ================= LOAD ALL =================
    public static void loadAll() {
        loadEmployees();
        loadMeals();
        loadCustomers();
        loadOrders();
        loadAdmin();
    }

    // ================= EMPLOYEES =================
    private static void saveEmployees() {
        try (PrintWriter pw = new PrintWriter("employees.txt")) {
            for (Employee e : DataStore.employees) {
                pw.println(e.id + "," + e.name + "," + e.username + "," + e.password);
            }
        } catch (Exception e) {
            System.out.println("Error saving employees");
        }
    }

    private static void loadEmployees() {
        try (Scanner sc = new Scanner(new File("employees.txt"))) {
            DataStore.employees.clear();

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                Employee e = new Employee(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3]
                );
                DataStore.employees.add(e);
            }
        } catch (Exception e) {
            System.out.println("No employees file found");
        }
    }

    // ================= MEALS =================
    private static void saveMeals() {
        try (PrintWriter pw = new PrintWriter("meals.txt")) {
            for (Meal m : DataStore.meals) {
                pw.println(m.id + "," + m.name + "," + m.price);
            }
        } catch (Exception e) {
            System.out.println("Error saving meals");
        }
    }

    private static void loadMeals() {
        try (Scanner sc = new Scanner(new File("meals.txt"))) {
            DataStore.meals.clear();

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                Meal m = new Meal(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2])
                );
                DataStore.meals.add(m);
            }
        } catch (Exception e) {
            System.out.println("No meals file found");
        }
    }

    // ================= CUSTOMERS =================
    private static void saveCustomers() {
        try (PrintWriter pw = new PrintWriter("customers.txt")) {
            for (Customer c : DataStore.customers) {
                pw.println(c.id + "," + c.name + "," +
                        c.totalPayments + "," + c.loyaltyPoints);
            }
        } catch (Exception e) {
            System.out.println("Error saving customers");
        }
    }

    private static void loadCustomers() {
        try (Scanner sc = new Scanner(new File("customers.txt"))) {
            DataStore.customers.clear();

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                Customer c = new Customer(
                        Integer.parseInt(parts[0]),
                        parts[1]
                );
                c.totalPayments = Double.parseDouble(parts[2]);
                c.loyaltyPoints = Integer.parseInt(parts[3]);

                DataStore.customers.add(c);
            }
        } catch (Exception e) {
            System.out.println("No customers file found");
        }
    }

    // ================= ORDERS =================
    private static void saveOrders() {
        try (PrintWriter pw = new PrintWriter("orders.txt")) {

            for (Order o : DataStore.orders) {

                String mealsIds = "";
                for (Meal m : o.meals) {
                    mealsIds += m.id + "-";
                }

                pw.println(o.id + "," + o.customer.id + "," + o.totalPrice + "," + mealsIds);
            }

        } catch (Exception e) {
            System.out.println("Error saving orders");
        }
    }

    private static void loadOrders() {
        try (Scanner sc = new Scanner(new File("orders.txt"))) {

            DataStore.orders.clear();

            while (sc.hasNextLine()) {

                String[] parts = sc.nextLine().split(",");

                int orderId = Integer.parseInt(parts[0]);
                int customerId = Integer.parseInt(parts[1]);
                double total = Double.parseDouble(parts[2]);

                Customer customer = null;
                for (Customer c : DataStore.customers) {
                    if (c.id == customerId) {
                        customer = c;
                        break;
                    }
                }

                Order o = new Order(customer);
                o.id = orderId;
                o.totalPrice = total;

                DataStore.orders.add(o);
            }

        } catch (Exception e) {
            System.out.println("No orders file found");
        }
    }

    // ================= ADMIN =================
    private static void saveAdmin() {
        try (PrintWriter pw = new PrintWriter("admin.txt")) {
            if (DataStore.admin != null) {
                pw.println(
                        DataStore.admin.id + "," +
                        DataStore.admin.name + "," +
                        DataStore.admin.username + "," +
                        DataStore.admin.password
                );
            }
        } catch (Exception e) {
            System.out.println("Error saving admin");
        }
    }

    private static void loadAdmin() {
        try (Scanner sc = new Scanner(new File("admin.txt"))) {

            if (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");

                DataStore.admin = new User(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        Role.ADMIN
                );
            }

        } catch (Exception e) {
            System.out.println("No admin file found");

            DataStore.admin = new User(0, "Admin", "admin", "1234", Role.ADMIN);
        }
    }
}
