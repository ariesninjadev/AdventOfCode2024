import helpers.Data;

public class Day7b {

    private enum Operation {
        ADD,
        MULT,
        CONCAT
    }

    private static class Equation {

        long result;
        long[] values;

        public Equation(String raw) {
            String[] parts = raw.split(": ");
            result = Long.parseLong(parts[0]);
            String[] vRaw = parts[1].split(" ");
            values = new long[vRaw.length];
            for (int i = 0; i < vRaw.length; i++) {
                values[i] = Long.parseLong(vRaw[i]);
            }
        }

        public boolean check() {
            Operation[] scheme = new Operation[values.length - 1];
            for (int i = 0; i < scheme.length; i++) {
                scheme[i] = Operation.ADD;
            }
            for (int i = 0; i < Math.pow(3, scheme.length); i++) {
                int temp = i; // Use a temporary variable to decode the base-3 value
                for (int j = 0; j < scheme.length; j++) {
                    int remainder = temp % 3; // Get the base-3 digit
                    temp /= 3; // Reduce temp for the next digit
                    if (remainder == 0) {
                        scheme[j] = Operation.ADD;
                    } else if (remainder == 1) {
                        scheme[j] = Operation.MULT;
                    } else {
                        scheme[j] = Operation.CONCAT;
                    }
                }
                // Check if the equation works
                long sum = values[0];
                for (int k = 1; k < values.length; k++) {
                    if (scheme[k - 1] == Operation.ADD) {
                        sum += values[k];
                    } else if (scheme[k - 1] == Operation.MULT) {
                        sum *= values[k];
                    } else {
                        sum = Long.parseLong(String.valueOf(sum) + String.valueOf(values[k]));
                    }
                }
                if (sum == result) {
                    return true;
                }
            }
            return false;
        }

        public long getResult() {
            return result;
        }

    }

    public static void main(String[] args) {

        // Init counter
        long count = 0l;

        // Get the data
        String[] data = Data.getFromFileByLines("java/resources/day7.txt");

        for (String s : data) {
            Equation e = new Equation(s);
            if (e.check()) {
                count += e.getResult();
            }
        }

        System.out.println(count);

    }

}