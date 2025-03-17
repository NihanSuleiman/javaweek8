import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStatistics {

    private ChampionshipStatistics() {
        // Utility class should not be instantiated
    }

    public static double calculateAveragePointsPerDriver() {
        ChampionshipManager manager = ChampionshipManager.getInstance();
        List<Driver> drivers = manager.getDrivers();

        if (drivers.isEmpty()) {
            return 0.0;
        }

        int totalPoints = manager.calculateTotalChampionshipPoints();
        return (double) totalPoints / drivers.size();
    }

    public static String findMostSuccessfulCountry() {
        ChampionshipManager manager = ChampionshipManager.getInstance();
        List<Driver> drivers = manager.getDrivers();

        if (drivers.isEmpty()) {
            return "None";
        }

        // Calculate points by country
        Map<String, Integer> pointsByCountry = new HashMap<>();
        for (Driver driver : drivers) {
            String country = driver.getCountry();
            int points = driver.getTotalPoints();
            pointsByCountry.put(country, pointsByCountry.getOrDefault(country, 0) + points);
        }

        // Find country with most points
        return pointsByCountry.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");
    }

    public static int countTotalRaces() {
        return ChampionshipManager.getInstance().getRaceResults().size();
    }
}