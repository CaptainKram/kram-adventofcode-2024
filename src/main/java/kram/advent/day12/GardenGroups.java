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
        if (!StringUtil.inBoundsUp(matrix, i-1) || matrix[i-1][j] != matrix[i][j]) {
            perimeter++;
        }
        if (!StringUtil.inBoundsDown(matrix, i+1) || matrix[i+1][j] != matrix[i][j]) {
            perimeter++;
        }
        if (!StringUtil.inBoundsLeft(matrix, j-1) || matrix[i][j-1] != matrix[i][j]) {
            perimeter++;
        }
        if (!StringUtil.inBoundsRight(matrix, j+1, i) || matrix[i][j+1] != matrix[i][j]) {
            perimeter++;
        }
        return perimeter;
    }

}
