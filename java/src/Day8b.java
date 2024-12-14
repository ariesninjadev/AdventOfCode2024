import helpers.Data;

import java.util.ArrayList;

public class Day8b {

    private static class Point {

        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }

    }

    private static class AntennaCluster {

        public static long choose(int n, int m) {
            if (m > n) return 0; // Invalid case
            if (m == 0 || m == n) return 1; // Base cases

            // Since C(n, m) == C(n, n-m), optimize by choosing the smaller m
            m = Math.min(m, n - m);

            long result = 1;
            for (int i = 0; i < m; i++) {
                result *= (n - i); // Multiply numerator
                result /= (i + 1); // Divide denominator
            }

            return result;
        }

        char symbol;
        ArrayList<Point> antennas;

        public AntennaCluster(char symbol) {
            this.symbol = symbol;
            antennas = new ArrayList<Point>();
        }

        public char getSymbol() {
            return symbol;
        }

        public void add(Point a) {
            antennas.add(a);
        }

        private Point endpoint(Point a, Point b) {
            return new Point(a.x + (2 * (b.x - a.x)), a.y + (2 * (b.y - a.y)));
        }

        private Point slope(Point a, Point b) {
            return new Point(b.x-a.x, b.y-a.y);
        }

        public Point[] antinodes(int maxX, int maxY) {
            ArrayList<Point> ans = new ArrayList<>();
            int left = 0;
            int right = 0;
            for (int i = 0; i < choose(antennas.size(), 2); i++) {
                right++;
                if (right == antennas.size()) {
                    right = ++left + 1;
                }
                Point slope = slope(antennas.get(left), antennas.get(right));
                // From left through right to edge of board
                Point pointer = new Point(antennas.get(left).x, antennas.get(left).y);
                while (pointer.x < maxX && pointer.y < maxY) {
                    ans.add(pointer);
                    pointer.x += slope.x;
                    pointer.y += slope.y;
                }
                // From left moving in reverse to other edge
                pointer = new Point(antennas.get(left).x, antennas.get(left).y);
                while (pointer.x > 0 && pointer.y > 0) {
                    ans.add(new Point(pointer));
                    pointer.x -= slope.x;
                    pointer.y -= slope.y;
                }
            }
            for (Point p : ans) {
                System.out.println(p.x + ", " + p.y);
            }
            return ans.toArray(new Point[0]);
        }

    }

    public static AntennaCluster getCluster(ArrayList<AntennaCluster> a, char c) {
        for (AntennaCluster x : a) {
            if (x.getSymbol() == c) {
                return x;
            }
        }
        return null;
    }

    public static boolean has(ArrayList<Point> a, Point p) {
        for (Point pt : a) {
            if (pt.x == p.x && pt.y == p.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean inBounds(Point p, int x, int y) {
        return (p.x >= 0 && p.y >= 0 && p.x < x && p.y < y);
    }

    public static void main(String[] args) {

        // Init storage
        ArrayList<AntennaCluster> ants = new ArrayList<>();
        ArrayList<Point> antinodes = new ArrayList<>();

        int x = -1;
        int y = -1;

        // Get the data
        //String[] data = Data.getFromFileByLines("java/resources/day8.txt");

        String[] data = {
                "....A....",
                ".........",
                ".........",
                ".........",
                "....A....",
                "........."
        };

        x = data.length;

        // Organize all antennas
        for (int i = 0; i < x; i++) {
            char[] byChar = data[i].toCharArray();
            y = byChar.length;
            for (int j = 0; j < y; j++) {
                if (byChar[j] != '.') {
                    AntennaCluster ref = getCluster(ants, byChar[j]);
                    if (ref == null) {
                        AntennaCluster newC = new AntennaCluster(byChar[j]);
                        newC.add(new Point(i, j));
                        ants.add(newC);
                    } else {
                        ref.add(new Point(i, j));
                    }
                }
            }
        }

        // Get antinodes
        for (AntennaCluster ac : ants) {
            Point[] antis = ac.antinodes(x, y);
            for (Point p : antis) {
                // System.out.println(p);
                if ((!has(antinodes, p)) && inBounds(p, x, y)) {
                    antinodes.add(p);
                }
            }
        }

        System.out.println(antinodes.size());

    }

}