import java.util.*; // Import the HashMap class

public class GraphOfFlights {
    // Define the flights map and airportList
    private Map<String, Flight> flights;
    private AirportList airportList;

    // Define the constructor
    public GraphOfFlights(AirportList airportList) {
        this.flights = new HashMap<>();
        this.airportList = airportList;
    }
    // Define the addFlight method
    public boolean addFlight(String origin, String destination, String date, double basePrice, int seats) {
        if (!airportList.containsAirport(origin) || !airportList.containsAirport(destination)) {
            throw new IllegalArgumentException("Origin or destination airport does not exist");
        }

        String flightCode = origin + "-" + destination + "-" + date;
        if (flights.containsKey(flightCode)) {
            return false;
        }

        Flight flight = new Flight(origin, destination, date, basePrice, seats);
        flights.put(flightCode, flight);
        return true;
    }
    // Define the removeFlight method
    public boolean removeFlight(String flightCode) {
        if (!flights.containsKey(flightCode)) {
            return false;
        }
        flights.remove(flightCode);
        return true;
    }

    public List<Flight> searchFlights(String origin, String destination, String date) {
        System.out.println("Searching flights from " + origin + " to " + destination + " on " + date);
        List<Flight> results = new ArrayList<>();
        
        // Direct flights
        for (Flight flight : flights.values()) {
            if (flight.getOrigin().equals(origin) && 
                flight.getDestination().equals(destination) && 
                flight.getDate().equals(date)) {
                results.add(flight);
            }
        }

        // One-stop flights
        for (Flight first : flights.values()) {
            if (first.getOrigin().equals(origin) && first.getDate().equals(date)) {
                for (Flight second : flights.values()) {
                    if (second.getOrigin().equals(first.getDestination()) && 
                        second.getDestination().equals(destination) && 
                        second.getDate().equals(date)) {
                        results.add(first); // Add connecting flights
                        results.add(second);
                    }
                }
            }
        }

        return results;
    }
    // Define the updateSeats method
    public boolean updateSeats(String flightCode, int seatsToBook) {
        Flight flight = flights.get(flightCode);
        if (flight == null || flight.getAvailableSeats() < seatsToBook) {
            return false;
        }
        
        flight.setAvailableSeats(flight.getAvailableSeats() - seatsToBook);
        return true;
    }
    // Define the getFlight method
    public Flight getFlight(String flightCode) {
        return flights.get(flightCode);
    }
    // Define the flightExists method
    public boolean flightExists(String flightCode) {
        return flights.containsKey(flightCode);
    }
}

/*
The GraphOfFlights class represents a graph of flights with methods to add, remove, search, and update flights.
The class uses a HashMap to store flights which is the ADT needed to store the flights and the one I decided to 
use outside of the lecture notes. The class also uses an AirportList object to validate the origin and destination
airports when adding a flight. 
 */