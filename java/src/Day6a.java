import helpers.Data;

public class Day6a {

    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public Direction rotate() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }

    private static boolean canMove(char[][] mapArray, int x, int y, Direction direction) {
        int rows = mapArray.length;
        int cols = mapArray[0].length;
        return switch (direction) {
            case UP -> y - 1 >= 0 && mapArray[y - 1][x] != '#';
            case DOWN -> y + 1 < rows && mapArray[y + 1][x] != '#';
            case LEFT -> x - 1 >= 0 && mapArray[y][x - 1] != '#';
            case RIGHT -> x + 1 < cols && mapArray[y][x + 1] != '#';
            default -> false;
        };
    }

    private static boolean atWall(char[][] mapArray, int x, int y, Direction direction) {
        int rows = mapArray.length;
        int cols = mapArray[0].length;
        return switch (direction) {
            case UP -> y - 1 < 0;
            case DOWN -> y + 1 >= rows;
            case LEFT -> x - 1 < 0;
            case RIGHT -> x + 1 >= cols;
        };
    }

    public static void main(String[] args) {

        // Get the relevant data
        Data d = new Data("java/resources/day6.txt");
        int rows = d.getRows();
        int cols = d.getCols();
        char[][] mapArray = d.getAs2dArray();
        Direction direction = Direction.UP;

        // Find the only "^" in the map
        int guardX = 0;
        int guardY = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mapArray[i][j] == '^') {
                    guardX = j;
                    guardY = i;
                }
            }
        }

        // Move while we are not at a wall
        while (!atWall(mapArray, guardX, guardY, direction)) {
            if (canMove(mapArray, guardX, guardY, direction)) {
                mapArray[guardY][guardX] = 'X';
                switch (direction) {
                    case UP -> guardY--;
                    case DOWN -> guardY++;
                    case LEFT -> guardX--;
                    case RIGHT -> guardX++;
                }
                // Log x and y
                mapArray[guardY][guardX] = '^';
            } else {
                direction = direction.rotate();
            }
        }

        // Count the number of visited spots
        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mapArray[i][j] == 'X') {
                    count++;
                }
            }
        }

        System.out.println(count);

    }

}
