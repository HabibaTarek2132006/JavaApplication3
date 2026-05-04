/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 */
import java.util.ArrayList;

public class Customer extends Person {

    ArrayList<String> gifts = new ArrayList<>();
    ArrayList<String> offers = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<String> notifications = new ArrayList<>();

    double totalPayments = 0;
    int loyaltyPoints = 0;

    boolean marketingProgram = false;
    boolean loyaltyProgram = false;
    boolean rewardProgram = false;

    public Customer(int id, String name) {
        super(id, name);
    }

    // ================= REGISTER PROGRAMS =================
    public void registerMarketing() {
        marketingProgram = true;
        addNotification(name + " joined Marketing Program");
    }

    public void registerLoyalty() {
        loyaltyProgram = true;
        addNotification(name + " joined Loyalty Program");
    }

    public void registerReward() {
        rewardProgram = true;
        addNotification(name + " joined Reward Program");
    }

    // ================= ORDERS =================
    public void addOrder(Order order) {
        orders.add(order);

        // ✔ مهم: الحساب يتم مرة واحدة فقط هنا
        addPayment(order.totalPrice);
    }

    // ================= PAYMENTS =================
    public void addPayment(double amount) {

        totalPayments += amount;
        loyaltyPoints += (int)(amount / 10);

        checkGifts();
    }

    // ================= GIFTS LOGIC (CLEAN) =================
    private void checkGifts() {

        if (totalPayments >= 200 && !gifts.contains("Free Drink 🥤")) {
            gifts.add("Free Drink 🥤");
            addNotification("🥤 Free Drink earned");
        }

        if (totalPayments >= 500 && !gifts.contains("Free Dessert 🍰")) {
            gifts.add("Free Dessert 🍰");
            addNotification("🍰 Free Dessert earned");
        }

        if (totalPayments >= 800 && !gifts.contains("Free Meal 🍔")) {
            gifts.add("Free Meal 🍔");
            addNotification("🍔 Free Meal earned");
        }
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
}