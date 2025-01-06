/*
This program generates test data for the airline reservation system. 
It generates 1000 flights with random origins, destinations, dates, prices, 
and seat counts, and writes them to ProjectData/flights.txt. Also it writes 
a list of airport codes to ProjectData/airports.txt. Finally it generates 500 
reservations with random customer IDs and flight segments, and writes them to 
ProjectData/reservations.txt. The references and ideas for this implementation
are general knowledge and can be found in the Java documentation. The implementation
was done in the most part using AI generated code and some modifications were made
to make it usable for my instance of the project.
*/


import java.io.BufferedWriter; // Import the BufferedWriter class
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class
import java.util.Arrays; // Import the Arrays class
import java.util.List; // Import the List class
import java.util.Random; // Import the Random class
import java.time.LocalDate; // Import the LocalDate class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.time.temporal.ChronoUnit; // Import the ChronoUnit class

public class GenerateTestData {

    // Define the attributes needed for the GenerateTestData class(they have to be static and final)
    private static final List<String> airports = Arrays.asList("JFK", "LAX", "ORD", "SEA", "MIA", "SFO", "DFW", "BOS", "ATL", "DEN");
    private static final LocalDate startDate = LocalDate.of(2024, 1, 1);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Random random = new Random();

    public static void main(String[] args) {
        generateAirports();
        generateFlights();
        generateReservations();
    }

    // Define the method for generating the airports
    private static void generateAirports() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProjectData/airports.txt"))) {
            writer.write(String.join(",", airports));
        } catch (IOException e) {
            System.err.println("Error writing airports: " + e.getMessage());
        }
    }


    // Define the method for generating the flights
    private static void generateFlights() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProjectData/flights.txt"))) {
            for (int i = 0; i < 1000; i++) {
                String origin = airports.get(random.nextInt(airports.size()));
                String dest;
                do {
                    dest = airports.get(random.nextInt(airports.size()));
                } while (origin.equals(dest));
                String date = startDate.plus(random.nextInt(365), ChronoUnit.DAYS).format(formatter);
                double price = 200 + (800 * random.nextDouble());
                int seats = 50 + random.nextInt(151);
                writer.write(String.format("%s,%s,%s,%.2f,%d%n", origin, dest, date, price, seats));
            }
        } catch (IOException e) {
            System.err.println("Error writing flights: " + e.getMessage());
        }
    }


    // Define the method for generating the reservations
    private static void generateReservations() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProjectData/reservations.txt"))) {
            List<String> flights = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("ProjectData/flights.txt"));
            for (int i = 0; i < 500; i++) {
                String customerID = String.format("CUST%03d", i);
                String flight = flights.get(random.nextInt(flights.size()));
                String[] flightData = flight.split(",");
                writer.write(String.format("%s,%s-%s-%s,%s%n", customerID, flightData[0], flightData[1], flightData[2], flightData[2]));
            }
        } catch (IOException e) {
            System.err.println("Error writing reservations: " + e.getMessage());
        }
    }
}