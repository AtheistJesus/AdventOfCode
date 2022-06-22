package year2021;

import java.util.*;
import java.nio.file.*;

public class Day7 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String[] numbers = lines.get(0).split(",");
        int[] dirs = new int[numbers.length];
        int index = 0;
        for (String str : numbers) {
            int total = 0;
            int num = Integer.parseInt(str);
            for (String s : numbers) {
                total += Math.abs(Integer.parseInt(s) - num);
            }
            dirs[index] = total;
            index++;
        }
        int min = dirs[0];
        for (int i = 1; i < dirs.length; i++)
        {
            if (dirs[i] < min)
            {
                min = dirs[i];
            }
        }
        System.out.println(min);
    }
}