public class GravelCar extends RallyCar {
    private double suspensionTravel;

    public GravelCar(String make, String model, int horsepower, double suspensionTravel) {
        super(make, model, horsepower);
        this.suspensionTravel = suspensionTravel;
    }

    @Override
    public double calculatePerformance() {
        return horsepower * 0.7 + suspensionTravel * 15;
    }

    public double getSuspensionTravel() {
        return suspensionTravel;
    }
}