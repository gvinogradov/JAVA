import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> flightList = findPlanesLeavingInTheNextTwoHours(airport);
        flightList.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        return  airport.getTerminals().stream()
                .collect(Collectors.toMap(Function.identity(), Terminal::getFlights)).values()
                .stream().collect(Collectors.toList())
                .stream().flatMap(e -> e.stream())
                .filter(f -> inTwoHours(f.getDate()) && f.getType().equals(Flight.Type.DEPARTURE))
                .collect(Collectors.toList());
    }

    public static boolean inTwoHours(Date date) {
        Date dateNow = Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault()).toInstant());
        Date dateInTwoHours = Date.from(LocalDateTime.now().plusHours(2)
                .atZone(ZoneId.systemDefault()).toInstant());
        return (date.compareTo(dateNow) >= 0 && date.compareTo(dateInTwoHours) <= 0);
    }

}