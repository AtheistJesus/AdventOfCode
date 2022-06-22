package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        Map<Character, Integer> letterCounts = new HashMap<>();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String polymer = insert(lines);
        for (int i = 0; i < polymer.length(); i++) {
            if (!letterCounts.containsKey(polymer.charAt(i))) {
                letterCounts.put(polymer.charAt(i), 1);
            }
            else letterCounts.put(polymer.charAt(i), letterCounts.get(polymer.charAt(i)) + 1);
        }
        int maxCount = letterCounts.get(polymer.charAt(0));
        int minCount = letterCounts.get(polymer.charAt(0));
        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
            else if (entry.getValue() < minCount) {
                minCount = entry.getValue();
            }
        }
        System.out.println(maxCount - minCount);
    }

    private static String insert(List<String> lines) {
        String polymer = lines.get(0);
        for (int index = 0; index < 10; index++) {
            for (int i = 0; i < polymer.length() - 1; i += 2) {
                for (int j = 2; j < lines.size(); j++) {
                    String[] info = lines.get(j).split(" -> ");
                    if (info[0].equals(polymer.charAt(i) + "" + polymer.charAt(i + 1))) {
                        polymer = polymer.replaceFirst(info[0], info[0].charAt(0) + "" + info[1] + "" + info[0].charAt(1));
                        break;
                    }
                }
            }
        }
        return polymer;
    }
}