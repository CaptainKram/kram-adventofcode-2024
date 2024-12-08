package kram.advent.day8;

import kram.advent.records.ReversedPosition;
import kram.advent.utils.ReadFromFile;
import kram.advent.utils.StringUtil;

import java.util.*;

public class ResonantCollinearity {

    private static final Map<Character, List<ReversedPosition>> antennasMap = new HashMap<>();
    public static Set<ReversedPosition> uniquePositions = new HashSet<>();

    public static void main(String[] args) {
        String input = ReadFromFile.readFileString("antennas");
        char[][] chars = StringUtil.stringToCharArr(input);
        fillAntennasMap(chars);
        findAntinodes(chars);

        System.out.println(uniquePositions.size());
    }

    public static void fillAntennasMap(char[][] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                char c = chars[i][j];
                if (c != '.') {
                    if (antennasMap.containsKey(c)) {
                        antennasMap.get(c).add(new ReversedPosition(i, j));
                    } else {
                        antennasMap.put(c, new ArrayList<>());
                        antennasMap.get(c).add(new ReversedPosition(i, j));
                    }
                }
            }
        }
    }
    
    public static void findAntinodes(char[][] chars) {
        for (Map.Entry<Character, List<ReversedPosition>> entry : antennasMap.entrySet()) {
            List<ReversedPosition> positions = entry.getValue();
            for (int i = 0; i < positions.size(); i++) {
                ReversedPosition pi = positions.get(i);
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    ReversedPosition pj = positions.get(j);
                    int ydiff = Math.abs(pi.y() - pj.y());
                    int xdiff = Math.abs(pi.x() - pj.x());
                    boolean iBiggerY = pi.y() > pj.y();
                    boolean iBiggerX = pi.x() > pj.x();
                    if (iBiggerY && iBiggerX) {
                        insertPosition(pi.y() + ydiff, pi.x() + xdiff, chars);
                        insertPosition(pj.y() - ydiff, pj.x() - xdiff, chars);
                    } else if (iBiggerY) {
                        insertPosition(pi.y() + ydiff, pi.x() - xdiff, chars);
                        insertPosition(pj.y() - ydiff, pj.x() + xdiff, chars);
                    } else if (iBiggerX) {
                        insertPosition(pi.y() - ydiff, pi.x() + xdiff, chars);
                        insertPosition(pj.y() + ydiff, pj.x() - xdiff, chars);
                    } else {
                        insertPosition(pi.y() - ydiff, pi.x() - xdiff, chars);
                        insertPosition(pj.y() + ydiff, pj.x() + xdiff, chars);
                    }
                }
            }
        }
    }

    private static void insertPosition(int y, int x, char[][] chars) {
        if (y >= 0 && x >= 0 && y < chars.length && x < chars[y].length) {
            uniquePositions.add(new ReversedPosition(y, x));
        }
    }

    private static void printAntinodes(char[][] chars) {
        for (ReversedPosition position : uniquePositions) {
            if (chars[position.y()][position.x()] == '.') {
                chars[position.y()][position.x()] = '#';
            }
        }
        for (char[] ch : chars) {
            System.out.println(ch);
        }
    }

}
