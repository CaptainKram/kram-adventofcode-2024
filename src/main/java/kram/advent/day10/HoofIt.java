package kram.advent.day10;

import kram.advent.records.ReversedPosition;
import kram.advent.utils.ReadFromFile;
import kram.advent.utils.StringUtil;

import java.util.*;

public class HoofIt {

    public static List<ReversedPosition> hills = new ArrayList<>();

    public static void main(String[] args) {
        String input = ReadFromFile.readFileString("topographicmap");
        char[][] matrix = StringUtil.stringToCharArr(input);

        int sum = 0;
        List<ReversedPosition> trailHeads = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char current = matrix[i][j];
                if (current == '0') {
                    ReversedPosition position = new ReversedPosition(i, j);
                    Integer score = findHills(position, matrix);
                    sum += score;
                    trailHeads.add(position);
                } else if (current == '9') {
                    hills.add(new ReversedPosition(i, j));
                }
            }
        }
        System.out.println(sum);

        int reachedHills = 0;
        for (ReversedPosition trailHead : trailHeads) {
            for (ReversedPosition hill : hills) {
                List<ReversedPosition> beenHere = new ArrayList<>();
                if (findHill(matrix, trailHead, hill, beenHere)) {
                    reachedHills++;
                }
            }
        }
//        System.out.println(reachedHills);
    }

    public static boolean findHill(char[][] matrix, ReversedPosition trailHead, ReversedPosition hill, List<ReversedPosition> beenHere) {
        int y = trailHead.y();
        int x = trailHead.x();
        char currentValue = matrix[y][x];
        beenHere.add(trailHead);

        boolean foundIt = false;
        if (trailHead.equals(hill)) {
            return true;
        }
        if (currentValue == '9') {
            return false;
        }

        ReversedPosition up = new ReversedPosition(y - 1, x);
        ReversedPosition down = new ReversedPosition(y + 1, x);
        ReversedPosition left = new ReversedPosition(y, x - 1);
        ReversedPosition right = new ReversedPosition(y, x + 1);

        boolean canGoUp = y - 1 >= 0 && matrix[y - 1][x] == currentValue + 1 && !beenHere.contains(up);
        boolean canGoDown = y + 1 < matrix.length && matrix[y + 1][x] == currentValue + 1 && !beenHere.contains(down);
        boolean canGoLeft = x - 1 >= 0 && matrix[y][x - 1] == currentValue + 1 && !beenHere.contains(left);
        boolean canGoRight = x + 1 < matrix[y].length && matrix[y][x + 1] == currentValue + 1 && !beenHere.contains(right);

        if (canGoUp) {
            foundIt = findHill(matrix, up, hill, beenHere);
        }
        if (canGoDown && !foundIt) {
            foundIt = findHill(matrix, down, hill, beenHere);
        }
        if (canGoLeft && !foundIt) {
            foundIt = findHill(matrix, left, hill, beenHere);
        }
        if (canGoRight && !foundIt) {
            foundIt = findHill(matrix, right, hill, beenHere);
        }
        return foundIt;
    }

    public static Integer findHills(ReversedPosition position, char[][] matrix) {
        int y = position.y();
        int x = position.x();
        int currentValue = matrix[y][x];

        int score = 0;
        if (currentValue == '9') {
            return ++score;
        }

        ReversedPosition up = new ReversedPosition(y - 1, x);
        ReversedPosition down = new ReversedPosition(y + 1, x);
        ReversedPosition left = new ReversedPosition(y, x - 1);
        ReversedPosition right = new ReversedPosition(y, x + 1);

        boolean canGoUp = y - 1 >= 0 && matrix[y - 1][x] == currentValue + 1;
        boolean canGoDown = y + 1 < matrix.length && matrix[y + 1][x] == currentValue + 1;
        boolean canGoLeft = x - 1 >= 0 && matrix[y][x - 1] == currentValue + 1;
        boolean canGoRight = x + 1 < matrix[y].length && matrix[y][x + 1] == currentValue + 1;

        if (canGoUp) {
            score += findHills(up, matrix);
        }
        if (canGoDown) {
            score += findHills(down, matrix);
        }
        if (canGoLeft) {
            score += findHills(left, matrix);
        }
        if (canGoRight) {
            score += findHills(right, matrix);
        }
        return score;
    }

}
