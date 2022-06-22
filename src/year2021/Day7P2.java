package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        List<String> lines = read(FILENAME);
        List<Long> dirs;
        String[] strings = lines.get(0).split(",");
        int[] numbers = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }
        int min = numbers[0];
        int max = numbers[0];
        for (int i : numbers)
        {
            if (i > max)
            {
                max = i;
            }
            else if (i < min)
            {
                min = i;
            }
        }
        dirs = getFuel(min, max, numbers);
        long newMin = findValue(dirs);
        System.out.println(newMin);
    }

    public static List<String> read(String path)
    {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(path));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static long findValue(List<Long> dirs)
    {
        long newMin = dirs.get(0);
        for (int i = 1; i < dirs.size(); i++)
        {
            if (dirs.get(i) < newMin) {
                newMin = dirs.get(i);
            }
        }
        return newMin;
    }

    public static List<Long> getFuel(int min, int max, int[] numbers)
    {
        List<Long> dirs = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            long total = 0;
            for (int number : numbers) {
                total += summation(Math.abs(number - i));
            }
            dirs.add(total);
        }
        return dirs;
    }

    public static long summation(long num)
    {
        long result = 0;
        for (long i = num; i > 0; i--)
        {
            result += i;
        }
        return result;
    }
}