import helpers.Data;

public class Day7a {

    private enum Operation {
        ADD,
        MULT
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
            for (int i = 0; i < Math.pow(2, scheme.length); i++) {
                for (int j = 0; j < scheme.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        scheme[j] = Operation.MULT;
                    } else {
                        scheme[j] = Operation.ADD;
                    }
                }
                // Check if the equation works
                long sum = values[0];
                for (int k = 1; k < values.length; k++) {
                    if (scheme[k - 1] == Operation.ADD) {
                        sum += values[k];
                    } else {
                        sum *= values[k];
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