public class Day2a {
    
    public static void main(String[] args) {
        
        // Get the raw data
        String[] raw = helpers.Data.getFromFileByLines("java/resources/day2.txt");
        
        // Keep count of safe reports
        int safe = 0;

        // Loop through each line
        for (String line : raw) {

            // Split the line into parts
            String[] partsRaw = line.split(" ");
            int[] parts = new int[partsRaw.length];
            for (int i = 0; i < partsRaw.length; i++) {
                parts[i] = Integer.parseInt(partsRaw[i]);
            }

            // Safe report
            boolean safeReport = true;
            
            // Check direction
            boolean ascending = true;
            if (parts[0] > parts[1]) {
                ascending = false;
            }

            // Get the first number
            int last = 0;

            for (int i = 1; i < parts.length; i++) {
                // Check if the next number is still ascending or descending
                if (parts[last] > parts[i] && ascending) {
                    safeReport = false;
                    break;
                } else if (parts[last] < parts[i] && !ascending) {
                    safeReport = false;
                    break;
                }
                // Check if the differences is between 1 and 3 inclusive
                if (Math.abs(parts[i] - parts[last]) >= 1 && Math.abs(parts[i] - parts[last]) <= 3) {
                    last = i;
                } else {
                    safeReport = false;
                    break;
                }
            }

            // If the report is safe, increment the count
            if (safeReport) {
                safe++;
            }

        }

        System.out.println(safe);

    }

}
