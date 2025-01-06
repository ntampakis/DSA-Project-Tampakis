import java.util.*; // Import the HashMap, Map, and Set classes

public class ReservationList {
    private Map<String, ReservationNode> flightReservations; // Dictionary of linked lists
    private GraphOfFlights flights;

    // Define the ReservationList Constructor
    public ReservationList(GraphOfFlights flights) {
        this.flightReservations = new HashMap<>();
        this.flights = flights;
    }

    // Define the createReservation Method
    public String createReservation(String customerID, FlightSegmentsList flightSegments, String date) {
        // Validate flight segments and availability
        for (String flightCode : flightSegments.getSegments()) {
            if (!flights.flightExists(flightCode)) {
                throw new IllegalArgumentException("Flight " + flightCode + " does not exist");
            }
            if (!flights.updateSeats(flightCode, 1)) {
                throw new IllegalArgumentException("No seats available on flight " + flightCode);
            }
        }

        double totalPrice = calculatePrice(flightSegments.getSegments().get(0), 
            flightSegments.getSegments().get(flightSegments.size() - 1), 
            date);
        
        ReservationNode newReservation = new ReservationNode(customerID, flightSegments, date, totalPrice);
        
        // Add to all relevant flight lists
        for (String flightCode : flightSegments.getSegments()) {
            if (!flightReservations.containsKey(flightCode)) {
                flightReservations.put(flightCode, newReservation);
            } else {
                // Add to end of linked list
                ReservationNode current = flightReservations.get(flightCode);
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newReservation);
            }
        }
        return newReservation.getReservationID();
    }

    // Define the calculatePrice Method
    public double calculatePrice(String origin, String destination, String date) {
        // Validate inputs
        if (origin == null || destination == null || date == null) {
            throw new IllegalArgumentException("Invalid parameters for price calculation");
        }

        // Get flight(s) for the route
        List<Flight> routeFlights = flights.searchFlights(origin, destination, date);
        if (routeFlights.isEmpty()) {
            throw new IllegalArgumentException("No flights found for the specified route");
        }

        double totalPrice = 0.0;
        double utilizationMultiplier = 1.0;

        for (Flight flight : routeFlights) {
            // Get base price
            double basePrice = flight.getBasePrice();
            
            // Calculate seat utilization factor (higher price for fewer seats)
            int availableSeats = flight.getAvailableSeats();
            utilizationMultiplier = 1.0 + ((100 - availableSeats) / 100.0);
            
            // Calculate segment price
            double segmentPrice = basePrice * utilizationMultiplier * seasonalMultiplier;
            totalPrice += segmentPrice;
        }

        // Apply connection discount if multiple flights
        if (routeFlights.size() > 1) {
            totalPrice *= 0.9; // 10% discount for connecting flights
        }

        return Math.round(totalPrice * 100.0) / 100.0; // Round to 2 decimal places
    }

    


    // Define the cancelReservation Method
    public boolean cancelReservation(String reservationID) {
        boolean found = false;
        
        for (Map.Entry<String, ReservationNode> entry : flightReservations.entrySet()) {
            ReservationNode current = entry.getValue();
            ReservationNode prev = null;
            
            while (current != null) {
                if (current.getReservationID().equals(reservationID)) {
                    // Free up seats
                    for (String flightCode : current.getFlightSegments()) {
                        flights.updateSeats(flightCode, -1); // Return seat
                    }
                    
                    // Remove from linked list
                    if (prev == null) {
                        flightReservations.put(entry.getKey(), current.getNext());
                    } else {
                        prev.setNext(current.getNext());
                    }
                    found = true;
                    break;
                }
                prev = current;
                current = current.getNext();
            }
        }
        
        return found;
    }


    // Define the listReservations Method
    public List<ReservationNode> listReservations(String customerID) {
        List<ReservationNode> customerReservations = new ArrayList<>();
        Set<String> addedReservations = new HashSet<>(); // To avoid duplicates
        
        for (ReservationNode node : flightReservations.values()) {
            while (node != null) {
                if (node.getCustomerID().equals(customerID) && 
                    !addedReservations.contains(node.getReservationID())) {
                    customerReservations.add(node);
                    addedReservations.add(node.getReservationID());
                }
                node = node.getNext();
            }
        }
        
        return customerReservations;
    }


    // Define the listReservationsByFlight Method
    public List<ReservationNode> listReservationsByFlight(String flightCode) {
        List<ReservationNode> flightReservationList = new ArrayList<>();
        ReservationNode current = flightReservations.get(flightCode);
        
        while (current != null) {
            flightReservationList.add(current);
            current = current.getNext();
        }
        
        return flightReservationList;
    }


/*
The ReservationList class manages a collection of flight reservations using a dictionary 
of linked lists. It provides methods to create, cancel, and list reservations, as well as
to calculate the price of a reservation. The createReservation method validates the flight
segments and availability before creating a new reservation. The calculatePrice method
calculates the total price of a reservation based on the flight segments, date, and seat
availability. The cancelReservation method cancels a reservation and frees up the seats
on the corresponding flights. The listReservations method returns a list of reservations
for a given customer ID, while the listReservationsByFlight method returns a list of
reservations for a specific flight. The ReservationList class is used to manage the
reservation system in the flight booking application. This class also has the MAP and SET
imported to use the HashMap, Map, and Set classes to store the reservations and avoid duplicates 
and it was a way to get more ADTs not shown in class and fitted way better for this insance.
 */