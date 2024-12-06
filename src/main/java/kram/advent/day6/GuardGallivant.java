package kram.advent.day6;

import kram.advent.utils.ReadFromFile;
import kram.advent.utils.StringUtil;

import java.util.HashSet;
import java.util.Set;

import static kram.advent.day6.Guard.guardChar;

public class GuardGallivant {

    private static final Set<Position> markedPositions = new HashSet<>();
    private static final Set<Position> obstructionPotential = new HashSet<>();

    public static void main(String[] args) {

        String input = ReadFromFile.readFileString("guardpath");
        char[][] matrix = StringUtil.stringToCharArr(input);

        Guard guard = findGuard(matrix);
        markedPositions.add(guard.getPosition());
        obstructionPotential.add(guard.getPosition());

        while (!guard.isOutside()) {
            Position obstruction = guard.findObstructionPotential(matrix);
            if (obstruction != null) {
                obstructionPotential.add(obstruction);
            }
            Position p = guard.move(matrix);
            markedPositions.add(p);
        }

        System.out.println(obstructionPotential);
        System.out.println(obstructionPotential.size() - 1);
    }

    private static Guard findGuard(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (char ch : guardChar) {
                    if (ch == matrix[i][j]) {
                        return new Guard(new Position(j, i), ch);
                    }
                }
            }
        }
        return null;
    }

}
