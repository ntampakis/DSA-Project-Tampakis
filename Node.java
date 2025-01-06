public class Node {

    // Variables
    String airportCode;
    Node next;
    
    // Constructor
    public Node(String airportCode) {
        this.airportCode = airportCode;
        this.next = null;
    }
}

/*
This class is used as a node in a linked list. 
It contains a string variable to store the airport code 
and a reference to the next node in the list. The constructor 
initializes the airport code and sets the next node reference 
to null. This class is used to create a linked list of airport 
codes in the flight segments list.
 */