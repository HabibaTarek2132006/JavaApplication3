import java.util.ArrayList;

public class DataStore {

    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Meal> meals = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<String> offers = new ArrayList<>();

    // ================= ADMIN =================
    public static User admin = new User(0, "Admin", "admin", "1234", Role.ADMIN);
}