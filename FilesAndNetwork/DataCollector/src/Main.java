import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Line> lines = new ArrayList<>();
    private static List<Station> stations = new ArrayList<>();
    private static ParseFiles parseFiles = new ParseFiles();

    public static void main(String[] args) {
        ParseHTML.parseMetroStations(lines, stations);

        parseFiles.parseMetroStation("data/");
        Map<String, Date> stationDates = parseFiles.getStationDate();
        Map<String, Double> stationDepths = parseFiles.getStationDepth();
        stations.stream().forEach(s -> {
            if (stationDates.containsKey(s.getName())) {
                s.setDate(stationDates.get(s.getName()));
            }
            if (stationDepths.containsKey(s.getName())) {
                s.setDepth(stationDepths.get(s.getName()));
            }
            System.out.println(s);
        });
    }
}
