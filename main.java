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

    public String get_car_id()
    {
        return carId;
    }

    public String get_car_brand()
    {
        return car_brand;
    }

    public String get_car_model()
    {
        return car_model;
    }

    public double calculate_car_price(int no_of_days)
    {
        return no_of_days * base_price_per_day;
    }

    public boolean car_availibility()
    {
        return is_car_available;
    }

    public void car_rented()
    {
        is_car_available = false;
    }

    public void car_returend()
    {
        is_car_available= true;
    }
}
public class main {

    public static void main(String[] args) {
        System.out.println("running");
    }
}