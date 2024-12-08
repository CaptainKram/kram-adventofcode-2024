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

}
