package year2021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4P2 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";

    public static void main(String[] args) {
        StringBuilder lines = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.length() != 0) lines.append(str).append("\n");
                else lines.append("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        List<Board> boards = new ArrayList<>();
        String[] numbers = lines.toString().split("\n\n");
        for (int i = 1; i < numbers.length; i++) {
            boards.add(new Board(twoDArray(numbers[i], "\n", "[ ]+", 5)));
        }
        String[] winning = numbers[0].split(",");
        List<Board> bingoBoards = new ArrayList<>();
        int winIndex = 0;
        for (String number : winning) {
            int index = 0;
            while (index < boards.size()) {
                boards.get(index).fill(Integer.parseInt(number));
                if (boards.get(index).bingoRow() || boards.get(index).bingoColumn()) {
                    bingoBoards.add(boards.get(index));
                    bingoBoards.get(winIndex).winningNumber = Integer.parseInt(number);
                    boards.remove(index);
                    winIndex++;
                }
                else index++;
            }
        }
        System.out.println(bingoBoards.get(bingoBoards.size() - 1).getScore());
    }

    public static String[][] twoDArray(String target, String regexOne, String regexTwo, int size) {
        String[][] array = new String[size][size];
        String[] first = target.split(regexOne);
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(first[i].trim().split(regexTwo), 0, array[i], 0, array[i].length);
        }
        return array;
    }
}

class Board {
    int[][] board;
    int winningNumber;

    public Board(String[][] inputBoard) {
        board = new int[inputBoard.length][inputBoard.length];
        for (int i = 0; i < inputBoard.length; i++) {
            for (int j = 0; j < inputBoard[i].length; j++) {
                board[i][j] = Integer.parseInt(inputBoard[i][j]);
            }
        }
    }

    public int getScore() {
        int score = 0;
        for (int[] line : board) {
            for (int number : line) {
                if (number != -1) {
                    score += number;
                }
            }
        }
        score *= winningNumber;
        return score;
    }

    public boolean bingoRow() {
        for (int[] line : board) {
            int count = 1;
            for (int i = 0; i < line.length - 1; i++) {
                if (line[i] == -1 && line[i + 1] == -1) {
                    count++;
                }
                else break;
            }
            if (count == 5) return true;
        }
        return false;
    }

    public boolean bingoColumn() {
        for (int i = 0; i < board.length; i++) {
            int count = 1;
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[j][i] == -1  && board[j + 1][i] == -1) {
                    count++;
                }
                else break;
            }
            if (count == 5) return true;
        }
        return false;
    }

    public void fill(int winningNumber) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == winningNumber) {
                    board[i][j] = -1;
                }
            }
        }
    }
}