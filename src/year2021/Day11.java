package year2021;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day11 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";
    static int total = 0;

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int[][] grid = new int[lines.size()][lines.get(0).length()];
        boolean[][] marked;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }
        for (int i = 0; i < 100; i++) {
            marked = new boolean[lines.size()][lines.get(0).length()];
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[j].length; k++) {
                    shine(grid, marked, j, k);
                }
            }
        }
        System.out.println(total);
    }

    public static void shine(int[][] grid, boolean[][] marked, int y, int x) {
        if (grid[y][x] != 9) {
            if (!marked[y][x]) {
                grid[y][x]++;
            }
        }
        else {
            total++;
            grid[y][x] = 0;
            marked[y][x] = true;
            if (y - 1 >= 0 && x - 1 >= 0) shine(grid, marked, y - 1, x - 1);
            if (y - 1 >= 0) shine(grid, marked, y - 1, x);
            if (y - 1 >= 0 && x + 1 != grid[y].length) shine(grid, marked, y - 1, x + 1);
            if (x - 1 >= 0) shine(grid, marked, y, x - 1);
            if (x + 1 != grid[y].length) shine(grid, marked, y, x + 1);
            if (y + 1 != grid.length && x - 1 >= 0) shine(grid, marked, y + 1, x - 1);
            if (y + 1 != grid.length) shine(grid, marked, y + 1, x);
            if (y + 1 != grid.length && x + 1 != grid[y].length) shine(grid, marked, y + 1, x + 1);
        }
    }
}