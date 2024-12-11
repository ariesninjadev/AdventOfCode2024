import helpers.Data;

public class Day4a {

    public static void main(String[] args) {
        // Get the crossword's first line to get the number of columns
        int cols = Data.getFromFileByLines("java/resources/day4.txt")[0].length();

        // Get full crossword to get the number of rows
        String crossword = Data.getFromFile("java/resources/day4.txt");
        int rows = crossword.length() / cols;

        // Get the crossword as a 2D array
        char[][] crosswordArray = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            crosswordArray[i] = crossword.substring(i * cols, (i + 1) * cols).toCharArray();
        }

        // We only need to match the word "XMAS" but in all directions
        String word = "XMAS";
        int count = 0;

        // Define directions: {row increment, col increment}
        int[][] directions = {
            {0, 1}, {1, 0}, {1, 1}, {-1, 1}, // horizontal, vertical, diagonal, anti-diagonal
            {0, -1}, {-1, 0}, {-1, -1}, {1, -1} // horizontal reversed, vertical reversed, diagonal reversed, anti-diagonal reversed
        };

        // Loop through the crossword
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int[] direction : directions) {
                    if (canMatch(crosswordArray, word, i, j, direction)) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean canMatch(char[][] crosswordArray, String word, int row, int col, int[] direction) {
        int rows = crosswordArray.length;
        int cols = crosswordArray[0].length;
        int wordLength = word.length();

        for (int k = 0; k < wordLength; k++) {
            int newRow = row + k * direction[0];
            int newCol = col + k * direction[1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || crosswordArray[newRow][newCol] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}