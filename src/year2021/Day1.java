package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int total = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 1; i < lines.size(); i++) {
            if (Integer.parseInt(lines.get(i)) > Integer.parseInt(lines.get(i-1))) {
                total++;
            }
        }
        System.out.println(total);
    }
}