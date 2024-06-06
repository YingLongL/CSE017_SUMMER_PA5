import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A test class that demonstrates the functionality of the SortedList with GeoLocation objects.
 * It includes methods to populate the list from a file, find elements, and sort the list using various criteria.
 */
public class Test {
    /**
     * The main method that runs several test cases to demonstrate adding, finding, and sorting operations in the SortedList.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SortedList<GeoLocation> cities = new SortedList<>();
        readCities(cities, "cities.txt");
        System.out.println("\nTest case 1: Sorted List created");
        System.out.println(cities.size() + " cities read from the file");
        System.out.println(cities.toString());

        System.out.println("\nTest case 2: Find an element (successful)");
        GeoLocation gl = cities.find(new GeoLocation("San Francisco", "", 0.0, 0.0));
        if (gl == null) {
            System.out.println("San Francisco not found");
        } else {
            System.out.println("San Francisco found:\n" + gl);
        }

        System.out.println("\nTest case 3: Find an element (failed)");
        gl = cities.find(new GeoLocation("Tokyo", "", 0.0, 0.0));
        if (gl == null) {
            System.out.println("Tokyo not found");
        } else {
            System.out.println("Tokyo found:\n" + gl);
        }

        System.out.println("\nTest case 4: Sort by state");
        class ComparatorByState implements Comparator<GeoLocation> {
            @Override
            public int compare(GeoLocation loc1, GeoLocation loc2) {
                return loc1.getState().compareTo(loc2.getState());
            }
        }
        cities.setComparator(new ComparatorByState());
        System.out.println(cities.toString());

        System.out.println("\nTest case 5: Sort by latitude");
        class ComparatorByLatitude implements Comparator<GeoLocation> {
            @Override
            public int compare(GeoLocation loc1, GeoLocation loc2) {
                return Double.compare(loc1.getLatitude(), loc2.getLatitude());
            }
        }
        cities.setComparator(new ComparatorByLatitude());
        System.out.println(cities.toString());

        System.out.println("\nTest case 6: Sort by longitude");
        class ComparatorByLongitude implements Comparator<GeoLocation> {
            @Override
            public int compare(GeoLocation loc1, GeoLocation loc2) {
                return Double.compare(loc1.getLongitude(), loc2.getLongitude());
            }
        }
        cities.setComparator(new ComparatorByLongitude());
        System.out.println(cities.toString());
    }

    /**
     * Reads GeoLocation data from a file and adds them to the specified SortedList.
     * Each line in the file should contain city, state, latitude, and longitude separated by commas.
     * 
     * @param list The SortedList to which the read GeoLocation objects are added.
     * @param filename The name of the file from which to read the GeoLocation data.
     */
    public static void readCities(SortedList<GeoLocation> list, String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String city = parts[0].trim();
                    String state = parts[1].trim();
                    double latitude = Double.parseDouble(parts[2].trim());
                    double longitude = Double.parseDouble(parts[3].trim());
                    GeoLocation location = new GeoLocation(city, state, latitude, longitude);
                    list.add(location);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}