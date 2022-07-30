import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Line> lines = new ArrayList<>();
    private static List<Station> stations = new ArrayList<>();
    private static final String METRO_STATIONS_LIST = "https://skillbox-java.github.io/";

    public static void main(String[] args) {
        parseMetro(METRO_STATIONS_LIST, lines, stations);

        System.out.println("Линии московского метро");
        lines.stream().forEach(System.out::println);
        System.out.println("\nСтанции московского метро");
        stations.stream().forEach(System.out::println);
    }

    public static void parseMetro(String url, List<Line> lines, List<Station> stations) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements lineNames = doc.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
        lineNames.forEach(e -> {
            lines.add(new Line(e.text(), e.attr("data-line")));
        });

        Elements stationNames = doc.select("div.js-metro-stations.t-metrostation-list-table");
        stationNames.forEach(e -> {
            String lineNumber = e.attr("data-line");
            e.children().forEach(station -> {
                String name = station.text().replaceAll("^\\d+\\.\\s+", "");
                stations.add(new Station(name, lineNumber));
            });
        });
    }
}
