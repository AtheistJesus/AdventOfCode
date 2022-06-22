package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";
    public static int[][] heights;
    public static boolean[][] marked;
    public static List<String> lines;

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        heights = new int[lines.size()][lines.get(0).length()];
        marked = new boolean[lines.size()][lines.get(0).length()];

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                heights[y][x] = Character.digit(lines.get(y).charAt(x), 10);
            }
        }
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++)
            {
                marked = new boolean[lines.size()][lines.get(0).length()];
                values.add(fillBasin(i, j));
            }
        }
        Collections.sort(values);
        System.out.println(values.get(values.size() - 1) * values.get(values.size() - 2) * values.get(values.size() - 3));
    }

    public static int fillBasin(int y, int x) {
        int total = 1;
        if (marked[y][x]) return 0;

        marked[y][x] = true;
        if (x + 1 < heights[0].length) {
            if (heights[y][x] < heights[y][x + 1] && heights[y][x + 1] != 9) {
                total += fillBasin(y, x + 1);
            }
        }

        if (x - 1 >= 0) {
            if (heights[y][x] < heights[y][x - 1] && heights[y][x - 1] != 9) {
                total += fillBasin(y, x - 1);
            }
        }

        if (y + 1 < heights.length) {
            if (heights[y][x] < heights[y + 1][x] && heights[y + 1][x] != 9) {
                total += fillBasin(y + 1, x);
            }
        }

        if (y - 1 >= 0) {
            if (heights[y][x] < heights[y - 1][x] && heights[y - 1][x] != 9) {
                total += fillBasin(y - 1, x);
            }
        }
        return total;
    }
}