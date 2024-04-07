/**
 * main
 */
class Car {
    private String carId;  // car id 
    private String car_brand;  // car brand 
    private String car_model;  // car model
    private double base_price_per_day; // price per day 
    private boolean is_car_available; // availibility of car
    
    public Car(String carId, String car_brand, String car_model, double basePriceDay, boolean is_car_available)   /// parameterized constructor
    {
        this.carId = carId;
        this.car_brand = car_brand;
        this.car_model = car_model;
        this.base_price_per_day = base_price_per_day;
        this.is_car_available = true;
    }

    public String get_car_id()  // to get car id 
    {
        return carId;
    }

    public String get_car_brand()  /// to get car brand
    {
        return car_brand;
    }

    public String get_car_model()  // to get car model
    {
        return car_model;
    }

    public double calculate_car_price(int no_of_days)  // to calculate total car price
    {
        return no_of_days * base_price_per_day;
    }

    public boolean car_availibility()    /// to know is car available
    {
        return is_car_available;
    }

    public void car_rented()    // when car gets rented
    {
        is_car_available = false;
    }

    public void car_returned()   /// when car is returned
    {
        is_car_available= true;
    }


}


/**
 * Customer
 */// customer details 
class Customer {
    private String customer_id;
    private String customer_name;
    
    public Customer(String customer_id, String customer_name)
    {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
    }

    public String get_customer_id()    // to get customer id
    {
        return customer_id;
    }

    public String get_customer_name()   // to get customer name
    {
        return customer_name;
    }
}


// combination of car and customer class

class Rental{  

    private Car car_variable;              // car variable of Car class type
                     
    private Customer customer_variable;    // customer variable of Customer type
         
    private int days;                      // for how many days customer wants car
    
    public Rental(Car car_variable, Customer customer_variable, int days)     // parameterized constructor
    {
        this.car_variable = car_variable;
        this.customer_variable = customer_variable;
        this.days = days;
    }

    public Car get_car()   // to get car details
    {
        return car_variable;
    }

    public Customer get_customer()     // to get customer details
    {
        return customer_variable;
    }

    public int get_days()      // to get number of days
    {
        return days;
    }
}

public class main {

    public static void main(String[] args) {
        System.out.println("running");
    }
}