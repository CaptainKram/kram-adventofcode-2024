package kram.advent.day6;

public class Guard {

    public static final char[] guardChar = {'^', 'v', '<', '>'};
    private Position position;
    private char icon;
    private boolean outside = false;

    public Guard(Position position, char icon) {
        this.position = position;
        this.icon = icon;
    }

    public boolean isOutside() {
        return outside;
    }

    public Position getPosition() {
        return position;
    }

    public Position move(char[][] matrix) {
        int y = this.position.y();
        int x = this.position.x();
        switch (this.icon) {
            case '^':
                if (y > 0) {
                    if (matrix[y-1][x] != '#') {
                        this.position = new Position(x, y-1);
                        return this.position;
                    }
                    this.icon = '>';
                    return this.position;
                }
                this.outside = true;
                return this.position;
            case '>':
                if (x < matrix[y].length - 1) {
                    if (matrix[y][x+1] != '#') {
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
                    if (matrix[y+1][x] != '#') {
                        this.position = new Position(x, y+1);
                        return this.position;
                    }
                    this.icon = '<';
                    return this.position;
                }
                this.outside = true;
                return this.position;
            case '<':
                if (x > 0) {
                    if (matrix[y][x-1] != '#') {
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

    private int y;
    private int x;

    public Position findObstructionPotential(char[][] matrix) {
        this.y = this.position.y();
        this.x = this.position.x();

        boolean up;
        boolean right;
        boolean down;
        boolean left;

        switch (this.icon) {
            case '^':
                up = checkForObstructionUp(matrix, 0);
                right = checkForObstructionRight(matrix, matrix[y].length - 1);
                down = checkForObstructionDown(matrix, this.position.y() + 1);
                if (this.position.x() - 1 >= 0 && up && right && down && matrix[this.position.x() - 1][this.position.y()] != '#') {
                    return new Position(this.position.x() - 1, this.position.y());
                }
                return null;
            case '>':
                right = checkForObstructionRight(matrix, matrix[y].length - 1);
                down = checkForObstructionDown(matrix, matrix.length - 1);
                left = checkForObstructionLeft(matrix, this.position.x() - 1);
                if (this.position.y() - 1 >= 0 && right && down && left && matrix[this.position.x()][this.position.y() - 1] != '#') {
                    return new Position(this.position.x(), this.position.y() - 1);
                }
                return null;
            case 'v':
                down = checkForObstructionDown(matrix, matrix.length - 1);
                left = checkForObstructionLeft(matrix, 0);
                up = checkForObstructionUp(matrix, this.position.y() - 1);
                if (this.position.x() + 1 < matrix[this.position.y()].length && down && left && up && matrix[this.position.x() + 1][this.position.y()] != '#') {
                    return new Position(this.position.x() + 1, this.position.y());
                }
                return null;
            case '<':
                left = checkForObstructionLeft(matrix, 0);
                up = checkForObstructionUp(matrix, 0);
                right = checkForObstructionRight(matrix, this.position.x() + 1);
                if (this.position.y() + 1 < matrix.length && left && up && right && matrix[this.position.x()][this.position.y() + 1] != '#') {
                    return new Position(this.position.x(), this.position.y() + 1);
                }
                return null;
        }
        return null;
    }

    private boolean checkForObstructionUp(char[][] matrix, int limit) {
        for (int i = y; i >= limit || i >= 0; i--) {
            if (matrix[i][x] == '#') {
                y = i + 1;
                return true;
            }
        }
        return false;
    }

    private boolean checkForObstructionRight(char[][] matrix, int limit) {
        for (int i = x; i <= limit || i < matrix[y].length; i++) {
            if (matrix[y][i] == '#') {
                x = i - 1;
                return true;
            }
        }
        return false;
    }

    private boolean checkForObstructionDown(char[][] matrix, int limit) {
        for (int i = y; i <= limit || i < matrix.length; i++) {
            if (matrix[i][x] == '#') {
                y = i - 1;
                return true;
            }
        }
        return false;
    }

    private boolean checkForObstructionLeft(char[][] matrix, int limit) {
        for (int i = x; i >= limit || i >= 0; i--) {
            if (matrix[y][i] == '#') {
                x = i + 1;
                return true;
            }
        }
        return false;
    }
}
