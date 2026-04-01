import java.util.ArrayList;
import java.util.List;

public class TrainApp {
    public static void main(String[] args) {
        // UC1: Initialize
        System.out.println("=== Train Consist Management App ===");
        List<String> passengerBogies = new ArrayList<>();
        System.out.println("Initial Bogie Count: " + passengerBogies.size());

        // UC2: Add Passenger Bogies (Create)
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        System.out.println("Bogies added: " + passengerBogies);

        passengerBogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + passengerBogies);

        boolean hasSleeper = passengerBogies.contains("Sleeper");
        System.out.println("Does Sleeper exist? " + hasSleeper);

        System.out.println("Final Train Consist: " + passengerBogies);
        System.out.println("Program continues...");
    }
}
