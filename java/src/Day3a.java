import helpers.Data;

public class Day3a {

    public static void main(String[] args) {

        // Get the memory
        String memory = Data.getFromFile("java/resources/day3.txt");

        // Keep count of the number of instances
        int count = 0;

        // Loop through the memory
        for (int i = 0; i < memory.length() - 4; i++) {
            // Check if we are at the string "mul("
            if (memory.substring(i, i + 4).equals("mul(")) {
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