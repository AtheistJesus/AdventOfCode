package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        List<String> generator = new ArrayList<>();
        List<String> scrubber = new ArrayList<>();
        try {
            generator = Files.readAllLines(Path.of(FILENAME));
            scrubber = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i < generator.get(0).length()) {
            List<String> zeroes = new ArrayList<>();
            List<String> ones = new ArrayList<>();
            int zeroCount = 0;
            int oneCount = 0;
            for (String s : generator) {
                if (s.charAt(i) == '0') {
                    zeroCount++;
                    zeroes.add(s);
                }
                else {
                    oneCount++;
                    ones.add(s);
                }
            }
            if (zeroCount > oneCount) {
                generator.removeAll(ones);
            }
            else {
                generator.removeAll(zeroes);
            }
            i++;
        }
        i = 0;
        while (i < scrubber.get(0).length() && scrubber.size() > 1) { // HERE
            List<String> zeroes = new ArrayList<>();
            List<String> ones = new ArrayList<>();
            int zeroCount = 0;
            int oneCount = 0;
            for (String s : scrubber) {
                if (s.charAt(i) == '0') {
                    zeroCount++;
                    zeroes.add(s);
                }
                else {
                    oneCount++;
                    ones.add(s);
                }
            }
            if (zeroCount <= oneCount) {
                scrubber.removeAll(ones);
            }
            else {
                scrubber.removeAll(zeroes);
            }
            i++;
        }
        System.out.println(Integer.parseInt(generator.get(0), 2) * Integer.parseInt(scrubber.get(0), 2));
    }
}