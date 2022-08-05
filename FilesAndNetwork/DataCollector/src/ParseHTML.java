import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParseHTML {

    private static final String METRO_STATIONS_LIST = "https://skillbox-java.github.io/";

    public static void parseMetroStations(List<Line> lines, List<Station> stations) {
        parseMetro(METRO_STATIONS_LIST, lines, stations);
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
                boolean hasConnection = !station.select("span.t-icon-metroln").isEmpty();
                stations.add(new Station(name, lineNumber, hasConnection));
            });
        });
    }
}
