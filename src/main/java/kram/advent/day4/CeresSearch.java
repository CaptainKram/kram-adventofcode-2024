package kram.advent.day4;

import kram.advent.day2.ReadFromFile;

public class CeresSearch {

    public static void main(String[] args) {

        String input = ReadFromFile.readFileString("xmas");
        String[] lines = input.split("\\n");
        char[][] matrix = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            matrix[i] = lines[i].toCharArray();
        }

        int xmasCounter = 0;
        int masCounter = 0;
        for (int i = 0; i < matrix.length; i++) {
            int innerLength = matrix[i].length;
            for (int j = 0; j < innerLength; j++) {
                char currentChar = matrix[i][j];
                if (currentChar == 'X') {
                    // check forward
                    if (j + 3 < innerLength && matrix[i][j+1] == 'M' && matrix[i][j+2] == 'A' && matrix[i][j+3] == 'S') {
                        xmasCounter++;
                    }
                    // check backward
                    if (j - 3 >= 0 && matrix[i][j-1] == 'M' && matrix[i][j-2] == 'A' && matrix[i][j-3] == 'S') {
                        xmasCounter++;
                    }
                    // check up
                    if (i - 3 >= 0 && matrix[i-1][j] == 'M' && matrix[i-2][j] == 'A' && matrix[i-3][j] == 'S') {
                        xmasCounter++;
                    }
                    // check down
                    if (i + 3 < matrix.length && matrix[i+1][j] == 'M' && matrix[i+2][j] == 'A' && matrix[i+3][j] == 'S') {
                        xmasCounter++;
                    }
                    // check left diagonal up
                    if (i - 3 >= 0 && j - 3 >= 0 && matrix[i-1][j-1] == 'M' && matrix[i-2][j-2] == 'A' && matrix[i-3][j-3] == 'S') {
                        xmasCounter++;
                    }
                    // check right diagonal up
                    if (i - 3 >= 0 && j + 3 < innerLength && matrix[i-1][j+1] == 'M' && matrix[i-2][j+2] == 'A' && matrix[i-3][j+3] == 'S') {
                        xmasCounter++;
                    }
                    // check left diagonal down
                    if (i + 3 < matrix.length && j - 3 >= 0 && matrix[i+1][j-1] == 'M' && matrix[i+2][j-2] == 'A' && matrix[i+3][j-3] == 'S') {
                        xmasCounter++;
                    }
                    // check right diagonal down
                    if (i + 3 < matrix.length && j + 3 < innerLength && matrix[i+1][j+1] == 'M' && matrix[i+2][j+2] == 'A' && matrix[i+3][j+3] == 'S') {
                        xmasCounter++;
                    }
                } else if (currentChar == 'A') {
                    if (i - 1 >= 0 && i + 1 < matrix.length && j - 1 >= 0 && j + 1 < innerLength) {
                        boolean upLeftM = matrix[i-1][j-1] == 'M';
                        boolean upLeftS = matrix[i-1][j-1] == 'S';
                        boolean upRightM = matrix[i-1][j+1] == 'M';
                        boolean upRightS = matrix[i-1][j+1] == 'S';
                        boolean downLeftM = matrix[i+1][j-1] == 'M';
                        boolean downLeftS = matrix[i+1][j-1] == 'S';
                        boolean downRightM = matrix[i+1][j+1] == 'M';
                        boolean downRightS = matrix[i+1][j+1] == 'S';

                        if (upLeftM && downRightS && upRightM && downLeftS) {
                            masCounter++;
                        } else if (upLeftM && downRightS && upRightS && downLeftM) {
                            masCounter++;
                        } else if (upLeftS && downRightM && upRightM && downLeftS) {
                            masCounter++;
                        } else if (upLeftS && downRightM && upRightS && downLeftM) {
                            masCounter++;
                        }
                    }
                }
            }
        }

        System.out.println(masCounter);

    }

}
