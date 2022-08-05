import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

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
        writeJSONStations("out/stations.json");
        readJSONMap("out/map.json");
    }

    private static void readJSONMap(String path) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject map = (JSONObject) parser.parse(new FileReader(path));

            JSONArray linesArr = (JSONArray) map.get("lines");
            JSONObject stationsObj = (JSONObject) map.get("stations");
            linesArr.forEach(l -> {
                String lineName = (String) ((JSONObject) l).get("name");
                String lineNumber = (String) ((JSONObject) l).get("number");
                Integer stationCount = ((JSONArray) stationsObj.get(lineNumber)).size();
                System.out.println("\nline: " + lineName +
                        "\nnumber: " + lineNumber +
                        "\nstation count:" + stationCount );
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void writeJSONStations(String path) {
       JSONArray stationsArr = new JSONArray();
        for (Station station: stations) {
            JSONObject stationsObj = new JSONObject();
            stationsObj.put("name", station.getName());
            stationsObj.put("hasConnection", station.isHasConnection());
            Optional lineName = lines.stream().filter(l -> l.getNumber().equals(station.getLineNumber())).findFirst();
            if (lineName.isPresent()) {
                stationsObj.put("line", ((Line) lineName.get()).getName());
            }
            if (station.getDate() != null) {

                stationsObj.put("date", (new SimpleDateFormat("dd.MM.yyyy")).format(station.getDate()));
            }
            if (station.getDepth() != null) {
                stationsObj.put("depth", station.getDepth());
            }
            stationsArr.add(stationsObj);
        }

        JSONObject jsonFile = new JSONObject();
        jsonFile.put("stations", stationsArr);
        try{
            Files.write(Paths.get(path), jsonFile.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
