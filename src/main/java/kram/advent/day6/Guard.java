package kram.advent.day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Guard {

    public static final char[] guardChar = {'^', 'v', '<', '>'};
    Map<Character, Set<Position>> markings = new HashMap<>();
    private Position position;
    private char icon;
    private boolean outside = false;

    public Guard(Position position, char icon) {
        this.position = position;
        this.icon = icon;
        for (Character c : guardChar) {
            markings.put(c, new HashSet<>());
        }
    }

    public boolean isOutside() {
        return outside;
    }

    public Position getPosition() {
        return position;
    }

    private boolean haveNotBeenHere(int x, int y) {
        Position p = new Position(x, y);
        for (char c : guardChar) {
            if (markings.get(c).contains(p)) {
                return false;
            }
        }
        return true;
    }

    public Position move(char[][] matrix) {
        int y = this.position.y();
        int x = this.position.x();

        this.markings.get(this.icon).add(this.position);
        switch (this.icon) {
            case '^':
                if (y > 0) {
                    if (matrix[y - 1][x] != '#') {
                        this.position = new Position(x, y - 1);
                        return this.position;
                    }
                    this.icon = '>';
                    return this.position;
                }
                this.outside = true;
                return this.position;
            case '>':
                if (x < matrix[y].length - 1) {
                    if (matrix[y][x + 1] != '#') {
                        this.position = new Position(x + 1, y);
                        return this.position;
                    }
                    this.icon = 'v';
                    return this.position;
                }
                this.outside = true;
                return this.position;
            case 'v':
                if (y < matrix.length - 1) {
                    if (matrix[y + 1][x] != '#') {
                        this.position = new Position(x, y + 1);
                        return this.position;
                    }
                    this.icon = '<';
                    return this.position;
                }
                this.outside = true;
                return this.position;
            case '<':
                if (x > 0) {
                    if (matrix[y][x - 1] != '#') {
                        this.position = new Position(x - 1, y);
                        return this.position;
                    }
                    this.icon = '^';
                    return this.position;
                }
                this.outside = true;
                return this.position;
        }
        return null;
    }

    /**
     * Tries turning right from wherever he is facing,
     * if he reaches a place he has been before - we add a wall infront of him
     */
    public Position haveIBeenHereBefore(char[][] matrix) {
        int x = this.position.x();
        int y = this.position.y();
        int turns = 200;
        Map<Character, Set<Position>> tempPositions = new HashMap<>();
        for (Character c : guardChar) {
            tempPositions.put(c, new HashSet<>());
        }
        return switch (this.icon) {
            case '^' -> {
                if (y - 1 >= 0 && matrix[y - 1][x] != '#') {
                    if (haveNotBeenHere(x, y-1) && checkRight(matrix, turns, x, y, tempPositions, new Position(x, y - 1))) {
                        yield new Position(x, y - 1);
                    }
                }
                yield null;
            }
            case '>' -> {
                if (x + 1 < matrix[y].length && matrix[y][x + 1] != '#') {
                    if (haveNotBeenHere(x+1, y) && checkDown(matrix, turns, x, y, tempPositions, new Position(x + 1, y))) {
                        yield new Position(x + 1, y);
                    }
                }
                yield null;
            }
            case 'v' -> {
                if (y + 1 < matrix.length && matrix[y + 1][x] != '#') {
                    if (haveNotBeenHere(x, y+1) && checkLeft(matrix, turns, x, y, tempPositions, new Position(x, y + 1))) {
                        yield new Position(x, y + 1);
                    }
                }
                yield null;
            }
            case '<' -> {
                if (x - 1 >= 0 && matrix[y][x - 1] != '#') {
                    if (haveNotBeenHere(x-1, y) && checkUp(matrix, turns, x, y, tempPositions, new Position(x - 1, y))) {
                        yield new Position(x - 1, y);
                    }
                }
                yield null;
            }
            default -> null;
        };
    }

    private boolean checkUp(char[][] matrix, int turns, int x, int y, Map<Character, Set<Position>> tempPositions, Position avoid) {
        for (int i = y; i >= 0; i--) {
            if (matrix[i][x] == '#' || (i == avoid.y() && x == avoid.x())) {
                if (turns > 0) {
                    turns--;
                    return checkRight(matrix, turns, x, i + 1, tempPositions, avoid);
                }
                return false;
            }
            if (this.markings.get('^').contains(new Position(x, i)) || tempPositions.get('^').contains(new Position(x, i))) {
                return true;
            }
            tempPositions.get('^').add(new Position(x, i));
        }
        return false;
    }

    private boolean checkRight(char[][] matrix, int turns, int x, int y, Map<Character, Set<Position>> tempPositions, Position avoid) {
        for (int i = x; i < matrix[y].length; i++) {
            if (matrix[y][i] == '#' || (y == avoid.y() && i == avoid.x())) {
                if (turns > 0) {
                    turns--;
                    return checkDown(matrix, turns, i - 1, y, tempPositions, avoid);
                }
                return false;
            }
            if (this.markings.get('>').contains(new Position(i, y)) || tempPositions.get('>').contains(new Position(i, y))) {
                return true;
            }
            tempPositions.get('>').add(new Position(i, y));
        }
        return false;
    }

    private boolean checkDown(char[][] matrix, int turns, int x, int y, Map<Character, Set<Position>> tempPositions, Position avoid) {
        for (int i = y; i < matrix.length; i++) {
            if (matrix[i][x] == '#' || (i == avoid.y() && x == avoid.x())) {
                if (turns > 0) {
                    turns--;
                    return checkLeft(matrix, turns, x, i - 1, tempPositions, avoid);
                }
                return false;
            }
            if (this.markings.get('v').contains(new Position(x, i)) || tempPositions.get('v').contains(new Position(x, i))) {
                return true;
            }
            tempPositions.get('v').add(new Position(x, i));
        }
        return false;
    }

    private boolean checkLeft(char[][] matrix, int turns, int x, int y, Map<Character, Set<Position>> tempPositions, Position avoid) {
        for (int i = x; i >= 0; i--) {
            if (matrix[y][i] == '#' || (y == avoid.y() && i == avoid.x())) {
                if (turns > 0) {
                    turns--;
                    return checkUp(matrix, turns, i + 1, y, tempPositions, avoid);
                }
                return false;
            }
            if (this.markings.get('<').contains(new Position(i, y)) || tempPositions.get('<').contains(new Position(i, y))) {
                return true;
            }
            tempPositions.get('<').add(new Position(i, y));
        }
        return false;
    }
}