import helpers.Data;
import java.util.HashSet;
import java.util.Set;

public class Day6b {

    private static char[][] defaultArea;
    private static CoordsDirection start;

    // Enum for directions
    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    // Record for coordinates
    record Coords(int x, int y) { }
    // Record for coordinates with direction
    private record CoordsDirection(int x, int y, Direction direction) { }

    public static void main(String[] args) {
        // Get the relevant data
        String data = Data.getFromFileRaw("java/resources/day6.txt");
        parseInput(data);
        part2();
    }

    private static void part2() {
        // Clone the default area
        char[][] loopArea = defaultArea.clone();
        // Move and get the result
        Result result = move(loopArea, false);
        Set<Coords> positions = new HashSet<>(result.getPositions());
        int cycleCount = 0;

        // Iterate over positions
        for (Coords c : positions) {
            loopArea[c.y][c.x] = '#';
            result = move(loopArea, true);
            cycleCount += result.getCycleCount();
            loopArea[c.y][c.x] = '.';
        }

        // Print the number of obstruction positions
        System.out.println("Obstruction Positions: " + cycleCount);
    }

    private static Result move(char[][] area, boolean checkLoop) {
        Set<Coords> positions = new HashSet<>();
        Set<CoordsDirection> positionsD = new HashSet<>();
        Result result = new Result(positions, 0);

        int x = start.x();
        int y = start.y();
        Direction direction = start.direction;

        // Move while within bounds
        while (y >= 0 && y < area.length && x >= 0 && x < area[y].length) {
            if (!checkLoop) positions.add(new Coords(x, y));

            // Move based on direction
            switch (direction) {
                case UP:
                    if (y - 1 >= 0 && area[y - 1][x] == '#') {
                        direction = Direction.RIGHT;
                    } else {
                        --y;
                    }
                    break;
                case DOWN:
                    if (y + 1 < area.length && area[y + 1][x] == '#') {
                        direction = Direction.LEFT;
                    } else {
                        ++y;
                    }
                    break;
                case LEFT:
                    if (x - 1 >= 0 && area[y][x - 1] == '#') {
                        direction = Direction.UP;
                    } else {
                        --x;
                    }
                    break;
                case RIGHT:
                    if (x + 1 < area[y].length && area[y][x + 1] == '#') {
                        direction = Direction.DOWN;
                    } else {
                        ++x;
                    }
                    break;
                default:
                    break;
            }

            // Check for loops
            if (checkLoop) {
                CoordsDirection cd = new CoordsDirection(x, y, direction);
                if (positionsD.contains(cd)) {
                    result.incrementCycleCount();
                    break;
                }
                positionsD.add(cd);
            }
        }

        return result;
    }

    private static void parseInput(String data) {
        String[] lines = data.split("\n");
        defaultArea = new char[lines.length][];
        int y = 0;

        // Parse the input data
        for (String line : lines) {
            defaultArea[y] = line.toCharArray();
            if (line.contains("^")) {
                int x = line.indexOf("^");
                start = new CoordsDirection(x, y, Direction.UP);
            }
            ++y;
        }
    }
}

class Result {
    private final Set<Day6b.Coords> positions;
    private int cycleCount;

    public Result(Set<Day6b.Coords> positions, int cycleCount) {
        this.positions = positions;
        this.cycleCount = cycleCount;
    }

    public Set<Day6b.Coords> getPositions() {
        return positions;
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public void incrementCycleCount() {
        cycleCount++;
    }
}