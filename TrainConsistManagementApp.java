import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainApp {
    public static void main(String[] args) {

        // --- UC1: Initialize Train and Display Consist Summary ---
        System.out.println("=== Train Consist Management App ===");

        List<String> passengerBogies = new ArrayList<>();

        System.out.println("Initial Bogie Count: " + passengerBogies.size());


        // --- UC2: Add Passenger Bogies (ArrayList Operations) ---
        System.out.println("\n--- Adding Passenger Bogies ---");
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        System.out.println("Current Bogies: " + passengerBogies);

        passengerBogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + passengerBogies);

        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Status: Sleeper bogie is attached.");
        }


        // --- UC3: Track Unique Bogie IDs (Set – HashSet) ---
        System.out.println("\n--- Registering Unique Bogie IDs ---");
        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");

        System.out.println("Registered Unique IDs: " + bogieIds);
        System.out.println("Total Unique Count: " + bogieIds.size());

        System.out.println("\nSystem initialized and ready for deployment.");
    }
}
