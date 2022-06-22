package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int score = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            score += fixLetters(line);
        }
        System.out.println(score);
    }

    public static int fixLetters(String line) {
        char c = '?', f = '?';
        char n1 = '?', n2 = '?', n3 = '?', n4 = '?', n5 = '?', n6 = '?';
        int outputValue = 0;
        int placeValue = 1000;
        String[] parts = line.split(" \\| ");
        String[] inputs = parts[0].split(" ");
        String[] values = parts[1].split(" ");
        for (String input : inputs) {
            if (input.length() == 2) {
                n1 = input.charAt(0);
                n2 = input.charAt(1);
            }
        }
        for (String input : inputs) {
            if (input.length() == 4) {
                n3 = input.charAt(0);
                n4 = input.charAt(1);
                n5 = input.charAt(2);
                n6 = input.charAt(3);
            }
        }
        for (String input : inputs) {
            if (input.length() == 6) {
                if (!input.contains(n1 + "")) {
                    c = n1;
                    f = n2;
                }
                else if (!input.contains(n2 + "")) {
                    c = n2;
                    f = n1;
                }
            }
        }
        for (String value : values) {
            if (value.length() == 2) {
                outputValue += placeValue;
                placeValue /= 10;
            }
            else if (value.length() == 3) {
                outputValue += (7 * placeValue);
                placeValue /= 10;
            }
            else if (value.length() == 4) {
                outputValue += (4 * placeValue);
                placeValue /= 10;
            }
            else {
                boolean bool = value.contains(f + "") && !value.contains(c + "");
                if (value.length() == 5) {
                    if (value.contains(c + "") && value.contains(f + "")) {
                        outputValue += (3 * placeValue);
                        placeValue /= 10;
                    }
                    else if (bool) {
                        outputValue += (5 * placeValue);
                        placeValue /= 10;
                    }
                    else {
                        outputValue += (2 * placeValue);
                        placeValue /= 10;
                    }
                }
                else if (value.length() == 6) {
                     if (bool) {
                        outputValue += (6 * placeValue);
                        placeValue /= 10;
                     }
                     else if (value.contains(n3 + "") && value.contains(n4 + "")
                             && value.contains(n5 + "") && value.contains(n6 + "")) {
                         outputValue += (9 * placeValue);
                         placeValue /= 10;
                     }
                     else placeValue /= 10;
                }
                else {
                    outputValue += (8 * placeValue);
                    placeValue /= 10;
                }
            }
        }
        return outputValue;
    }
}