package dk.mikkel.adventofcode.year2024;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day04 {

    private static Logger logger = LogManager.getLogger(Year2024Day05.class);

    public static void main(String[] args) {
        new Year2024Day05().runner();
    }

    public void runner() {

        String[] input = FileReader.readFileToArray(2024, 4);
        puzzleOne(input);
        puzzleTwo(input);
    }

    public void puzzleOne(String[] input) {
        logger.info("Puzzle 1");

        char[][] map = new char[input.length][input[0].length()];

        int count = 0;

        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                // System.out.print(row[j]);

                if (row[j] == 'X') {
                    if (i > 2 && input[i - 1].charAt(j) == 'M'
                            && input[i - 2].charAt(j) == 'A'
                            && input[i - 3].charAt(j) == 'S') {
                        map[i][j] = 'X';
                        map[i - 1][j] = 'M';
                        map[i - 2][j] = 'A';
                        map[i - 3][j] = 'S';
                        count++;
                    }

                    if (i < input.length - 3 && input[i + 1].charAt(j) == 'M' && input[i + 2].charAt(j) == 'A'
                            && input[i + 3].charAt(j) == 'S') {
                        map[i][j] = 'X';
                        map[i + 1][j] = 'M';
                        map[i + 2][j] = 'A';
                        map[i + 3][j] = 'S';
                        count++;
                    }

                    if (j > 2 && input[i].charAt(j - 1) == 'M' && input[i].charAt(j - 2) == 'A'
                            && input[i].charAt(j - 3) == 'S') {
                        map[i][j] = 'X';
                        map[i][j - 1] = 'M';
                        map[i][j - 2] = 'A';
                        map[i][j - 3] = 'S';
                        count++;
                    }

                    if (j < row.length - 3 && input[i].charAt(j + 1) == 'M' && input[i].charAt(j + 2) == 'A'
                            && input[i].charAt(j + 3) == 'S') {
                        map[i][j] = 'X';
                        map[i][j + 1] = 'M';
                        map[i][j + 2] = 'A';
                        map[i][j + 3] = 'S';
                        count++;
                    }

                    // Diagonals

                    if (j < row.length - 3 && i < input.length - 3 && input[i+1].charAt(j + 1) == 'M' && input[i+2].charAt(j + 2) == 'A'
                            && input[i+3].charAt(j + 3) == 'S') {
                        map[i][j] = 'X';
                        map[i + 1][j + 1] = 'M';
                        map[i + 2][j + 2] = 'A';
                        map[i + 3][j + 3] = 'S';
                        count++;
                    }

                    if (j > 2 && i > 2 
                            && input[i-1].charAt(j - 1) == 'M' 
                            && input[i-2].charAt(j - 2) == 'A'
                            && input[i-3].charAt(j - 3) == 'S') {
                        map[i][j] = 'X';
                        map[i - 1][j - 1] = 'M';
                        map[i - 2][j - 2] = 'A';
                        map[i - 3][j - 3] = 'S';
                        count++;
                    }

                    if (j < row.length - 3 && i > 2 && input[i-1].charAt(j + 1) == 'M' && input[i-2].charAt(j + 2) == 'A'
                            && input[i-3].charAt(j + 3) == 'S') {
                        map[i][j] = 'X';
                        map[i - 1][j + 1] = 'M';
                        map[i - 2][j + 2] = 'A';
                        map[i - 3][j + 3] = 'S';
                        count++;
                    }

                    if (j > 2 && i < input.length - 3 && input[i+1].charAt(j - 1) == 'M' && input[i+2].charAt(j - 2) == 'A'
                            && input[i+3].charAt(j - 3) == 'S') {
                        map[i][j] = 'X';
                        map[i + 1][j - 1] = 'M';
                        map[i + 2][j - 2] = 'A';
                        map[i + 3][j - 3] = 'S';
                        count++;
                    }

                } else {
                    // map[i][j] = '.';
                }

            }

            

        }


        for (int i = 0; i < map.length; i++) {
            char[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j] == 0 ? '.' : row[j];

                System.out.print(c);

            }
            System.out.println();
        }

        System.out.println(count);

    }

    public void puzzleTwo(String[] input) {
        logger.info("Puzzle 2");

        char[][] map = new char[input.length][input[0].length()];

        int count = 0;

        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                // System.out.print(row[j]);
                // MAS
                if (row[j] == 'A' && i > 0 && j > 0 && j < row.length - 1 && i < input.length - 1) {
                    System.out.println("Found A at " + i + ", " + j);
                    char m = 'M';
                    char s = 'S';
                    boolean c1 = checkPartOne(input, map, i, j, m, s);
                    boolean c2 = checkPartOne(input, map, i, j, s, m);
                    
                    boolean c3 = checkPartTwo(input, map, i, j, m, s);
                    boolean c4 = checkPartTwo(input, map, i, j, s, m);

                    if (c1 && c3 || c1 && c4 || c2 && c3 || c2 && c4){
                        count++;
                    }


                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            char[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j] == 0 ? '.' : row[j];

                System.out.print(c);

            }
            System.out.println();
        }

        System.out.println(count);
    }

    private boolean checkPartOne(String[] input, char[][] map, int i, int j, char m, char s) {
        if (input[i - 1].charAt(j + 1) == m 
         && input[i + 1].charAt(j - 1) == s) {
            map[i][j] = 'A';
            map[i - 1][j + 1] = m;
            map[i + 1][j - 1] = s;
            return true;
        }
        return false;
    }

    private boolean checkPartTwo(String[] input, char[][] map, int i, int j, char m, char s) {
        if (input[i - 1].charAt(j - 1) == m 
         && input[i + 1].charAt(j + 1) == s) {
            map[i][j] = 'A';
            map[i - 1][j - 1] = m;
            map[i + 1][j + 1] = s;
            return true;
        }
        return false;
    }

}
