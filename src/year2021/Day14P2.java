package year2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";
    static Map<String, Long> pairCounts = new HashMap<>();
    final static Map<Character, Long> letterCounts = new HashMap<>();
    final static Map<String, Character> rules = new HashMap<>();
    static List<String> lines;

    public static void main(String[] args) throws IOException {
        lines = Files.readAllLines(Path.of(FILENAME));
        String polymer = lines.get(0);
        initLetters(polymer);
        initPairs(polymer);
        storeRules(lines);

        for (int time = 0; time < 40; time++) {
            pairCounts = getLetterCounts();
        }
        System.out.println(maxMinusMin(letterCounts));
    }

    public static void initLetters(String template) {
        for (int i = 0; i < template.length(); i++) {
            if (!letterCounts.containsKey(template.charAt(i))) {
                letterCounts.put(template.charAt(i), 1L);
            }
            else letterCounts.put(template.charAt(i), letterCounts.get(template.charAt(i)) + 1);
        }
    }

    public static void initPairs(String template) {
        for (int i = 0; i < template.length() - 1; i++) {
            String pair = template.charAt(i) + "" + template.charAt(i + 1);
            pairCounts.put(pair, pairCounts.getOrDefault(pair, 0L) + 1);
        }
    }

    public static void storeRules(List<String> lines) {
        for (int i = 2; i < lines.size(); i++) {
            String[] data = lines.get(i).split(" -> ");
            rules.put(data[0], data[1].charAt(0));
        }
    }

    public static Map<String, Long> getLetterCounts() {
        Map<String, Long> newPairCounts = new HashMap<>();
        for (Map.Entry<String, Character> rule : rules.entrySet()) {
            Long increaseBy = pairCounts.get(rule.getKey());
            if (pairCounts.containsKey(rule.getKey()) && increaseBy != 0) {
                char firstLetter = rule.getKey().charAt(0);
                char secondLetter = rule.getKey().charAt(1);
                char insertion = rule.getValue();
                String firstEntry = firstLetter + "" + insertion;
                String secondEntry = insertion + "" + secondLetter;
                increaseEntry(newPairCounts, firstEntry, increaseBy);
                increaseEntry(newPairCounts, secondEntry, increaseBy);
                if (!letterCounts.containsKey(insertion)) {
                    letterCounts.put(insertion, increaseBy);
                }
                else {
                    letterCounts.put(insertion, letterCounts.get(insertion) + increaseBy);
                }
            }
        }
        return newPairCounts;
    }

    public static <T> void increaseEntry(Map<T, Long> map, T key, long increaseBy) {
        if (!map.containsKey(key)) {
            map.put(key, increaseBy);
        }
        else map.put(key, map.get(key) + increaseBy);
    }

    public static long maxMinusMin(Map<Character, Long> letters) {
        Long firstValue = (Long) letters.values().toArray()[0];
        long maxCount = firstValue, minCount = firstValue;
        for (Map.Entry<Character, Long> letter : letters.entrySet()) {
            if (letter.getValue() > maxCount) {
                maxCount = letter.getValue();
            }
            else if (letter.getValue() < minCount) {
                minCount = letter.getValue();
            }
        }
        return maxCount - minCount;
    }
}