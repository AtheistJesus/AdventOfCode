package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int[][] coordinates = new int[1000][1000];
        int count = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int index = 0;
        while (index < lines.size()) {
            String[] coords = lines.get(index).split(" -> ");
            String[] first = coords[0].split(",");
            String[] second = coords[1].split(",");
            if (!first[0].equals(second[0]) && !first[1].equals(second[1])) {
                lines.remove(index);
            } else index++;
        }
        // ALL RIGHT
        for (String line : lines) {
            String[] coords = line.split(" -> ");
            String[] first = coords[0].split(",");
            String[] second = coords[1].split(",");
            if (first[0].equals(second[0])) {
                if (Integer.parseInt(first[1]) < Integer.parseInt(second[1])) {
                    for (int i = Integer.parseInt(first[1]); i <= Integer.parseInt(second[1]); i++) {
                        coordinates[Integer.parseInt(first[0])][i]++;
                    }
                }
                else {
                    for (int i = Integer.parseInt(second[1]); i <= Integer.parseInt(first[1]); i++) {
                        coordinates[Integer.parseInt(first[0])][i]++;
                    }
                }
            }
            else {
                if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
                    for (int i = Integer.parseInt(first[0]); i <= Integer.parseInt(second[0]); i++) {
                        coordinates[i][Integer.parseInt(first[1])]++;
                    }
                }
                else {
                    for (int i = Integer.parseInt(second[0]); i <= Integer.parseInt(first[0]); i++) {
                        coordinates[i][Integer.parseInt(first[1])]++;
                    }
                }
            }
        }
        for (int[] line : coordinates) {
            for (int number : line) {
                if (number >= 2) count++;
            }
        }
        System.out.println(count);
    }
}