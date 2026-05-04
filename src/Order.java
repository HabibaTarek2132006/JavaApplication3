/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 */
import java.util.ArrayList;

public class Order {

    static int counter = 1;

    public int id;
    public Customer customer;
    public ArrayList<Meal> meals = new ArrayList<>();
    public double totalPrice;

    public Order(Customer customer) {
        this.id = counter++;
        this.customer = customer;
    }

    public void calculateTotal() {
        totalPrice = 0;
        for (Meal m : meals) {
            totalPrice += m.price;
        }

        // 🔥 مهم جدًا: تسجيل الدفع في العميل
        customer.addPayment(totalPrice);
    }
}