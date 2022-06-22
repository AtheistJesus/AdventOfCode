package year2021;

import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;

public class Day6P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        Map<Integer, Long> freq = new HashMap<>();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            freq.put(i, 0L);
        }
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arr = lines.get(0).split(",");
        for (String s : arr) {
            freq.put(Integer.parseInt(s), freq.get(Integer.parseInt(s)) + 1);
        }
        for (int i = 0; i < 256; i++) {
            long newFish = freq.get(0);
            for (int j = 1; j <= freq.size() - 1; j++) {
                freq.put(j - 1, freq.get(j));
            }
            freq.put(6, newFish + freq.get(6));
            freq.put(8, newFish);
        }
        long total = 0;
        for (Map.Entry<Integer, Long> pair : freq.entrySet()) {
            total += pair.getValue();
        }
        System.out.println(total);
    }
}