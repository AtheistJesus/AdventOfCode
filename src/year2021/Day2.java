package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int position = 0;
        int depth = 0;
        List<String> lines = new ArrayList<>();
        List<String> directions = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            directions.add(line.split(" ")[0]);
            distances.add(Integer.parseInt(line.split(" ")[1]));
        }
        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).equals("down")) depth += distances.get(i);
            else if (directions.get(i).equals("up")) depth -= distances.get(i);
            else position += distances.get(i);
        }
        System.out.println(position * depth);
    }
}