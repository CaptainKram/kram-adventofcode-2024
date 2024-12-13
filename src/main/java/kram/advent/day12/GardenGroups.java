package kram.advent.day12;

import kram.advent.records.ReversedPosition;
import kram.advent.utils.ReadFromFile;
import kram.advent.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GardenGroups {

    public static final List<ReversedPosition> beenHere = new ArrayList<>();
    private static final String AREA = "area";
    private static final String PERIMETER = "perimeter";

    public static void main(String[] args) {
        String input = ReadFromFile.readFileString("gardenplots");
        char[][] matrix = StringUtil.stringToCharArr(input);

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += calculatePlant(matrix, i, j, new HashMap<>());
            }
        }
        System.out.println(result);
    }

    public static int calculatePlant(char[][] matrix, int i, int j, Map<String, Integer> records) {
        if (beenHere.contains(new ReversedPosition(i, j))) {
            return 0;
        }
        beenHere.add(new ReversedPosition(i, j));
        int perimeter = calculatePerimeter(matrix, i, j);
        records.put(AREA, records.getOrDefault(AREA, 0) + 1);
        records.put(PERIMETER, records.getOrDefault(PERIMETER, 0) + perimeter);

        if (StringUtil.inBoundsUp(matrix, i-1) && matrix[i-1][j] == matrix[i][j]) {
            calculatePlant(matrix, i-1, j, records);
        }
        if (StringUtil.inBoundsDown(matrix, i+1) && matrix[i+1][j] == matrix[i][j]) {
            calculatePlant(matrix, i+1, j, records);
        }
        if (StringUtil.inBoundsLeft(matrix, j-1) && matrix[i][j-1] == matrix[i][j]) {
            calculatePlant(matrix, i, j-1, records);
        }
        if (StringUtil.inBoundsRight(matrix, j+1, i) && matrix[i][j+1] == matrix[i][j]) {
            calculatePlant(matrix, i, j+1, records);
        }

        return records.get(AREA) * records.get(PERIMETER);
    }

    private static int calculatePerimeter(char[][] matrix, int i, int j) {
        int perimeter = 0;

        char here = matrix[i][j];

        boolean inboundsUp = StringUtil.inBoundsUp(matrix, i-1);
        boolean inboundsDown = StringUtil.inBoundsDown(matrix, i+1);
        boolean inboundsLeft = StringUtil.inBoundsLeft(matrix, j-1);
        boolean inboundsRight = StringUtil.inBoundsRight(matrix, j+1, i);

        boolean inboundsUpLeft = StringUtil.inBounds(matrix, i-1, j-1);
        boolean inboundsUpRight = StringUtil.inBounds(matrix, i-1, j+1);
        boolean inboundsDownLeft = StringUtil.inBounds(matrix, i+1, j-1);
        boolean inboundsDownRight = StringUtil.inBounds(matrix, i+1, j+1);

        boolean upNeighbourDidNotCountLeft = true;
        boolean upNeighbourDidNotCountRight = true;
        boolean upNeighbourDidNotCountOut = true;

        boolean downNeighbourDidNotCountLeft = true;
        boolean downNeighbourDidNotCountRight = true;
        boolean downNeighbourDidNotCountOut = true;

        boolean leftNeighbourDidNotCountUp = true;
        boolean leftNeighbourDidNotCountDown = true;
        boolean leftNeighbourDidNotCountOut = true;

        boolean rightNeighbourDidNotCountUp = true;
        boolean rightNeighbourDidNotCountDown = true;
        boolean rightNeighbourDidNotCountOut = true;

        if (inboundsUp) {
            char up = matrix[i-1][j];
            upNeighbourDidNotCountLeft = up != here || !beenHere.contains(new ReversedPosition(i - 1, j)) || (inboundsLeft && matrix[i][j - 1] == here) || (inboundsUpLeft && matrix[i-1][j-1] == here);
            upNeighbourDidNotCountRight = up != here || !beenHere.contains(new ReversedPosition(i - 1, j)) || (inboundsRight && matrix[i][j + 1] == here) || (inboundsUpRight && matrix[i-1][j+1] == here);
            upNeighbourDidNotCountOut = up != here || !beenHere.contains(new ReversedPosition(i - 1, j));
        }
        if (inboundsDown) {
            char down = matrix[i+1][j];
            downNeighbourDidNotCountLeft = down != here || !beenHere.contains(new ReversedPosition(i + 1, j)) || (inboundsLeft && matrix[i][j - 1] == here) || (inboundsDownLeft && matrix[i+1][j-1] == here);
            downNeighbourDidNotCountRight = down != here || !beenHere.contains(new ReversedPosition(i + 1, j)) || (inboundsRight && matrix[i][j + 1] == here) || (inboundsDownRight && matrix[i+1][j+1] == here);
            downNeighbourDidNotCountOut = down != here || !beenHere.contains(new ReversedPosition(i + 1, j));
        }
        if (inboundsLeft) {
            char left = matrix[i][j-1];
            leftNeighbourDidNotCountUp = left != here || !beenHere.contains(new ReversedPosition(i, j - 1)) || (inboundsUp && matrix[i - 1][j] == here) || (inboundsUpLeft && matrix[i-1][j-1] == here);
            leftNeighbourDidNotCountDown = left != here || !beenHere.contains(new ReversedPosition(i, j - 1)) || (inboundsDown && matrix[i + 1][j] == here) || (inboundsDownLeft && matrix[i+1][j-1] == here);
            leftNeighbourDidNotCountOut = left != here || !beenHere.contains(new ReversedPosition(i, j - 1));
        }
        if (inboundsRight) {
            char right = matrix[i][j+1];
            rightNeighbourDidNotCountUp = right != here || !beenHere.contains(new ReversedPosition(i, j + 1)) || (inboundsUp && matrix[i - 1][j] == here) || (inboundsDownRight && matrix[i+1][j+1] == here);
            rightNeighbourDidNotCountDown = right != here || !beenHere.contains(new ReversedPosition(i, j + 1)) || (inboundsDown && matrix[i + 1][j] == here) || (inboundsDownRight && matrix[i+1][j+1] == here);
            rightNeighbourDidNotCountOut = right != here || !beenHere.contains(new ReversedPosition(i, j + 1));
        }

        if (!inboundsUp && leftNeighbourDidNotCountOut && rightNeighbourDidNotCountOut) {
            perimeter++;
        } else if (inboundsUp && matrix[i-1][j] != here && leftNeighbourDidNotCountUp && rightNeighbourDidNotCountUp) {
            perimeter++;
        }

        if (!inboundsDown && leftNeighbourDidNotCountOut && rightNeighbourDidNotCountOut) {
            perimeter++;
        } else if (inboundsDown && matrix[i+1][j] != here && leftNeighbourDidNotCountDown && rightNeighbourDidNotCountDown) {
            perimeter++;
        }

        if (!inboundsLeft && upNeighbourDidNotCountOut && downNeighbourDidNotCountOut) {
            perimeter++;
        } else if (inboundsLeft && matrix[i][j-1] != here && upNeighbourDidNotCountLeft && downNeighbourDidNotCountLeft) {
            perimeter++;
        }

        if (!inboundsRight && upNeighbourDidNotCountOut && downNeighbourDidNotCountOut) {
            perimeter++;
        } else if (inboundsRight && matrix[i][j+1] != here && upNeighbourDidNotCountRight && downNeighbourDidNotCountRight) {
            perimeter++;
        }

        return perimeter;
    }

}
