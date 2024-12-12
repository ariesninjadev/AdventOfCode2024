import helpers.Data;

public class Day4b {

    public static void main(String[] args) {

        // Get the relevant data
        Data d = new Data("java/resources/day4.txt");
        int rows = d.getRows();
        int cols = d.getCols();
        char[][] crosswordArray = d.getAs2dArray();

        int count = 0;

        // Define directions: {row increment, col increment}
        int[][] directions = {
            {1, 1}, {-1, 1}, {-1, -1}, {1, -1}
        };

        // For each spot
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Look for an M
                if (crosswordArray[i][j] == 'M') {
                    // Look for a following A and S
                    for (int[] direction : directions) {
                        // Make sure we can move this way
                        if (i + (direction[0]*2) < 0 || i + (direction[0]*2) >= rows) {
                            continue;
                        }
                        if (j + (direction[1]*2) < 0 || j + (direction[1]*2) >= cols) {
                            continue;
                        }
                        if (crosswordArray[i + direction[0]][j + direction[1]] == 'A') {
                            int[] center = {i + direction[0], j + direction[1]};
                            if (crosswordArray[i + (direction[0]*2)][j + (direction[1]*2)] == 'S') {
                                // Look for another set of M and S on the next axis
                                if (crosswordArray[center[0] - direction[0]][center[1] + direction[1]] == 'M') {
                                    if (crosswordArray[center[0] + direction[0]][center[1] - direction[1]] == 'S') {
                                        count++;
                                    }
                                } else if (crosswordArray[center[0] - direction[0]][center[1] + direction[1]] == 'S') {
                                    if (crosswordArray[center[0] + direction[0]][center[1] - direction[1]] == 'M') {
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(count/2);

    }
}
