//AI was used in this task for support e.g., to solve errors + when I did not know how to do something//
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        GravelCar gravelCar1 = new GravelCar("Toyota", "Yaris WRC", 380, 12.5);
        GravelCar gravelCar2 = new GravelCar("Hyundai", "i20 WRC", 370, 13.0);
        AsphaltCar asphaltCar1 = new AsphaltCar("Ford", "Puma Rally1", 390, 240.0);
        AsphaltCar asphaltCar2 = new AsphaltCar("M-Sport", "Fiesta WRC", 375, 225.0);

        Driver ogier = new Driver("Sébastien Ogier", "France", gravelCar1);
        Driver rovanpera = new Driver("Kalle Rovanperä", "Finland", gravelCar2);
        Driver tanak = new Driver("Ott Tänak", "Estonia", asphaltCar1);
        Driver neuville = new Driver("Thierry Neuville", "Belgium", asphaltCar2);

        manager.registerDriver(ogier);
        manager.registerDriver(rovanpera);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        Map<Driver, Integer> finlandPositions = new HashMap<>();
        finlandPositions.put(ogier, 1);
        finlandPositions.put(tanak, 2);
        finlandPositions.put(rovanpera, 3);
        finlandPositions.put(neuville, 4);

        RallyRaceResult finlandRally = new RallyRaceResult();
        finlandRally.recordRaceResult("Rally Finland", "Jyväskylä", finlandPositions);
        manager.recordRaceResult(finlandRally);

        rovanpera.changeCar(asphaltCar1);
        tanak.changeCar(gravelCar2);

        Map<Driver, Integer> monteCarloPositions = new HashMap<>();
        monteCarloPositions.put(rovanpera, 1);
        monteCarloPositions.put(neuville, 2);
        monteCarloPositions.put(ogier, 3);
        monteCarloPositions.put(tanak, 4);

        RallyRaceResult monteCarloRally = new RallyRaceResult();
        monteCarloRally.recordRaceResult("Monte Carlo Rally", "Monaco", monteCarloPositions);
        manager.recordRaceResult(monteCarloRally);

        displayChampionshipStandings(manager);

        displayChampionshipLeader(manager);

        displayChampionshipStatistics();

        displayRaceResults(manager);

        displayCarPerformanceRatings();
    }

    private static void displayChampionshipStandings(ChampionshipManager manager) {
        List<Driver> standings = manager.getChampionshipStandings();

        int position = 1;
        for (Driver driver : standings) {
            System.out.println(position + ". " + driver.getName() + " (" + driver.getCountry() + "): " +
                    driver.getTotalPoints() + " points");
            position++;
        }
    }

    private static void displayChampionshipLeader(ChampionshipManager manager) {
        Driver leader = manager.getLeadingDriver();

        System.out.println("===== CHAMPIONSHIP LEADER =====");
        if (leader != null) {
            System.out.println(leader.getName() + " with " + leader.getTotalPoints() + " points");
        } else {
            System.out.println("No leader found");
        }
    }

    private static void displayChampionshipStatistics() {
        System.out.println("===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total Drivers: " + Driver.getTotalDrivers());
        System.out.println("Total Races: " + ChampionshipStatistics.countTotalRaces());
        System.out.printf("Average Points Per Driver: %.2f%n", ChampionshipStatistics.calculateAveragePointsPerDriver());
        System.out.println("Most Successful Country: " + ChampionshipStatistics.findMostSuccessfulCountry());
        System.out.println("Total Championship Points: " +
                ChampionshipManager.getInstance().calculateTotalChampionshipPoints());
    }

    private static void displayRaceResults(ChampionshipManager manager) {
        System.out.println("===== RACE RESULTS =====");

        for (RaceResult raceResult : manager.getRaceResults()) {
            System.out.println("Race: " + raceResult.getRaceName() + " (" + raceResult.getLocation() + ")");

            raceResult.getResults().entrySet().stream()
                    .sorted(Map.Entry.<Driver, Integer>comparingByValue().reversed())
                    .forEach(entry -> {
                        Driver driver = entry.getKey();
                        int points = entry.getValue();
                        System.out.println("Position " + getPositionFromPoints(points) + ": " +
                                driver.getName() + " - " + points + " points");
                    });
        }
    }

    private static void displayCarPerformanceRatings() {
        System.out.println("===== CAR PERFORMANCE RATINGS =====");

        GravelCar gravelCar = new GravelCar("Hyundai", "i20 WRC", 370, 13.0);
        AsphaltCar asphaltCar = new AsphaltCar("Ford", "Puma Rally1", 390, 240.0);

        System.out.println("Gravel Car Performance: " + gravelCar.calculatePerformance());
        System.out.println("Asphalt Car Performance: " + asphaltCar.calculatePerformance());
    }

    private static int getPositionFromPoints(int points) {
        switch (points) {
            case 25: return 1;
            case 18: return 2;
            case 15: return 3;
            case 12: return 4;
            case 10: return 5;
            case 8: return 6;
            case 6: return 7;
            case 4: return 8;
            case 2: return 9;
            case 1: return 10;
            default: return 11;
        }
    }
}