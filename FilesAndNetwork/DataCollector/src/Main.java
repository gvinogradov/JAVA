import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Line> lines = new ArrayList<>();
    private static List<Station> stations = new ArrayList<>();

    public static void main(String[] args) {
       // ParseHTML.parseMetroStations(lines, stations);
        ParseFiles.parseMetroStation(stations);

    }
}
