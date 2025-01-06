import java.util.Arrays; // Import the Arrays class
import java.util.List; // Import the List class
import util.FileReader; // Import the FileReader class
import java.util.*; // Import the ArrayList class

public class Main {

    // Define the static attributes needed fot the main class
    private static AirportList airports;
    private static GraphOfFlights flights;
    private static ReservationList reservations;

    public static void main(String[] args) {

        // Calling the methods to initialize the system, load the data and run the performance tests
        initializeSystem();
        loadData();
        runPerformanceTests();
    }

    private static void initializeSystem() {
        airports = new AirportList();
        flights = new GraphOfFlights(airports);
        reservations = new ReservationList(flights);
    }

    
    //Load data
    private static void loadData() {
        // Load airports
        List<String[]> airportData = FileReader.readCSV("Airports.txt");
        for (String[] data : airportData) {
            for (String airport : data) {
                airports.addAirport(airport.trim());
            }
        }

        // Load flights
        List<String[]> flightData = FileReader.readCSV("Flights.txt");
        for (String[] data : flightData) {
            System.out.println("Loading flight: " + Arrays.toString(data));
            flights.addFlight(
                data[0].trim(),
                data[1].trim(),
                data[2].trim(),
                Double.parseDouble(data[3].trim()),
                Integer.parseInt(data[4].trim())
            );
        }

        // Load reservations
        List<String[]> reservationData = FileReader.readCSV("Reservations.txt");
        for (String[] data : reservationData) {
            FlightSegmentsList segments = new FlightSegmentsList();
            segments.addSegment(data[1].trim());
            System.out.println("Creating reservation: " + Arrays.toString(data));
            reservations.createReservation(
                data[0].trim(),
                segments,
                data[2].trim()
            );
        }
    }

    private static void runPerformanceTests() {
        // Test flight search performance
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            flights.searchFlights("JFK", "LAX", "2024-05-01");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Flight search performance (1000 searches): " + (endTime - startTime) + "ms");

        // Print system statistics
        System.out.println("\nSystem Statistics:");
        System.out.println("Total Airports: " + airports.listOfAirports());
        System.out.println("Total Flights: " + flights.searchFlights("", "", "").size());
        
        // Test specific operations
        testSpecificOperations();
    }

    // Define the testSpecificOperations Method
    private static void testSpecificOperations() {
        System.out.println("\nTesting Specific Operations:");
        
        // Test flight search
        List<Flight> searchResults = flights.searchFlights("JFK", "LAX", "2024-05-01");
        System.out.println("Found " + searchResults.size() + " direct flights from JFK to LAX");

        // Test reservation creation
        try {
            FlightSegmentsList segments = new FlightSegmentsList();
            segments.addSegment("JFK-LAX-2024-05-01");
            String reservationID = reservations.createReservation("CUST002", segments, "2024-05-01");
            System.out.println("Created new reservation: " + reservationID);
        } catch (Exception e) {
            System.err.println("Error creating reservation: " + e.getMessage());
        }
    }
}

/*
 The outcome of the code is that it will read data from the files airports.txt, flights.txt, 
 and reservations.txt, and load them into the system. It will then run performance tests on the system, 
 including flight search performance and specific operations. The specific operations include 
 testing flight search and reservation creation. The system statistics will also be printed, 
 showing the total number of airports and flights in the system. It is also creating a reservation for 
 a customer with the ID CUST001 for a flight from JFK to LAX on 2024-05-01. 
----------------------------------------------------------------------------------------------------------
Loading flight: [JFK, LAX, 2024-05-01, 300.0, 100]
Loading flight: [LAX, ORD, 2024-05-01, 250.0, 100]
Loading flight: [ORD, SEA, 2024-05-01, 200.0, 100]
Loading flight: [SEA, MIA, 2024-05-01, 350.0, 100]
Loading flight: [MIA, SFO, 2024-05-01, 400.0, 100]
Loading flight: [SFO, DFW, 2024-05-01, 450.0, 100]
Loading flight: [DFW, BOS, 2024-05-01, 500.0, 100]
Loading flight: [BOS, ATL, 2024-05-01, 550.0, 100]
Loading flight: [ATL, DEN, 2024-05-01, 600.0, 100]
Loading flight: [DEN, JFK, 2024-05-01, 650.0, 100]
Loading flight: [JFK, ORD, 2024-05-02, 300.0, 100]
Loading flight: [ORD, LAX, 2024-05-02, 250.0, 100]
Loading flight: [LAX, SEA, 2024-05-02, 200.0, 100]
Loading flight: [SEA, MIA, 2024-05-02, 350.0, 100]
Loading flight: [MIA, SFO, 2024-05-02, 400.0, 100]
Loading flight: [SFO, DFW, 2024-05-02, 450.0, 100]
Loading flight: [DFW, BOS, 2024-05-02, 500.0, 100]
Loading flight: [BOS, ATL, 2024-05-02, 550.0, 100]
Loading flight: [ATL, DEN, 2024-05-02, 600.0, 100]
Loading flight: [DEN, JFK, 2024-05-02, 650.0, 100]
Creating reservation: [CUST001, JFK-LAX-2024-05-01, 2024-05-01]
Searching flights from JFK-LAX-2024-05-01 to JFK-LAX-2024-05-01 on 2024-05-01
Exception in thread "main" java.lang.IllegalArgumentException: No flights found for the specified route
-----------------------------------------------------------------------------------------------------------
Also if you want more test data generated at random you can itialize the GenerateTestData file.
It is located with the name GenerateTestData.java in the same directory as the 
other files. In order to use it you need to run it first in order for it to generate the test data
and then you can run the Main.java file to test the system. I tested it in my local machine and it worked. 
I won't post the results here because it is a lot of data but you can try it in your local machine if you want to.
*/