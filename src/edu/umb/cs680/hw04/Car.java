xpackage edu.umb.cs680.hw04;

public class Car {

    private String make, model;
    private int mileage, year;
    private float price;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }

    public static void main(String args[]) {
        Car car = new Car("Toyota", "4Runner", 351999, 1994, 500);
        System.out.printf("Car: for $%.0f, you can get a %s %s %s with %s miles on it.\n",
                          car.getPrice(),
                          car.getYear(), car.getMake(), car.getModel(),
                          car.getMileage());
    }

}
