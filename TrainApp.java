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

// UC15: Custom Runtime Exception Class
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// UC7 & UC14: Passenger Bogie with validation
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

// UC12 & UC15: Goods Bogie with Safety Validation
class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type) {
        this.type = type;
    }

    // UC15: Method with Safety logic and structured handling
    public void assignCargo(String cargo) {
        try {
            if (this.type.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Safety Violation: Petroleum cannot be carried in Rectangular bogies!");
            }
            this.cargo = cargo;
            System.out.println("Cargo successfully assigned: " + cargo + " to " + type + " bogie.");
        } catch (CargoSafetyException e) {
            System.err.println("ALERT: " + e.getMessage());
        } finally {
            System.out.println("Cargo Assignment Process Completed for " + type + " bogie.");
        }
    }

    @Override
    public String toString() {
        return "GoodsBogie: " + type + " [Cargo: " + (cargo != null ? cargo : "Unassigned") + "]";
    }
}

public class TrainApp {
    public static void main(String[] args) {

        // --- UC1: Initialize Train ---
        System.out.println("=== Train Consist Management App ===");
        List<String> simpleBogies = new ArrayList<>();

        // --- UC2: ArrayList Operations ---
        System.out.println("\n--- UC2: Managing Passenger Bogies ---");
        simpleBogies.add("Sleeper");
        simpleBogies.add("AC Chair");
        simpleBogies.remove("AC Chair");
        System.out.println("Current Bogie List: " + simpleBogies);

        // --- UC3: HashSet (Unique IDs) ---
        System.out.println("\n--- UC3: Registering Unique Bogie IDs ---");
        Set<String> bogieIds = new HashSet<>(Arrays.asList("BG101", "BG102", "BG101"));
        System.out.println("Unique IDs: " + bogieIds);

        // --- UC4: LinkedList (Physical Chaining) ---
        System.out.println("\n--- UC4: Physical Train Chaining ---");
        LinkedList<String> trainSequence = new LinkedList<>(Arrays.asList("Engine", "Sleeper", "Cargo"));
        trainSequence.add(1, "Pantry Car");

        // --- UC5: LinkedHashSet (Ordered Uniqueness) ---
        System.out.println("\n--- UC5: Preserve Insertion Order ---");
        Set<String> trainFormation = new LinkedHashSet<>(Arrays.asList("Engine", "Sleeper", "Cargo", "Guard"));

        // --- UC6: HashMap (Mapping Bogie to Capacity) ---
        System.out.println("\n--- UC6: Bogie Capacity Mapping (HashMap) ---");
        Map<String, Integer> bogieCapacityMap = new HashMap<>();
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 56);

        // --- UC7: Sort Bogies by Capacity ---
        System.out.println("\n--- UC7: Sorting Bogies by Capacity (Comparator) ---");
        List<Bogie> passengerBogies = new ArrayList<>();
        try {
            passengerBogies.add(new Bogie("Sleeper", 72));
            passengerBogies.add(new Bogie("AC Chair", 56));
            passengerBogies.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) { System.out.println(e.getMessage()); }
        passengerBogies.sort(Comparator.comparingInt(b -> b.capacity));

        // --- UC8: Filter Bogies ---
        System.out.println("\n--- UC8: Filter Bogies (Capacity > 60) ---");
        passengerBogies.stream().filter(b -> b.capacity > 60).forEach(System.out::println);

        // --- UC9: Grouping ---
        System.out.println("\n--- UC9: Grouping Bogies by Category ---");
        passengerBogies.stream().collect(Collectors.groupingBy(b -> b.name))
                .forEach((type, list) -> System.out.println("Category: [" + type + "] | Count: " + list.size()));

        // --- UC10: Aggregate Seats ---
        System.out.println("\n--- UC10: Aggregate Total Seating Capacity ---");
        int total = passengerBogies.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("Total Seating Capacity: " + total + " seats");

        // --- UC11: Regex Validation ---
        System.out.println("\n--- UC11: Validate Train ID (Regex) ---");
        String tid = "TRN-1234";
        System.out.println("Train ID " + tid + " Valid: " + Pattern.compile("TRN-\\d{4}").matcher(tid).matches());

        // --- UC12: Safety Compliance ---
        System.out.println("\n--- UC12: Enforcing Safety Rules (allMatch) ---");
        List<GoodsBogie> goodsList = Arrays.asList(new GoodsBogie("Cylindrical"), new GoodsBogie("Rectangular"));
        goodsList.get(0).cargo = "Petroleum"; // Hard setting for demo
        boolean isSafe = goodsList.stream().allMatch(b -> !Objects.equals(b.type, "Cylindrical") || "Petroleum".equals(b.cargo));
        System.out.println("Stream Safety Check: " + (isSafe ? "PASSED" : "FAILED"));

        // --- UC13: Performance Comparison ---
        System.out.println("\n--- UC13: Performance Benchmarking ---");
        long sTime = System.nanoTime();
        passengerBogies.stream().filter(b -> b.capacity > 60).count();
        System.out.println("Stream Filter Duration: " + (System.nanoTime() - sTime) + " ns");

        // --- UC14: Custom Exception ---
        System.out.println("\n--- UC14: Testing Custom Exception (Invalid Capacity) ---");
        try { new Bogie("Invalid", -5); } catch (InvalidCapacityException e) { System.out.println("Caught: " + e.getMessage()); }

        // --- UC15: Safe Cargo Assignment Using try-catch-finally ---
        System.out.println("\n--- UC15: Safe Cargo Assignment (Structured Handling) ---");
        GoodsBogie g1 = new GoodsBogie("Rectangular");
        GoodsBogie g2 = new GoodsBogie("Cylindrical");

        System.out.println("Task 1: Assign Petroleum to Rectangular (Unsafe)");
        g1.assignCargo("Petroleum");

        System.out.println("\nTask 2: Assign Petroleum to Cylindrical (Safe)");
        g2.assignCargo("Petroleum");

        System.out.println("\nSystem finalized. All Use Cases complete.");
    }
}
