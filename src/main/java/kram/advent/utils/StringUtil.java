package kram.advent.utils;

public class StringUtil {

    public static char[][] stringToCharArr(String s) {
        String[] lines = s.split("\\n");
        char[][] matrix = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            matrix[i] = lines[i].toCharArray();
        }
        return matrix;
    }

}
