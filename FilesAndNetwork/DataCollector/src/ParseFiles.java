import core.Station;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Character.isDigit;

public class ParseFiles {

    private static final String PATH = "data/";
    private static final String CSV_FIELD_NAME = "Название станции";
    private static final String CSV_FIELD_NAME_2 = "Название";
    private static final String CSV_FIELD_DEPTH = "Глубина";
    private static final String CSV_FIELD_DATE = "Дата открытия";
    private static final List<String> CSV_FIELDS = Arrays.asList(CSV_FIELD_NAME, CSV_FIELD_NAME_2, CSV_FIELD_DEPTH, CSV_FIELD_DATE);
    private static List<Station> stations;

    public static List<String> fileList(String path, String extension) {
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

    public static void parseCSVfile() {
        List<String> filesCSV = fileList(PATH, ".csv");
        filesCSV.forEach(str -> {
            loadStationFromCSV(str);
        });
    }

    public static void parseMetroStation(List<Station> stations) {
        ParseFiles.stations = stations;
        parseCSVfile();
    }

    private static double parseDouble(String str) {
        String value = str.replaceAll(",", ".").replaceAll("\"", "");
        boolean negative = !isDigit(str.charAt(0));
        value = value.replaceAll("[^\\d\\.]", "");
        if (value.isEmpty()) {
            return 0.0;
        }
        return negative ? -1.0 * Double.parseDouble(value) : Double.parseDouble(value);
    }

    private static List<String> parseCSVLine(String str) {
        List<String> fields = new ArrayList<>();
        fields = Arrays.stream(str.split(",", 2)).collect(Collectors.toList());
        return fields;
    }

    public static void loadStationFromCSV(String path) {
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
                    System.out.println(depth);
                }
                if (fields[1].equals(CSV_FIELD_DATE)) {
                    String dateFormat = "dd.MM.yyyy";
                 //   Date date = new SimpleDateFormat(dateFormat).parse(values.get(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error read file: " + path);
        }
    }


    private static void readCSV(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            parseCsvAttributes(lines);
        } catch (IllegalArgumentException ex) {
            System.out.println(path.toString() + " - " + ex.getMessage());
        }
    }

    private static void parseCsvAttributes(List<String> lines) throws IllegalArgumentException {
        List<String> fileds = Arrays.asList(lines.get(0).split(","));
        if (fileds.size() > 2) {
            throw new IllegalArgumentException("Too many fields");
        }
        Map<String, Integer> fieldsIndexes = CSV_FIELDS.stream().collect(Collectors
                        .toMap(Function.identity(), s -> fileds.indexOf(s)));
        for (int i = 1; i < lines.size(); i++) {
            String[] args = lines.get(i).split(",");
            putStationAttribute(args, fieldsIndexes);
        }
    }

    public static void putStationAttribute(String[] args, Map<String, Integer> fieldsIndexes) throws IllegalArgumentException {
        int indexName = Math.max(fieldsIndexes.get(CSV_FIELD_NAME),
                                fieldsIndexes.get(CSV_FIELD_NAME_2));
        if (indexName < 0) {
            throw new IllegalArgumentException("Attribute NAME has not found");
        }
        Station station = stations.stream().filter(s -> s.getName()
                .equalsIgnoreCase(args[indexName])).findAny().orElse(null);
        if (station == null) {
            System.out.println("Station with name: '" + args[indexName] + "' not found");
            return;
        }

        int indexDepth = fieldsIndexes.get(CSV_FIELD_DEPTH);
        double depth = 0.0;
        if (indexDepth >= 0) {
            try {
                depth = parseDouble(args[indexDepth]);
            } catch (NumberFormatException ex) {
                System.out.println("Illegal number format: " + args[indexDepth]);
            }
            station.setDepth(depth);
            System.out.println(station);
        }

        int indexDate = fieldsIndexes.get(CSV_FIELD_DATE);
        if (indexDepth >= 0) {
            String dateFormat = "dd.MM.yyyy";
            try {
                station.setDate(new SimpleDateFormat(dateFormat).parse(args[indexDate]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void  parseJSON(File file) {

    }

}
