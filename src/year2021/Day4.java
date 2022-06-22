package year2021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";
    static int latest = 0;

    public static void main(String[] args) {
        StringBuilder lines = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.length() != 0) lines.append(str);
                lines.append("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        List<String[][]> boards = new ArrayList<>();
        String[] numbers = lines.toString().split("\n\n");
        String[] winning = numbers[0].split(",");
        for (int i = 1; i < numbers.length; i++) {
            boards.add(twoDArray(numbers[i], "\n", "[ ]+", 5));
        }
        System.out.println(getScore(bingo(boards, winning)));
    }

    public static String[][] bingo(List<String[][]> boards, String[] numbers) {
        for (String number : numbers) {
            for (String[][] board : boards) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j].equals(number)) {
                            board[i][j] = "-1";
                            latest = Integer.parseInt(number);
                        }
                    }
                }
                for (String[] strings : board) {
                    int count = 1;
                    for (int i = 0; i < strings.length - 1; i++) {
                        if (strings[i].equals("-1") && strings[i + 1].equals("-1")) count++;
                        else break;
                    }
                    if (count == 5) return board;
                }
                for (int i = 0; i < board.length; i++) {
                    int count = 1;
                    for (int j = 0; j < board[i].length - 1; j++) {
                        if (board[j][i].equals("-1") && board[j + 1][i].equals("-1")) count++;
                        else break;
                    }
                    if (count == 5) return board;
                }
            }
        }
        throw new RuntimeException("No bingo boards found!");
    }

    public static String[][] twoDArray(String target, String regexOne, String regexTwo, int size) {
        String[][] array = new String[size][size];
        String[] first = target.split(regexOne);
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(first[i].trim().split(regexTwo), 0, array[i], 0, array[i].length);
        }
        return array;
    }

    public static int getScore(String[][] board) {
        int score = 0;
        for (String[] line : board) {
            for (String number : line) {
                if (!number.equals("-1")) {
                    score += Integer.parseInt(number);
                }
            }
        }
        score *= latest;
        return score;
    }
}