import java.util.ArrayList;

public class Order {
    public int id;
    public Customer customer;
    public ArrayList<Meal> meals = new ArrayList<>();
    public double totalPrice;

    public Order(Customer customer) {
        this.customer = customer;
        this.id = generateCustomerOrderId(customer);
    }

    private int generateCustomerOrderId(Customer customer) {
        int id = 1;
        for (Order o : customer.orders) {
            if (o.id >= id) {
                id = o.id + 1;
            }
        }
        return id;
    }

    public void calculateTotal() {
        totalPrice = 0;
        for (Meal m : meals) {
            totalPrice += m.price;
        }
    }

    public void checkout() {
        calculateTotal();
        customer.orders.add(this);       // ✅ بس بتضيف الorder
        customer.addPayment(totalPrice); // ✅ بتحسب payments وpoints مرة واحدة بس
    }
}