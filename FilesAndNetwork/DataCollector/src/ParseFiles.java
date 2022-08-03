import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isDigit;

public class ParseFiles {

    private String path;
    private static final String CSV_FIELD_NAME = "Название станции";
    private static final String CSV_FIELD_NAME_2 = "Название";
    private static final String CSV_FIELD_DEPTH = "Глубина";
    private static final String CSV_FIELD_DATE = "Дата открытия";
    private static final String JSON_FIELD_NAME = "name";
    private static final String JSON_FIELD_DATE = "date";
    private static final String JSON_FIELD_NAME2 = "station_name";
    private static final String JSON_FIELD_DEPTH = "depth";
    private static final String JSON_FIELD_DEPTH2 = "depth_meters";

    @Getter
    private Map<String, Double> stationDepth;
    @Getter
    private Map<String, Date> stationDate;

    public void parseMetroStation(String path) {
        this.path = path;
        stationDepth = new HashMap<>();
        stationDate = new HashMap<>();
        parseCSVFile();
        parseJSONFile();
    }

    private double parseDouble(String str) {
        String value = str.replaceAll(",", ".").replaceAll("\"", "");
        boolean negative = !isDigit(str.charAt(0));
        value = value.replaceAll("[^\\d\\.]", "");
        if (value.isEmpty()) {
            return 0.0;
        }
        return negative ? -1.0 * Double.parseDouble(value) : Double.parseDouble(value);
    }

    private List<String> parseCSVLine(String str) {
        List<String> fields = new ArrayList<>();
        fields = Arrays.stream(str.split(",", 2)).collect(Collectors.toList());
        return fields;
    }

    private List<String> fileList(String path, String extension) {
        List<String> files = new ArrayList<>();
        try {
            files = Files.find(Path.of(path), Integer.MAX_VALUE,
                            (p, attr) -> attr.isRegularFile()
                                    && p.toString().endsWith(extension))
                    .map(p ->p.toString()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    private void parseCSVFile() {
        List<String> filesCSV = fileList(path, ".csv");
        filesCSV.forEach(str -> {
            loadStationFromCSV(str);
        });
    }

    private void loadStationFromCSV(String path) {
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
                    String[] fields = lines.get(0).split(",");
            if (!fields[0].equals(CSV_FIELD_NAME) && !fields[0].equals(CSV_FIELD_NAME_2)) {
                return;
            }
            for (int i = 1; i < lines.size(); i++) {
                List<String> values = parseCSVLine(lines.get(i));
                if (fields[1].equals(CSV_FIELD_DEPTH)) {
                    double depth = parseDouble(values.get(1));
                    stationDepth.put(values.get(0), depth);
                }
                if (fields[1].equals(CSV_FIELD_DATE)) {
                    String dateFormat = "dd.MM.yyyy";
                    Date date = new SimpleDateFormat(dateFormat).parse(values.get(1));
                    stationDate.put(values.get(0), date);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error read file: " + path);
        }
    }

    private void parseJSONFile() {
        List<String> filesJSON = fileList(path, ".json");
        filesJSON.forEach(str -> {
            loadStationFromJSON(str);
        });
    }

    private void loadStationFromJSON(String path) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray stationsArray = (JSONArray) parser.parse(new FileReader(path));
            JSONObject stationsObject = (JSONObject) stationsArray.get(0);
            Set<String> keys = stationsObject.keySet();
            String nameKey =  keys.contains(JSON_FIELD_NAME) ? JSON_FIELD_NAME :
                    keys.contains(JSON_FIELD_NAME2) ? JSON_FIELD_NAME2 : null;
            if (nameKey == null) {
                System.out.println("Illegal JSON file format: " + path);
                return;
            }
            if (keys.contains(JSON_FIELD_DATE)) {
                parseStationDate(stationsArray, nameKey, JSON_FIELD_DATE);
            }
            if (keys.contains(JSON_FIELD_DEPTH)) {
                parseStationDepth(stationsArray, nameKey, JSON_FIELD_DEPTH);
            } else if (keys.contains(JSON_FIELD_DEPTH2)) {
                parseStationDepth(stationsArray, nameKey, JSON_FIELD_DEPTH2);
            }
        } catch (Exception e) {
            System.out.println("File read error: " + path);
        }
    }

    private void parseStationDate(JSONArray stationsArray, String nameKey, String dateKey) throws ParseException {
        for (Object s : stationsArray) {
            JSONObject station = (JSONObject) s;
            String name = (String) station.get(nameKey);
            String dateFormat = "dd.MM.yyyy";
            Date date = new SimpleDateFormat(dateFormat).parse((String) station.get(dateKey));
            stationDate.put(name, date);
        }
    }

    private void parseStationDepth(JSONArray stationsArray, String nameKey, String depthKey) {
        for (Object s : stationsArray) {
            JSONObject station = (JSONObject) s;
            String name = (String) station.get(nameKey);
            double depth = parseDouble(String.valueOf(station.get(depthKey)));
            stationDepth.put(name, depth);
        }
    }

}
