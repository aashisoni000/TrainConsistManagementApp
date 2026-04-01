import java.util.*;
import java.util.stream.Collectors;

public class TrainAppTest {
    public static void main(String[] args) {
        runUC8Tests();
        runUC9Tests();
        runUC10Tests();
    }

    public static void runUC8Tests() {
        System.out.println("--- Running UC8 Stream Filter Tests ---");
        testFilter_CapacityGreaterThanThreshold();
        testFilter_NoBogiesMatching();
        testFilter_EmptyBogieList();
        testFilter_OriginalListUnchanged();
        testFilter_CapacityEqualToThreshold();
        testFilter_MultipleBogiesMatching();
        testFilter_AllBogiesMatching();
    }

    public static void runUC9Tests() {
        System.out.println("\n--- Running UC9 Stream Grouping Tests ---");
        testGrouping_BogiesGroupedByType();
        testGrouping_MultipleBogiesInSameGroup();
        testGrouping_DifferentBogieTypes();
        testGrouping_EmptyBogieList();
        testGrouping_SingleBogieCategory();
        testGrouping_MapContainsCorrectKeys();
        testGrouping_GroupSizeValidation();
        testGrouping_OriginalListUnchanged();
    }

    public static void runUC10Tests() {
        System.out.println("\n--- Running UC10 Stream Reduce Tests ---");
        testReduce_TotalSeatCalculation();
        testReduce_MultipleBogiesAggregation();
        testReduce_SingleBogieCapacity();
        testReduce_EmptyBogieList();
        testReduce_CorrectCapacityExtraction();
        testReduce_AllBogiesIncluded();
        testReduce_OriginalListUnchanged();
    }

    // --- UC8 Test Methods ---
    static void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC", 50));
        List<Bogie> result = list.stream().filter(b -> b.capacity > 70).collect(Collectors.toList());
        boolean passed = result.size() == 1 && result.get(0).name.equals("Sleeper");
        System.out.println("testFilter_CapacityGreaterThanThreshold: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testFilter_CapacityEqualToThreshold() {
        List<Bogie> list = Arrays.asList(new Bogie("TestBogie", 70));
        List<Bogie> result = list.stream().filter(b -> b.capacity > 70).collect(Collectors.toList());
        System.out.println("testFilter_CapacityEqualToThreshold: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    static void testFilter_MultipleBogiesMatching() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper A", 72), new Bogie("Sleeper B", 75), new Bogie("AC", 50));
        List<Bogie> result = list.stream().filter(b -> b.capacity > 70).collect(Collectors.toList());
        System.out.println("testFilter_MultipleBogiesMatching: " + (result.size() == 2 ? "PASSED" : "FAILED"));
    }

    static void testFilter_AllBogiesMatching() {
        List<Bogie> list = Arrays.asList(new Bogie("A", 80), new Bogie("B", 90));
        List<Bogie> result = list.stream().filter(b -> b.capacity > 70).collect(Collectors.toList());
        System.out.println("testFilter_AllBogiesMatching: " + (result.size() == 2 ? "PASSED" : "FAILED"));
    }

    static void testFilter_NoBogiesMatching() {
        List<Bogie> list = Arrays.asList(new Bogie("General", 40));
        List<Bogie> result = list.stream().filter(b -> b.capacity > 100).collect(Collectors.toList());
        System.out.println("testFilter_NoBogiesMatching: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    static void testFilter_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        List<Bogie> result = emptyList.stream().filter(b -> b.capacity > 50).collect(Collectors.toList());
        System.out.println("testFilter_EmptyBogieList: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    static void testFilter_OriginalListUnchanged() {
        List<Bogie> original = new ArrayList<>(Arrays.asList(new Bogie("Sleeper", 72)));
        original.stream().filter(b -> b.capacity > 80).collect(Collectors.toList());
        System.out.println("testFilter_OriginalListUnchanged: " + (original.size() == 1 ? "PASSED" : "FAILED"));
    }

    // --- UC9 Test Methods ---
    static void testGrouping_BogiesGroupedByType() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC Chair", 56));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        boolean passed = result.containsKey("Sleeper") && result.containsKey("AC Chair");
        System.out.println("testGrouping_BogiesGroupedByType: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testGrouping_MultipleBogiesInSameGroup() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("Sleeper", 72));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        boolean passed = result.get("Sleeper").size() == 2;
        System.out.println("testGrouping_MultipleBogiesInSameGroup: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testGrouping_DifferentBogieTypes() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("Cargo", 1000));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        boolean passed = result.size() == 2;
        System.out.println("testGrouping_DifferentBogieTypes: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testGrouping_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        Map<String, List<Bogie>> result = emptyList.stream().collect(Collectors.groupingBy(b -> b.name));
        System.out.println("testGrouping_EmptyBogieList: " + (result.isEmpty() ? "PASSED" : "FAILED"));
    }

    static void testGrouping_SingleBogieCategory() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("Sleeper", 72));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        System.out.println("testGrouping_SingleBogieCategory: " + (result.size() == 1 ? "PASSED" : "FAILED"));
    }

    static void testGrouping_MapContainsCorrectKeys() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("First Class", 24));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        boolean passed = result.containsKey("Sleeper") && result.containsKey("First Class");
        System.out.println("testGrouping_MapContainsCorrectKeys: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testGrouping_GroupSizeValidation() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("Sleeper", 72), new Bogie("AC", 56));
        Map<String, List<Bogie>> result = list.stream().collect(Collectors.groupingBy(b -> b.name));
        boolean passed = result.get("Sleeper").size() == 2 && result.get("AC").size() == 1;
        System.out.println("testGrouping_GroupSizeValidation: " + (passed ? "PASSED" : "FAILED"));
    }

    static void testGrouping_OriginalListUnchanged() {
        List<Bogie> original = new ArrayList<>(Arrays.asList(new Bogie("Sleeper", 72)));
        original.stream().collect(Collectors.groupingBy(b -> b.name));
        System.out.println("testGrouping_OriginalListUnchanged: " + (original.size() == 1 ? "PASSED" : "FAILED"));
    }

    // --- UC10 Test Methods ---
    static void testReduce_TotalSeatCalculation() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC", 50));
        int total = list.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_TotalSeatCalculation: " + (total == 122 ? "PASSED" : "FAILED"));
    }

    static void testReduce_MultipleBogiesAggregation() {
        List<Bogie> list = Arrays.asList(new Bogie("A", 10), new Bogie("B", 20), new Bogie("C", 30));
        int total = list.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_MultipleBogiesAggregation: " + (total == 60 ? "PASSED" : "FAILED"));
    }

    static void testReduce_SingleBogieCapacity() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72));
        int total = list.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_SingleBogieCapacity: " + (total == 72 ? "PASSED" : "FAILED"));
    }

    static void testReduce_EmptyBogieList() {
        List<Bogie> emptyList = new ArrayList<>();
        int total = emptyList.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_EmptyBogieList: " + (total == 0 ? "PASSED" : "FAILED"));
    }

    static void testReduce_CorrectCapacityExtraction() {
        Bogie b = new Bogie("Test", 100);
        List<Bogie> list = Arrays.asList(b);
        int total = list.stream().map(obj -> obj.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_CorrectCapacityExtraction: " + (total == 100 ? "PASSED" : "FAILED"));
    }

    static void testReduce_AllBogiesIncluded() {
        List<Bogie> list = Arrays.asList(new Bogie("A", 1), new Bogie("B", 1), new Bogie("C", 1));
        int total = list.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_AllBogiesIncluded: " + (total == 3 ? "PASSED" : "FAILED"));
    }

    static void testReduce_OriginalListUnchanged() {
        List<Bogie> original = new ArrayList<>(Arrays.asList(new Bogie("Sleeper", 72)));
        original.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        System.out.println("testReduce_OriginalListUnchanged: " + (original.size() == 1 ? "PASSED" : "FAILED"));
    }
}
