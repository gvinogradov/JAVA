import core.Line;
import core.Station;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator calculator;
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Кировско-Выборгская");
        stationIndex.addStation(new Station("Площадь Ленина", line1));
        stationIndex.addStation(new Station("Чернышевская", line1));
        stationIndex.addStation(new Station("Площадь Восстания", line1));
        stationIndex.addStation(new Station("Владимирская", line1));
        stationIndex.addStation(new Station("Пушкинская", line1));
        line1.addStation(stationIndex.getStation("Площадь Ленина"));
        line1.addStation(stationIndex.getStation("Чернышевская"));
        line1.addStation(stationIndex.getStation("Площадь Восстания"));
        line1.addStation(stationIndex.getStation("Владимирская"));
        line1.addStation(stationIndex.getStation("Пушкинская"));
        stationIndex.addLine(line1);

        Line line2 = new Line(2, "Московско-Петроградская");
        stationIndex.addStation(new Station("Петроградская", line2));
        stationIndex.addStation(new Station("Горьковская", line2));
        stationIndex.addStation(new Station("Невский проспект", line2));
        stationIndex.addStation(new Station("Сенная площадь", line2));
        line2.addStation(stationIndex.getStation("Петроградская"));
        line2.addStation(stationIndex.getStation("Горьковская"));
        line2.addStation(stationIndex.getStation("Невский проспект"));
        line2.addStation(stationIndex.getStation("Сенная площадь"));
        stationIndex.addLine(line2);

        Line line3 = new Line(3, "Невско-Василеостровская");
        stationIndex.addStation(new Station("Василеостровская", line3));
        stationIndex.addStation(new Station("Гостиный двор", line3));
        stationIndex.addStation(new Station("Маяковская", line3));
        stationIndex.addStation(new Station("Площадь Александра Невского", line3));
        line3.addStation(stationIndex.getStation("Василеостровская"));
        line3.addStation(stationIndex.getStation("Гостиный двор"));
        line3.addStation(stationIndex.getStation("Маяковская"));
        line3.addStation(stationIndex.getStation("Площадь Александра Невского"));
        stationIndex.addLine(line3);

        //первое пересечение
        List<Station> connectionStations = new ArrayList<>();
        connectionStations.add(stationIndex.getStation("Площадь Восстания"));
        connectionStations.add(stationIndex.getStation("Маяковская"));
        stationIndex.addConnection(connectionStations);

        //второе пересечение
        connectionStations = new ArrayList<>();
        connectionStations.add(stationIndex.getStation("Невский проспект"));
        connectionStations.add(stationIndex.getStation("Гостиный двор"));
        stationIndex.addConnection(connectionStations);

        calculator = new RouteCalculator(stationIndex);
    }

    public void testIsConnected() {
        Set<Station> connected = stationIndex.getConnectedStations(stationIndex
                .getStation("Площадь Восстания"));
        boolean actual =  connected.contains(stationIndex.getStation("Маяковская"));
        boolean expected = true;
        assertEquals(expected, actual);
    }


    public void testCalculateDuration() {
        List<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("Василеостровская"));
        route.add(stationIndex.getStation("Гостиный двор"));
        route.add(stationIndex.getStation("Невский проспект"));
        route.add(stationIndex.getStation("Горьковская"));
        route.add(stationIndex.getStation("Петроградская"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine() {
        List<String> actual = getRouteList("Петроградская",
                "Сенная площадь", 0);
        List<String> expected = Arrays.asList("Петроградская", "Горьковская",
                                    "Невский проспект", "Сенная площадь");
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {
        List<String> actual = getRouteList("Василеостровская",
                "Сенная площадь", 1);
        List<String> expected = Arrays.asList(
                "Василеостровская",
                "Гостиный двор",
                "Невский проспект",
                "Сенная площадь");
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections() {
        List<String> actual = getRouteList("Петроградская",
                "Площадь Ленина", 2);
        List<String> expected = Arrays.asList("Петроградская", "Горьковская",
                "Невский проспект", "Гостиный двор", "Маяковская",
                "Площадь Восстания", "Чернышевская", "Площадь Ленина");
        assertEquals(expected, actual);
    }

    private  List<String> getRouteList(String source, String destination, int connectionCount) {
        List<Station> stationList = calculator.getShortestRoute(stationIndex.getStation(source),
                stationIndex.getStation(destination), connectionCount);
        return stationList.stream().map(n -> n.getName())
                .collect(Collectors.toList());
    }

}

