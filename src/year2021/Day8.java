package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int count = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] array = line.split(" \\| ");
            String[] outputValues = array[1].split(" ");
            for (String value : outputValues) {
                if (value.length() == 2 || value.length() == 3 || value.length() == 4 || value.length() == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}