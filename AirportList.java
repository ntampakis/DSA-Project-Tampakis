import java.util.*; // Import the List and ArrayList classes

public class AirportList {
    private Node head;
    
    // Define the Airport List Constructor
    public AirportList() {
        this.head = null;
    }
    // Define the addAirport Method
    public boolean addAirport(String airportCode) {
        if (airportCode == null || airportCode.trim().isEmpty()) {
            return false;
        }
        
        if (containsAirport(airportCode)) {
            return false;
        }
        
        Node newNode = new Node(airportCode);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        return true;
    }
    // Define the removeAirport Method
    public boolean removeAirport(String airportCode) {
        if (head == null || airportCode == null) {
            return false;
        }
        
        if (head.airportCode.equals(airportCode)) {
            head = head.next;
            return true;
        }
        
        Node current = head;
        while (current.next != null && !current.next.airportCode.equals(airportCode)) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }
        
        return false;
    }
    // Define the listOfAirports Method
    public String[] listOfAirports() {
        List<String> airports = new ArrayList<>();
        Node current = head;
        
        while (current != null) {
            airports.add(current.airportCode);
            current = current.next;
        }
        
        return airports.toArray(new String[0]);
    }
    // Define the containsAirport Method
    public boolean containsAirport(String airportCode) {
        Node current = head;
        while (current != null) {
            if (current.airportCode.equals(airportCode)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

/*
This class is used to store a list of airport codes in a linked list. 
It provides methods to add and remove airports, get a list of airports,
and check if an airport is in the list. The class uses a Node 
class to represent each airport in the list.
 */