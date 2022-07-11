import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> flightList = findPlanesLeavingInTheNextTwoHours(airport);
        flightList.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        return airport.getTerminals().stream()
                .flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE)
                        && inTwoHours(f.getDate()))
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