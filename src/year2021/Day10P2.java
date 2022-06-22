package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Day10P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        Stack<Character> open;
        List<String> lines = new ArrayList<>();
        List<String> marked = new ArrayList<>();
        List<String> closed = new ArrayList<>();
        List<Long> scores = new ArrayList<>();
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
                        marked.add(content);
                        break;
                    }
                }
            }
        }
        lines.removeAll(marked);
        for (String content : lines) {
            open = new Stack<>();
            StringBuilder current = new StringBuilder();
            for (int i = 0; i < content.length(); i++) {
                if (content.charAt(i) == '(' || content.charAt(i) == '[' || content.charAt(i) == '{' ||
                        content.charAt(i) == '<') {
                    open.push(content.charAt(i));
                }
                else {
                    if (matches(open.peek(), content.charAt(i))) {
                        open.pop();
                    }
                }
            }
            for (int i = open.size() - 1; i >= 0; i--) {
                if (open.get(i) == '(') current.append(')');
                else if (open.get(i) == '[') current.append(']');
                else if (open.get(i) == '{') current.append('}');
                else current.append('>');
            }
            closed.add(current.toString());
        }
        for (String str : closed) {
            long score = 0;
            for (int i = 0; i < str.length(); i++) {
                score *= 5;
                score += scoreIncomplete(str.charAt(i));
            }
            scores.add(score);
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size()/2));
    }

    public static int scoreIncomplete(char illegal) {
        if (illegal == ')') return 1;
        else if (illegal == ']') return 2;
        else if (illegal == '}') return 3;
        else return 4;
    }

    public static boolean matches(char opening, char closing) {
        return opening == '(' && closing == ')' || opening == '[' && closing == ']' ||
                opening == '{' && closing == '}' || opening == '<' && closing == '>';
    }
}
