import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TrainApp {
    public static void main(String[] args) {

        // --- UC1: Initialize Train ---
        System.out.println("=== Train Consist Management App ===");
        List<String> passengerBogies = new ArrayList<>();
        System.out.println("Initial Bogie Count: " + passengerBogies.size());


        // --- UC2: ArrayList Operations (Dynamic CRUD) ---
        System.out.println("\n--- UC2: Managing Passenger Bogies ---");
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        passengerBogies.remove("AC Chair");
        System.out.println("Current Bogie List: " + passengerBogies);


        // --- UC3: HashSet (Unique Bogie IDs) ---
        System.out.println("\n--- UC3: Registering Unique Bogie IDs ---");
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // Duplicate ignored
        System.out.println("Unique IDs (Unordered): " + bogieIds);


        // --- UC4: LinkedList (Physical Chaining) ---
        System.out.println("\n--- UC4: Physical Train Chaining (LinkedList) ---");
        LinkedList<String> trainSequence = new LinkedList<>();
        trainSequence.add("Engine");
        trainSequence.add("Sleeper");
        trainSequence.add("Cargo");
        trainSequence.add(1, "Pantry Car"); // Insert in middle
        System.out.println("Ordered Sequence: " + trainSequence);


        // --- UC5: LinkedHashSet (Ordered Uniqueness) ---
        System.out.println("\n--- UC5: Preserve Insertion Order (LinkedHashSet) ---");

        Set<String> trainFormation = new LinkedHashSet<>();

        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        System.out.println("Attempting to re-attach duplicate: Sleeper...");
        trainFormation.add("Sleeper");

        System.out.println("Final Formation Order: " + trainFormation);
        System.out.println("Integrity Check: No duplicates allowed, order preserved.");
    }
}
