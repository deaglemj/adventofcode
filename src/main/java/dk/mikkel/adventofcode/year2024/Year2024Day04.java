package dk.mikkel.adventofcode.year2024;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day04 {

    private static Logger logger = LogManager.getLogger(Year2024Day04.class);

    public static void main(String[] args) {
        new Year2024Day04().runner();
    }

    public void runner() {

        String[] input = FileReader.readFileToArray(2024, 4, true);
        puzzleOne(input);
        // puzzleTwo(instructions);
    }

    public void puzzleOne(String[] input) {
        logger.info("Puzzle 1");

        char[][] map = new char[input.length][input[0].length()];

        for (int i = 0; i < input.length; i++) {
            char[] row = input[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                // System.out.print(row[j]);

                if (row[j] == 'X') {
                    if (i > 3 && input[i - 1].charAt(j) == 'M') {
                        if (i > 2 && input[i - 2].charAt(j) == 'A') {
                            if (i > 1 && input[i - 3].charAt(j) == 'S') {
                                map[i][j] = 'X';
                                map[i - 1][j] = 'M';
                                map[i - 2][j] = 'A';
                                map[i - 3][j] = 'S';
                            }
                        }
                    }
                    
                    if (i < input.length - 3 && input[i + 1].charAt(j) == 'M') {
                        if (i < input.length - 2 && input[i + 2].charAt(j) == 'A') {
                            if (i < input.length - 1 && input[i + 3].charAt(j) == 'S') {
                                map[i][j] = 'X';
                                map[i + 1][j] = 'M';
                                map[i + 2][j] = 'A';
                                map[i + 3][j] = 'S';
                            }
                        }
                    }

                    if (j > 3 && input[i].charAt(j - 1) == 'M') {
                        if (j > 2 && input[i].charAt(j - 2) == 'A') {
                            if (j > 1 && input[i].charAt(j - 3) == 'S') {
                                map[i][j] = 'X';
                                map[i][j - 1] = 'M';
                                map[i][j - 2] = 'A';
                                map[i][j - 3] = 'S';
                            }
                        }
                    }

                    if (j < row.length - 3 && input[i].charAt(j + 1) == 'M') {
                        if (j < row.length - 2 && input[i].charAt(j + 2) == 'A') {
                            if (j < row.length - 1 && input[i].charAt(j + 3) == 'S') {
                                map[i][j] = 'X';
                                map[i][j + 1] = 'M';
                                map[i][j + 2] = 'A';
                                map[i][j + 3] = 'S';
                            }
                        }
                    }
                } else {
                    map[i][j] = '.';
                }

            }

            System.out.println();

        }

        

        for (int i = 0; i < map.length; i++) {
            char[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j]);
                System.out.print("-");
            }
            System.out.println();
        }

    }

    public void puzzleTwo(List<String> instructions) {
        logger.info("Puzzle 2");

    }

}
