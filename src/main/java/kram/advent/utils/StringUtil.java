package kram.advent.utils;

import java.util.List;
import java.util.stream.Stream;

public class StringUtil {

    public static char[][] stringToCharArr(String s) {
        List<String> stringList = stringToList(s);
        char[][] matrix = new char[stringList.size()][];
        for (int i = 0; i < stringList.size(); i++) {
            matrix[i] = stringList.get(i).toCharArray();
        }
        return matrix;
    }

    public static List<String> stringToList(String s) {
        Stream<String> linesStream =  s.lines();
        return linesStream.toList();
    }

    public static boolean inBounds(char[][] matrix, int x, int y) {
        return x >= 0 && y >= 0 && x < matrix[y].length && y < matrix.length;
    }

    public static boolean inBoundsUp(char[][] matrix, int y) {
        return y >= 0;
    }

    public static boolean inBoundsDown(char[][] matrix, int y) {
        return y < matrix.length;
    }

    public static boolean inBoundsLeft(char[][] matrix, int x) {
        return x >= 0;
    }

    public static boolean inBoundsRight(char[][] matrix, int x, int y) {
        return x < matrix[y].length;
    }

}
