import java.util.ArrayList;

public class Customer extends Person {

    ArrayList<String> gifts         = new ArrayList<>();
    ArrayList<String> offers        = new ArrayList<>();
    ArrayList<Order>  orders        = new ArrayList<>();
    ArrayList<String> notifications = new ArrayList<>();

    double  totalPayments    = 0;
    int     loyaltyPoints    = 0;
    boolean marketingProgram = false;
    boolean loyaltyProgram   = false;
    boolean rewardProgram    = false;

    public Customer(int id, String name) {
        super(id, name);
    }

    // ================= REGISTER PROGRAMS =================

    public void registerMarketing() {
        marketingProgram = true;
        addOffer("Marketing Member - 10% Discount 🏷");  // ✅ offer تلقائي
        addNotification(name + " joined Marketing Program");
    }

    public void registerLoyalty() {
        loyaltyProgram = true;
        addOffer("Reward Member - Double Points 🌟");  // ✅ offer تلقائي
        addNotification(name + " joined Loyalty Program");
    }

    public void registerReward() {
        rewardProgram = true;
        addOffer("Loyalty Member - Free Appetizer 🍟");    // ✅ offer تلقائي
        addNotification(name + " joined Reward Program");
    }

    // ================= ORDERS =================

    public void addOrder(Order order) {
        orders.add(order);
        addPayment(order.totalPrice);
    }

    // ================= PAYMENTS =================

    public void addPayment(double amount) {
        totalPayments += amount;
        loyaltyPoints += (int)(amount / 10);
        checkGifts();
    }

    // ================= GIFTS =================

    private void checkGifts() {
        gifts.clear();
        if (totalPayments >= 200) gifts.add("Free Drink 🥤");
        if (totalPayments >= 500) gifts.add("Free Dessert 🍰");
        if (totalPayments >= 800) gifts.add("Free Meal 🍔");
    }

    // ================= OFFERS =================

    public void addOffer(String offer) {
        offers.add(offer);
        addNotification("Offer: " + offer);
    }

    // ================= NOTIFICATIONS =================

    public void addNotification(String msg) {
        notifications.add(msg);
        System.out.println("[" + name + "] " + msg);
    }

    // ================= CANCEL ORDER =================

    public boolean cancelOrder(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.id == orderId) {
                totalPayments -= o.totalPrice;
                if (totalPayments < 0) totalPayments = 0;
                loyaltyPoints -= (int)(o.totalPrice / 10);
                if (loyaltyPoints < 0) loyaltyPoints = 0;
                orders.remove(i);
                checkGifts();
                addNotification("Order " + orderId + " cancelled");
                return true;
            }
        }
        return false;
    }

    // ================= ORDERS INFO =================

    public String getOrdersInfo() {
        String data = "";
        for (Order o : orders) {
            data += "Order ID: " + o.id + " | Total: " + o.totalPrice + "\n";
        }
        return data;
    }
}
