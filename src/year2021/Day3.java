package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        String gamma = "";
        String epsilon = "";
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.get(0).length(); i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for (String line : lines) {
                if (line.charAt(i) == '0') {
                    zeroCount++;
                }
                else oneCount++;
            }
            if (zeroCount > oneCount) {
                gamma += '0';
                epsilon += '1';
            }
            else {
                gamma += '1';
                epsilon += '0';
            }
        }
        System.out.println(Long.parseLong(gamma, 2) * Long.parseLong(epsilon, 2));
    }
}