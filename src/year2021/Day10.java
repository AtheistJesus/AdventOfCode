package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day10 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        int score = 0;
        Stack<Character> open;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String content : lines) {
            open = new Stack<>();
            for (int i = 0; i < content.length(); i++) {
                    if (content.charAt(i) == '(' || content.charAt(i) == '['
                            || content.charAt(i) == '{' || content.charAt(i) == '<') {
                        open.add(content.charAt(i));
                    }
                    else {
                        if (matches(open.peek(), content.charAt(i))) {
                            open.pop();
                        }
                        else {
                            score += scoreCorrupted(content.charAt(i));
                            break;
                        }
                    }
            }
        }

        System.out.println(score);
    }

    public static int scoreCorrupted(char illegal) {
        if (illegal == ')') return 3;
        else if (illegal == ']') return 57;
        else if (illegal == '}') return 1197;
        else return 25137;
    }

    public static boolean matches(char opening, char closing) {
        return opening == '(' && closing == ')' || opening == '[' && closing == ']' ||
                opening == '{' && closing == '}' || opening == '<' && closing == '>';
    }
}