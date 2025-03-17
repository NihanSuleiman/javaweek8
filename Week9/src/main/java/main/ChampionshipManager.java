import java.util.*;

public class ChampionshipManager {
    private static ChampionshipManager instance;

    private List<Driver> drivers;
    private List<RaceResult> raceResults;

    private ChampionshipManager() {
        drivers = new ArrayList<>();
        raceResults = new ArrayList<>();
    }

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    public void recordRaceResult(RaceResult raceResult) {
        raceResults.add(raceResult);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<RaceResult> getRaceResults() {
        return raceResults;
    }

    public List<Driver> getChampionshipStandings() {
        List<Driver> standings = new ArrayList<>(drivers);
        standings.sort((d1, d2) -> d2.getTotalPoints() - d1.getTotalPoints());
        return standings;
    }

    public Driver getLeadingDriver() {
        if (drivers.isEmpty()) {
            return null;
        }

        return getChampionshipStandings().get(0);
    }

    public int calculateTotalChampionshipPoints() {
        return drivers.stream().mapToInt(Driver::getTotalPoints).sum();
    }
}