package dk.mikkel.adventofcode.year2020;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day01 {
    public static void main(String[] args) {
        new Year2020Day01().runner();
    }

    public void runner() {

        Integer[] input = FileReader.readFileToList("day_1.txt").stream().map(Integer::parseInt)
                .toArray(Integer[]::new);

        boolean puzzleOneNFound = false;
        boolean puzzleTwoFound = false;

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == 2020) {
                    System.out.println("Day 1: Report Repair : Puzzle 1 :" + (input[i] * input[j]));
                    puzzleOneNFound = true;
                }
                if (!puzzleTwoFound) {
                    for (int k = j + 1; k < input.length; k++) {
                        if (input[i] + input[j] + input[k] == 2020) {
                            System.out.println("Day 1: Report Repair : Puzzle 2 :" + (input[i] * input[j] * input[k]));
                            puzzleTwoFound = true;
                        }
                    }
                }
                if (puzzleOneNFound && puzzleTwoFound) {
                    return;
                }
            }
        }
    }
}
