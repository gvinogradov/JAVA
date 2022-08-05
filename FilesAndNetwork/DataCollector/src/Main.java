import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        });

        writeJSONMap("out/map.json");
    }

    private static void writeJSONMap(String path) {
        JSONObject map = new JSONObject();
        JSONObject stationsObect = new JSONObject();
        JSONArray linesArr = new JSONArray();
        for (Line line: lines) {
            JSONObject lineObject = new JSONObject();
            lineObject.put("name", line.getName());
            lineObject.put("number", line.getNumber());
            linesArr.add(lineObject);

            JSONArray stationsArr = new JSONArray();
            stations.stream().filter(s -> s.getLineNumber().equals(line.getNumber()))
                    .forEach(s->stationsArr.add(s.getName()));
            stationsObect.put(line.getNumber(), stationsArr);
        }

        map.put("stations", stationsObect);
        map.put("lines", linesArr);

        try{
            Files.write(Paths.get(path), map.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
