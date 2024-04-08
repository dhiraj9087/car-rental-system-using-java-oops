import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main
 */
class Car {
    private String car_id;  // car id 
    private String car_brand;  // car brand 
    private String car_model;  // car model
    private double base_price_per_day; // price per day 
    private boolean is_car_available; // availibility of car
    
    public Car(String car_id, String car_brand, String car_model, double base_price_per_day, boolean is_car_available)   /// parameterized constructor
    {
        this.car_id = car_id;
        this.car_brand = car_brand;
        this.car_model = car_model;
        this.base_price_per_day = base_price_per_day;
        this.is_car_available = true;
    }

    public String get_car_id()  // to get car id 
    {
        return car_id;
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


class Car_Rental_System {
    private List<Car> cars;           // declears a list which stores data of cars

    private List<Customer> customers; // declears a list which stores data of customers

    private List<Rental> rentals;     // declears a list which stores all info of which customer rents which car

    public Car_Rental_System()        // constructor
    {   
        cars = new ArrayList<>();
        customers = new ArrayList<>();   /// using contructor we made those list inside memory 
        rentals = new ArrayList<>();
    }
         
    public void add_car(Car car)        // add car to list
    {
        cars.add(car);
    }

    public void add_customer(Customer customer)   // add customer to list
    {
        customers.add(customer);
    }

    public void rent_car(Car car, Customer customer, int days)   // add info into list
    {
        if (car.car_availibility())
        {
            car.car_rented();                                  /// if car availablee then add to list
            rentals.add(new Rental(car, customer, days));
        }
        else{
            System.out.println("The car is already rented");
        }
    }

    public void return_car(Car car)                         // return car method
    {
        Rental rental_to_remove = null;
        for (Rental rental:rentals) {
            if (rental.get_car()==car) {
                rental_to_remove = rental;
                break;

            }
        }
        if (rental_to_remove!=null) {
            rentals.remove(rental_to_remove);
        }
        else{
            System.out.println("Car was not rented. ");
        }


        car.car_returned();                         /// car returned succesfully


    }

    
    public void menu()
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice==1){
                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");
                String customer_name = scanner.nextLine();

                System.out.println("\nAvailable Cars:");
                for (Car car : cars) {
                    if (car.car_availibility()) {
                        System.out.println(car.get_car_id() + " - " + car.get_car_brand() + " " + car.get_car_model());
                    }
                }

                System.out.print("\nEnter the car ID you want to rent: ");
                String car_id = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int rental_days = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer new_customer = new Customer("Customer"+customers.size() + 1, customer_name);
                add_customer(new_customer);

                Car selected_car = null;
                for(Car car:cars) {
                    if (car.get_car_id().equals(car_id) && car.car_availibility()){
                        selected_car = car;
                        break;
                    }
                }
                if (selected_car==null) {
                    System.out.println("\nInvalid car selection or car not available for rent.");

                }
                else {
                    double total_price = selected_car.calculate_car_price(rental_days);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + new_customer.get_customer_id());
                    System.out.println("Customer Name: " + new_customer.get_customer_name());
                    System.out.println("Car: " + selected_car.get_car_brand() + " " + selected_car.get_car_model());
                    System.out.println("Rental Days: " + rental_days);
                    System.out.printf("Total Price: $%.2f%n", total_price);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        rent_car(selected_car,new_customer,rental_days);
                        System.out.println("\nCar rented successfully.");
                    }
                    else{
                        System.out.println("\nRental canceled");
                    }
                }
            }
            else if (choice==2){
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String car_id = scanner.nextLine();

                Car car_to_return = null;
                for(Car car:cars)
                {
                    if (car.get_car_id().equals(car_id) && car.car_availibility()){
                        car_to_return=car;
                        break;
                    }

                }

                if (car_to_return != null) {
                    Customer customer = null;
                    for(Rental rental: rentals)
                    {
                        if(rental.get_car()==car_to_return)
                        {
                            customer = rental.get_customer();
                            break;
                        }
                    }

                    if (customer != null)
                    {
                        return_car(car_to_return);
                        System.out.println("Car returned successfully by " + customer.get_customer_name());

                    }
                    else {
                        System.out.println("Car was not rented or rental information is missing.");

                    }
                }
                else {
                    System.out.println("Invalid car ID or car is not rented.");

                }
            }
            else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");

    }





}

public class main {

    public static void main(String[] args) {
        Car_Rental_System rentalSystem = new Car_Rental_System();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0,true); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0,true);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0,true);
        rentalSystem.add_car(car1);
        rentalSystem.add_car(car2);
        rentalSystem.add_car(car3);

        rentalSystem.menu();
    }
    
}