import java.util.*;
import java.util.stream.Collectors;

public class TrainAppTest {
    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        System.out.println("--- Running UC8 Stream Filter Tests ---");

        testFilter_CapacityGreaterThanThreshold();
        testFilter_NoBogiesMatching();
        testFilter_EmptyBogieList();
        testFilter_OriginalListUnchanged();

        testFilter_CapacityEqualToThreshold();
        testFilter_MultipleBogiesMatching();
        testFilter_AllBogiesMatching();
    }

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
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper A", 72),
                new Bogie("Sleeper B", 75),
                new Bogie("AC", 50)
        );
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
}
