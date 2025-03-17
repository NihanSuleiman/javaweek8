import java.util.HashMap;
import java.util.Map;

public class RallyRaceResult implements RaceResult {
    private String raceName;
    private String location;
    private Map<Driver, Integer> results;
    private static final int[] POINTS_SYSTEM = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    public RallyRaceResult() {
        this.results = new HashMap<>();
    }

    @Override
    public void recordRaceResult(String raceName, String location, Map<Driver, Integer> positions) {
        this.raceName = raceName;
        this.location = location;

        for (Map.Entry<Driver, Integer> entry : positions.entrySet()) {
            Driver driver = entry.getKey();
            int position = entry.getValue();

            int points = 0;
            if (position > 0 && position <= POINTS_SYSTEM.length) {
                points = POINTS_SYSTEM[position - 1];
            }

            driver.addPoints(points);
            results.put(driver, points);
        }
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Map<Driver, Integer> getResults() {
        return results;
    }
}