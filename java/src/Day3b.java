import helpers.Data;

public class Day3b {

    public static void main(String[] args) {

        // Get the memory
        String memory = Data.getFromFile("java/resources/day3.txt");

        // Enabled state
        boolean enabled = true;

        // Keep count of the number of instances
        int count = 0;

        // Loop through the memory
        for (int i = 0; i < memory.length() - 4; i++) {

            // Check if we are at the string "do()"
            if (memory.substring(i, i + 4).equals("do()")) {
                enabled = true;
            }
            // Check if we are at the string "don't()"
            if (i < memory.length() - 7 && memory.substring(i, i + 7).equals("don't()")) {
                enabled = false;
            }

            // Check if we are at the string "mul("
            if (memory.substring(i, i + 4).equals("mul(") && enabled) {
                // Check if the next char is a digit
                int j = i + 4;
                if (!Character.isDigit(memory.charAt(j))) {
                    continue;
                }
                // Extract the first number
                int start = j;
                while (Character.isDigit(memory.charAt(j))) {
                    j++;
                }
                int num1 = Integer.parseInt(memory.substring(start, j));
                
                // Check if the next char is a comma
                if (memory.charAt(j) != ',') {
                    continue;
                }
                j++;
                
                // Extract the second number
                start = j;
                while (Character.isDigit(memory.charAt(j))) {
                    j++;
                }
                int num2 = Integer.parseInt(memory.substring(start, j));
                
                // Check if the next char is a closing parenthesis
                if (memory.charAt(j) != ')') {
                    continue;
                }
                
                // Increment the count
                count += num1 * num2;

            }

        }

        System.out.println(count);

    }
}