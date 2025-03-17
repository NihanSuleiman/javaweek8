import java.util.List;
import java.util.Map;

public interface RaceResult {
    void recordRaceResult(String raceName, String location, Map<Driver, Integer> positions);

    String getRaceName();

    String getLocation();

    Map<Driver, Integer> getResults();
}