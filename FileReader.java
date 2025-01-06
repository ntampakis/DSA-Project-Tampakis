package util; // Package declaration

import java.io.*; // Import the IOException class
import java.util.*; // Import the ArrayList class

public class FileReader {
    public static List<String[]> readCSV(String filename) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("ProjectData/" + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return records;
    }
}

/*
The FileReader class provides a static method readCSV that reads a CSV file 
and returns a list of string arrays. The method takes a filename as a parameter 
and reads the file line by line, splitting each line by commas and adding the 
resulting array to the list of records. If an IOException occurs during the file 
reading process, an error message is printed to the standard error stream. The 
method returns the list of records after reading the entire file. The class is
not mine and I used documentation and AI generated code to implement it. It was
the best way, to read the CSV files and get the data I needed for the project, I found.
 */