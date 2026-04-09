import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// UC14: Custom Exception Class
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// UC7 & UC14: Updated Bogie class with validation
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bogie: " + name + " (Capacity: " + capacity + ")";
    }
}

// UC12: Supporting Class for Goods bogies
class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "GoodsBogie: " + type + " [Cargo: " + cargo + "]";
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
        bogieIds.add("BG101");
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
        try {
            passengerBogies.add(new Bogie("Sleeper", 72));
            passengerBogies.add(new Bogie("Sleeper", 72));
            passengerBogies.add(new Bogie("AC Chair", 56));
            passengerBogies.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) {
            System.out.println("Error creating bogies: " + e.getMessage());
        }

        passengerBogies.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("Bogies Sorted by Capacity (Low to High):");
        passengerBogies.forEach(System.out::println);

        // --- UC8: Filter Passenger Bogies Using Streams ---
        System.out.println("\n--- UC8: Filter Bogies (Capacity > 60) Using Streams ---");
        List<Bogie> filteredBogies = passengerBogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        System.out.println("Filtered Bogies (High Capacity Only):");
        filteredBogies.forEach(System.out::println);

        // --- UC9: Group Bogies by Type (Collectors.groupingBy) ---
        System.out.println("\n--- UC9: Grouping Bogies by Category ---");
        Map<String, List<Bogie>> groupedBogies = passengerBogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
        groupedBogies.forEach((type, list) -> System.out.println("Category: [" + type + "] | Count: " + list.size()));

        // --- UC10: Count Total Seats in Train (reduce) ---
        System.out.println("\n--- UC10: Aggregate Total Seating Capacity ---");
        int totalSeatingCapacity = passengerBogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
        System.out.println("Total Seating Capacity of the Train: " + totalSeatingCapacity + " seats");

        // --- UC11: Validate Train ID & Cargo Codes (Regex) ---
        System.out.println("\n--- UC11: Validate Train ID & Cargo Codes (Regex) ---");
        String trainIdInput = "TRN-1234";
        Pattern trainIdPattern = Pattern.compile("TRN-\\d{4}");
        if (trainIdPattern.matcher(trainIdInput).matches()) {
            System.out.println("Valid Train ID: " + trainIdInput);
        } else {
            System.out.println("Invalid Train ID: " + trainIdInput);
        }

        // --- UC12: Safety Compliance Check for Goods Bogies ---
        System.out.println("\n--- UC12: Enforcing Safety Rules (allMatch) ---");
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));
        System.out.println("Train Safety Check: " + (isSafe ? "PASSED" : "FAILED"));

        // --- UC13: Performance Comparison (Loops vs Streams) ---
        System.out.println("\n--- UC13: Performance Benchmarking (Loops vs Streams) ---");
        long startLoop = System.nanoTime();
        List<Bogie> loopFiltered = new ArrayList<>();
        for (Bogie b : passengerBogies) { if (b.capacity > 60) loopFiltered.add(b); }
        long endLoop = System.nanoTime();
        System.out.println("Loop Execution Time: " + (endLoop - startLoop) + " ns");

        long startStream = System.nanoTime();
        passengerBogies.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
        long endStream = System.nanoTime();
        System.out.println("Stream Execution Time: " + (endStream - startStream) + " ns");

        // --- UC14: Handle Invalid Bogie Capacity (Custom Exception) ---
        System.out.println("\n--- UC14: Testing Custom Exception (Invalid Capacity) ---");
        try {
            System.out.println("Attempting to create bogie with capacity -10...");
            new Bogie("InvalidBogie", -10);
        } catch (InvalidCapacityException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        System.out.println("\nAggregation complete. System finalized. All Use Cases complete.");
    }
}
