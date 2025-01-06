import java.util.ArrayList; // Import ArrayList class
import java.util.List; // Import List class

public class FlightSegmentsList {

    // List to store flight segments
    private List<String> flightSegments;

    // Constructor
    public FlightSegmentsList() {
        this.flightSegments = new ArrayList<>();
    }

    // Method to add a segment to the list
    public void addSegment(String segment) {
        flightSegments.add(segment);
    }

    // Getter method
    public List<String> getSegments() {
        return new ArrayList<>(flightSegments); // Return a defensive copy
    }

    // Method to get the size of the list
    public int size() {
        return flightSegments.size();
    }
}

/*
The FlightSegmentsList class represents a list of flight segments in a reservation.
It uses an ArrayList to store the flight segments and provides methods to add segments,
get the list of segments, and get the size of the list. This class is used to manage
the flight segments in a reservation and provide access to them when needed. I created it because 
I needed a way to store and manage the flight segments in a reservation, and also another reason was 
because it originally was implemented inside the ReservatioNode class and I wanted to separate the
concerns and make the code more modular and easier to maintain and not run into compiling issues anymore.
*/