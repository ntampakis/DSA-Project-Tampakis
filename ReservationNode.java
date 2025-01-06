import java.util.List; // Import List class 

public class ReservationNode {

    // Define attributes
    private String reservationID;
    private String customerID;
    private FlightSegmentsList flightSegments;
    private String date;
    private double totalPrice;
    private ReservationNode next;

    // Constructor
    public ReservationNode(String customerID, FlightSegmentsList flightSegments, String date, double price) {
        this.customerID = customerID;
        this.flightSegments = flightSegments;
        this.date = date;
        this.totalPrice = price;
        this.reservationID = customerID + "-" + date + "-" + System.currentTimeMillis();
        this.next = null;
    }

    // Getters and Setters
    public String getReservationID() { 
        return reservationID; 
    }

    public String getCustomerID() { 
        return customerID; 
    }

    public List<String> getFlightSegments() { 
        return flightSegments.getSegments(); 
    }

    public String getDate() { 
        return date; 
    }

    public double getTotalPrice() { 
        return totalPrice; 
    }

    public ReservationNode getNext() { 
        return next; 
    }

    public void setNext(ReservationNode next) { 
        this.next = next; 
    }
}

/*
The ReservationNode class represents a node in a linked list of reservations. 
Each node contains information about a reservation, including the customer ID, 
flight segments, date, total price, and a unique reservation ID. The class 
provides methods to access and modify this information, as well as to link 
nodes together in a linked list structure.
 */