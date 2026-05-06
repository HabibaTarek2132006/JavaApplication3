/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 * 
 */
import java.util.ArrayList;

public class Order {

    public int id;
    public Customer customer;
    public ArrayList<Meal> meals = new ArrayList<>();
    public double totalPrice;

    public Order(Customer customer) {
        this.id = generateId(); // 👈 هنا بيستخدمها
        this.customer = customer;
    }

    // 🔥 الميثود لازم تكون جوه الكلاس
    private int generateId() {

    boolean[] used = new boolean[1000]; // حجم كفاية

    for (Order o : DataStore.orders) {
        if (o.id < used.length) {
            used[o.id] = true;
        }
    }

    for (int i = 1; i < used.length; i++) {
        if (!used[i]) {
            return i;
        }
    }

    return used.length; // fallback
}

    public void calculateTotal() {
        totalPrice = 0;
        for (Meal m : meals) {
            totalPrice += m.price;
        }
    }

    public void checkout() {
        calculateTotal();
        customer.addPayment(totalPrice);
        customer.addOrder(this);
    }
}