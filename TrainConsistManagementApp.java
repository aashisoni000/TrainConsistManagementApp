import java.util.*;

// UC7: Supporting Class for Object-based collection handling
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bogie: " + name + " (Capacity: " + capacity + ")";
    }
}

public class TrainApp {
    public static void main(String[] args) {

        // --- UC1: Initialize Train ---
        System.out.println("=== Train Consist Management App ===");
        List<String> simpleBogies = new ArrayList<>();
        System.out.println("Initial Bogie Count: " + simpleBogies.size());


        // --- UC2: ArrayList Operations ---
        System.out.println("\n--- UC2: Managing Passenger Bogies ---");
        simpleBogies.add("Sleeper");
        simpleBogies.add("AC Chair");
        simpleBogies.add("First Class");
        simpleBogies.remove("AC Chair");
        System.out.println("Current Bogie List: " + simpleBogies);


        // --- UC3: HashSet (Unique IDs) ---
        System.out.println("\n--- UC3: Registering Unique Bogie IDs ---");
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // Duplicate ignored
        System.out.println("Unique IDs (Unordered): " + bogieIds);


        // --- UC4: LinkedList (Physical Chaining) ---
        System.out.println("\n--- UC4: Physical Train Chaining ---");
        LinkedList<String> trainSequence = new LinkedList<>();
        trainSequence.add("Engine");
        trainSequence.add("Sleeper");
        trainSequence.add("Cargo");
        trainSequence.add(1, "Pantry Car");
        System.out.println("Ordered Sequence: " + trainSequence);


        // --- UC5: LinkedHashSet (Ordered Uniqueness) ---
        System.out.println("\n--- UC5: Preserve Insertion Order ---");
        Set<String> trainFormation = new LinkedHashSet<>();
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");
        System.out.println("Final Formation Order: " + trainFormation);


        // --- UC6: HashMap (Mapping Bogie to Capacity) ---
        System.out.println("\n--- UC6: Bogie Capacity Mapping (HashMap) ---");
        Map<String, Integer> bogieCapacityMap = new HashMap<>();
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 56);
        bogieCapacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println("Bogie Type: " + entry.getKey() + " | Seats: " + entry.getValue());
        }


        // --- UC7: Sort Bogies by Capacity (Comparator) ---
        System.out.println("\n--- UC7: Sorting Bogies by Capacity (Comparator) ---");

        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 24));

        passengerBogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Bogies Sorted by Capacity (Low to High):");
        for (Bogie b : passengerBogies) {
            System.out.println(b);
        }

        System.out.println("\nOperational planning complete. Program continues...");
    }
}
