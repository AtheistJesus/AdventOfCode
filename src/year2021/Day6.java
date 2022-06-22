package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String[] temp = lines.get(0).split(",");
        List<Integer> timers = new ArrayList<>();
        for (String str : temp) {
            timers.add(Integer.parseInt(str));
        }
        for (int i = 0; i < 80; i++) {
            int count = 0;
            for (int j = 0; j < timers.size() - count; j++) {
                if (timers.get(j) > 0) {
                    timers.set(j, timers.get(j) - 1);
                }
                else {
                    timers.set(j, 6);
                    timers.add(8);
                    count++;
                }
            }
        }
        System.out.println(timers.size());
    }
}
