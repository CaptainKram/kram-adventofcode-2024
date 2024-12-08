package kram.advent.day8;

import kram.advent.records.ReversedPosition;
import kram.advent.utils.ReadFromFile;
import kram.advent.utils.StringUtil;

import java.util.*;

public class ResonantCollinearity {

    private static final Map<Character, Integer> charsCount = new HashMap<>();
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
                        charsCount.put(c, charsCount.get(c) + 1);
                    } else {
                        antennasMap.put(c, new ArrayList<>());
                        antennasMap.get(c).add(new ReversedPosition(i, j));
                        charsCount.put(c, 1);
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
                    handlePositions(chars, entry.getKey(), pi.y(), pi.x(), pj.y(), pj.x());
                }
                if (charsCount.get(entry.getKey()) >= 2) {
                    uniquePositions.add(pi);
                }
            }
        }
    }

    private static void handlePositions(char[][] chars, char ch, int y1, int x1, int y2, int x2) {
        int ydiff = Math.abs(y1 - y2);
        int xdiff = Math.abs(x1 - x2);
        boolean iBiggerY = y1 > y2;
        boolean iBiggerX = x1 > x2;
        boolean higher;
        boolean lower;
        int valueHolderY = ydiff;
        int valueHolderX = xdiff;
        do {
            if (iBiggerY && iBiggerX) {
                higher = insertPosition(chars, y1 + ydiff, x1 + xdiff);
                lower = insertPosition(chars, y2 - ydiff, x2 - xdiff);
            } else if (iBiggerY) {
                higher = insertPosition(chars, y1 + ydiff, x1 - xdiff);
                lower = insertPosition(chars, y2 - ydiff, x2 + xdiff);
            } else if (iBiggerX) {
                higher = insertPosition(chars, y1 - ydiff, x1 + xdiff);
                lower = insertPosition(chars, y2 + ydiff, x2 - xdiff);
            } else {
                higher = insertPosition(chars, y1 - ydiff, x1 - xdiff);
                lower = insertPosition(chars, y2 + ydiff, x2 + xdiff);
            }
            ydiff += valueHolderY;
            xdiff += valueHolderX;
            if (charsCount.get(ch) < 2) {
                higher = false;
                lower = false;
            }
        } while (higher || lower);
    }

    private static boolean insertPosition(char[][] chars, int y, int x) {
        if (y >= 0 && x >= 0 && y < chars.length && x < chars[y].length) {
            uniquePositions.add(new ReversedPosition(y, x));
            return true;
        }
        return false;
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
