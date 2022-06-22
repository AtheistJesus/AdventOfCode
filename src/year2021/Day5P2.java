package year2021;

import java.io.File;
import java.util.*;

public class Day5P2 {
    private static final File FILENAME = new File("C:/Users/Damian/Numbers.txt");
    final static Map<String, Integer> scores = new HashMap<>();

    public static void main(String[] args) {
        int count = 0;
        try (Scanner scan = new Scanner(FILENAME)) {
            while (scan.hasNextLine()) {
                String[] split = scan.nextLine().split(" -> ");
                String[] begin = split[0].split(",");
                String[] end = split[1].split(",");
                int beginX = Integer.parseInt(begin[0]);
                int beginY = Integer.parseInt(begin[1]);
                int endX = Integer.parseInt(end[0]);
                int endY = Integer.parseInt(end[1]);
                incrementLine(beginX, beginY, endX, endY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Integer> set : scores.entrySet()) {
            if (set.getValue() >= 2) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void incrementPoint(int x, int y) {
        if (!scores.containsKey(x + "," + y)) {
            scores.put(x + "," + y, 1);
        } else scores.put(x + "," + y, scores.get(x + "," + y) + 1);
    }

    public static void incrementLine(int beginX, int beginY, int endX, int endY) {
        if (beginX == endX) {
            if (beginY < endY) {
                for (int i = beginY; i <= endY; i++) {
                    incrementPoint(endX, i);
                }
            } else {
                for (int i = endY; i <= beginY; i++) {
                    incrementPoint(endX, i);
                }
            }
        } else if (beginY == endY) {
            if (beginX < endX) {
                for (int i = beginX; i <= endX; i++) {
                    incrementPoint(i, endY);
                }
            } else {
                for (int i = endX; i <= beginX; i++) {
                    incrementPoint(i, endY);
                }
            }
        } else {
            if (Math.abs(endX - beginX) == Math.abs(endY - beginY)) {
                if (beginX < endX) {
                    if (beginY < endY) {
                        for (int i = beginX, j = beginY; i <= endX; i++, j++) {
                            incrementPoint(i, j);
                        }
                    } else {
                        for (int i = beginX, j = beginY; i <= endX; i++, j--) {
                            incrementPoint(i, j);
                        }
                    }
                } else {
                    if (beginY < endY) {
                        for (int i = endX, j = endY; i <= beginX; i++, j--) {
                            incrementPoint(i, j);
                        }
                    } else {
                        for (int i = endX, j = endY; i <= beginX; i++, j++) {
                            incrementPoint(i, j);
                        }
                    }
                }
            }
        }
    }
}