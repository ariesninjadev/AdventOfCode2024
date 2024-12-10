import helpers.Data;
import java.util.Arrays;

public class Day1a {

    public static void main(String[] args) {

        // Get the raw data
        String[] raw = Data.getFromFileByLines("java/resources/day1.txt");

        // Initialize the arrays
        int[] left = new int[raw.length];
        int[] right = new int[raw.length];

        // Split the raw data into left and right
        for (int i = 0; i < raw.length; i++) {
            String[] parts = raw[i].split(" {3}");
            left[i] = Integer.parseInt(parts[0]);
            right[i] = Integer.parseInt(parts[1]);
        }

        // Sort the arrays in ascending order
        Arrays.sort(left);
        Arrays.sort(right);

        // Calculate the total distance
        int totalDistance = 0;
        for (int i = 0; i < left.length; i++) {
            totalDistance += Math.abs(right[i] - left[i]);
        }

        System.out.println(totalDistance);

    }
}