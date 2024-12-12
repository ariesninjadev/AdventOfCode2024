import helpers.Data;

import java.util.Arrays;

public class Day5b {

    public static void main(String[] args) {
   
        // Get all data
        String[] data = Data.getFromFileByLines("java/resources/day5.txt");
        
        // Find where the ruleset ends
        int ruleSize = 0;
        for (String s : data) {
            if (s.equals("")) {
                break;
            }
            ruleSize++;
        }
        
        // System.out.println(ruleSize);

        // Init subdata fields
        int[][] rules = new int[ruleSize][2];
        int updateCount = data.length - ruleSize - 1;
        int[][] updates = new int[updateCount][];
        
        // Populate ruleset
        for (int i = 0; i < ruleSize; i++) {
            rules[i] = Arrays.stream(data[i].split("\\|"))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        
        // Populate updates
        for (int i = ruleSize + 1; i < data.length; i++) {
            updates[i-ruleSize-1] = Arrays.stream(data[i].split("\\,"))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        
        int total = 0;
        
        // Scan each update
        for (int[] update : updates) {
            boolean valid = true;
            // Scan each pair
            for (int i = 0; i < update.length; i++) {
                for (int j = i + 1; j < update.length; j++) {
                    // Scan each rule
                    for (int[] rule : rules) {
                        // Check if the inverse exists
                        if (rule[0] == update[j] && rule[1] == update[i]) {
                            valid = false;
                            // Perform inplace swap
                            int temp = update[i];
                            update[i] = update[j];
                            update[j] = temp;
                        }
                    }
                }
            }
            if (!valid) {
                // Find middle value
                int mid = update[update.length/2];
                total += mid;
            }
        }
        
        System.out.println(total);

    }
}
