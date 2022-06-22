package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int marked = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String[] first = lines.get(0).split(",");
        int blankLine = 0;
        int maxX = Integer.parseInt(first[0]);
        int maxY = Integer.parseInt(first[1]);
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).length() == 0) {
                blankLine = i;
            }
        }
        for (int i = 1; i < blankLine; i++) {
            String[] current = lines.get(i).split(",");
            if (Integer.parseInt(current[0]) > maxX) {
                maxX = Integer.parseInt(current[0]);
            }
            if (Integer.parseInt(current[1]) > maxY) {
                maxY = Integer.parseInt(current[1]);
            }
        }
        String cleared = lines.get(blankLine + 1).replace("fold along ", "");
        int location = Integer.parseInt(cleared.split("=")[1]);
        char direction = cleared.charAt(cleared.indexOf("=") - 1);
        String[][] grid = new String[maxY + 1][maxX + 1];
        for (String[] strings : grid) {
            Arrays.fill(strings, ".");
        }
        for (int i = 0; i < blankLine; i++) {
            String[] coords = lines.get(i).split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            grid[y][x] = "#";
        }
        grid = fold(grid, location, direction);
        for (String[] line : grid) {
            for (String point : line) {
                if (point.equals("#")) marked++;
            }
        }
        System.out.println(marked);
    }
    public static String[][] fold(String[][] grid, int number, char direction) {
        String[][] newGrid;
        if (direction == 'x') {
            newGrid = new String[grid.length][number];
            for (int i = 0; i < grid.length; i++) {
                System.arraycopy(grid[i], 0, newGrid[i], 0, number);
            }
            for (int i = 0; i < grid.length; i++) {
                int count = 2;
                for (int j = number + 1; j < grid[i].length; j++) {
                    if (grid[i][j - count].equals(".") && grid[i][j].equals("#")) {
                        newGrid[i][j - count] = "#";
                    }
                    count += 2;
                }
            }
        }
        else if (direction == 'y') {
            newGrid = new String[number][grid[0].length];
            for (int i = 0; i < number; i++) {
                System.arraycopy(grid[i], 0, newGrid[i], 0, grid[i].length);
            }
            for (int i = 0; i < grid[0].length; i++) {
                int count = 2;
                for (int j = number + 1; j < grid.length; j++) {
                    if (grid[j][i].equals("#")) {
                        newGrid[j - count][i] = "#";
                    }
                    count += 2;
                }
            }
        }
        else throw new RuntimeException("Direction should either be 'x' or 'y'!");
        return newGrid;
    }
}