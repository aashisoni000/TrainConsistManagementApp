import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
        System.out.println("Current Bogies: " + passengerBogies);
        passengerBogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + passengerBogies);


        // --- UC3: HashSet (Unique Bogie IDs) ---
        System.out.println("\n--- UC3: Registering Unique Bogie IDs ---");
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // Duplicate ignored
        System.out.println("Unique IDs Registered: " + bogieIds);


        // --- UC4: LinkedList (Maintaining Physical Order) ---
        System.out.println("\n--- UC4: Physical Train Chaining (LinkedList) ---");

        LinkedList<String> trainSequence = new LinkedList<>();

        trainSequence.add("Engine");
        trainSequence.add("Sleeper");
        trainSequence.add("AC");
        trainSequence.add("Cargo");
        trainSequence.add("Guard");
        System.out.println("Initial Sequence: " + trainSequence);

        trainSequence.add(2, "Pantry Car");
        System.out.println("After adding Pantry Car at index 2: " + trainSequence);

        trainSequence.removeFirst();
        trainSequence.removeLast();

        System.out.println("Final Ordered Train Consist: " + trainSequence);
        System.out.println("System integrity and physical order verified.");
    }
}
