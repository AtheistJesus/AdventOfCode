package year2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day9 {
    public static final String FILENAME = "C:/Users/Damian/Numbers.txt";
    static int[][] heights;
    static int total = 0;

    public static void main(String[] args)
    {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(FILENAME));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        heights = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++)
        {
            for (int j = 0; j < lines.get(i).length(); j++)
            {
                heights[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }
        getInsidePoints();
        getOuterColumns();
        getOuterRows();
        getCorners();
        System.out.println(total);
    }

    public static void getInsidePoints()
    {
        for (int i = 1; i < heights.length - 1; i++)
        {
            for (int j = 1; j < heights[i].length - 1; j++)
            {
                if (heights[i-1][j] > heights[i][j] && heights[i+1][j] > heights[i][j]
                        && heights[i][j-1] > heights[i][j] && heights[i][j+1] > heights[i][j])
                {
                    total += heights[i][j] + 1;
                }
            }
        }
    }

    public static void getOuterRows()
    {
        for (int i = 1; i < heights.length - 1; i++)
        {
            if (heights[0][i-1] > heights[0][i] && heights[1][i] > heights[0][i] && heights[0][i+1] > heights[0][i])
            {
                total += heights[0][i] + 1;
            }
            if (heights[heights.length-1][i-1] > heights[heights.length-1][i]
                    && heights[heights.length-2][i] > heights[heights.length-1][i]
                    && heights[heights.length-1][i+1] > heights[heights.length-1][i])
            {
                total += heights[heights.length - 1][i] + 1;
            }
        }
    }

    public static void getOuterColumns()
    {
        for (int i = 1; i < heights.length - 1; i++)
        {
            if (heights[i-1][0] > heights[i][0] && heights[i+1][0] > heights[i][0] && heights[i][1] > heights[i][0])
            {
                total += heights[i][0] + 1;
            }
            if (heights[i-1][heights.length-1] > heights[i][heights.length-1]
                    && heights[i+1][heights.length-1] > heights[i][heights.length-1]
                    && heights[i][heights.length-2] > heights[i][heights.length-1])
            {
                total += heights[i][heights.length-1] + 1;
            }
        }
    }

    public static void getCorners()
    {
        if (heights[0][1] > heights[0][0] && heights[1][0] > heights[0][0])
        {
            total += heights[0][0] + 1;
        }
        if (heights[0][heights.length - 2] > heights[0][heights.length - 1] && heights[1][heights.length - 1] > heights[0][heights.length - 1])
        {
            total += heights[0][heights.length - 1] + 1;
        }
        if (heights[heights.length - 1][1] > heights[heights.length - 1][0] && heights[heights.length - 1][1] > heights[heights.length - 1][0])
        {
            total += heights[heights.length - 1][0] + 1;
        }
        if (heights[heights.length - 1][heights.length - 2] > heights[heights.length - 1][heights.length - 1] && heights[heights.length - 2][heights.length - 1] > heights[heights.length - 1][heights.length - 1])
        {
            total += heights[heights.length - 1][heights.length - 1] + 1;
        }
    }
}