/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarek
 */
public class Main {

    public static void main(String[] args) {

        DataStore.employees.add(new Employee(1, "Habiba", "emp1", "1111"));
        DataStore.employees.add(new Employee(2, "Raghad", "emp2", "2222"));
         DataStore.employees.add(new Employee(3, "Salma", "emp3", "3333"));
        DataStore.employees.add(new Employee(4, "Suzan", "emp4", "4444"));
         DataStore.employees.add(new Employee(5, "Shahd", "emp5", "5555"));
        DataStore.employees.add(new Employee(6, "Ali", "emp6", "6666"));

        DataStore.meals.add(new Meal(1, "Pizza", 30));
        DataStore.meals.add(new Meal(2, "Burger", 450));
        DataStore.meals.add(new Meal(3, "Hot Dog", 100));
        DataStore.meals.add(new Meal(4, "Pasta", 200));
        DataStore.meals.add(new Meal(5, "Chicken", 300));
        DataStore.meals.add(new Meal(6, "Steak", 500));
        

        new LoginScreen().setVisible(true);
    }
}


