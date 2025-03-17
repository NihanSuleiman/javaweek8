public class Driver {
    private String name;
    private String country;
    private int totalPoints;
    private RallyCar car;

    private static int totalDrivers = 0;

    public Driver(String name, String country, RallyCar car) {
        this.name = name;
        this.country = country;
        this.totalPoints = 0;
        this.car = car;
        totalDrivers++;
    }

    public void addPoints(int points) {
        this.totalPoints += points;
    }

    public void changeCar(RallyCar newCar) {
        this.car = newCar;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public RallyCar getCar() {
        return car;
    }

    public static int getTotalDrivers() {
        return totalDrivers;
    }
}