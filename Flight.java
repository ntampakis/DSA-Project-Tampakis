public class FlightNode {

    // Define the attributes
    private String origin;
    private String destination;
    private String date;
    private double basePrice;
    private int availableSeats;
    private String flightCode;

    // Define the constructor
    public Flight(String origin, String destination, String date, double basePrice, int seats) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.basePrice = basePrice;
        this.availableSeats = seats;
        this.flightCode = origin + "-" + destination + "-" + date;
    }

    // Getters and setters
    public String getFlightCode() { 
        return flightCode; 
    }
    public String getOrigin() { 
        return origin; 
    }
    public String getDestination() { 
        return destination; 
    }
    public String getDate() { 
        return date; 
    }
    public double getBasePrice() {
         return basePrice; 
    }
    public int getAvailableSeats() { 
        return availableSeats; 
    }
    public void setAvailableSeats(int seats) {
         this.availableSeats = seats; 
    }
}

/*
The Flight class defines a flight object with attributes for the origin, 
destination, date, base price, and available seats. It provides getter and
setter methods to access and modify these attributes. 

 */
