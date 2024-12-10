public class Day1b {

    public static void main(String[] args) {

        // Get the raw data
        String[] raw = helpers.Data.getFromFileByLines("java/resources/day1.txt");

        // Initialize the arrays
        int[] left = new int[raw.length];
        int[] right = new int[raw.length];

        // Split the raw data into left and right
        for (int i = 0; i < raw.length; i++) {
            String[] parts = raw[i].split(" {3}");
            left[i] = Integer.parseInt(parts[0]);
            right[i] = Integer.parseInt(parts[1]);
        }

        // Check how many times each number on the left is on the right, and
        // multiply the number by the number of times it appears
        int similarityScore = 0;
        for (int i = 0; i < left.length; i++) {
            int count = 0;
            for (int j = 0; j < right.length; j++) {
                if (left[i] == right[j]) {
                    count++;
                }
            }
            similarityScore += left[i] * count;
        }

        System.out.println(similarityScore);

    }

}
