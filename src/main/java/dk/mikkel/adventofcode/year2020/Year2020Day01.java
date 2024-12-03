package dk.mikkel.adventofcode.year2020;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day01 {

    private static Logger logger = LogManager.getLogger(Year2020Day01.class);

    public static void main(String[] args) {
        new Year2020Day01().runner();
    }

    public void runner() {

        Integer[] input = FileReader.readFileToList("year2020/day_01.input").stream().map(Integer::parseInt)
                .toArray(Integer[]::new);

        boolean puzzleOneNFound = false;
        boolean puzzleTwoFound = false;

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                puzzleOneNFound |= checkForPairWithSum2020(input, puzzleOneNFound, i, j);
                puzzleTwoFound |= checkForTripletSum2020(input, puzzleTwoFound, i, j);
                if (puzzleOneNFound && puzzleTwoFound) {
                    return;
                }
            }
        }
    }

    private boolean checkForTripletSum2020(Integer[] input, boolean puzzleTwoFound, int i, int j) {
        if (!puzzleTwoFound) {
            for (int k = j + 1; k < input.length; k++) {
                if (input[i] + input[j] + input[k] == 2020) {
                    logger.info("Day 1: Report Repair : Puzzle 2 : {} ", (input[i] * input[j] * input[k]));
                    puzzleTwoFound = true;
                }
            }
        }
        return puzzleTwoFound;
    }

    private boolean checkForPairWithSum2020(Integer[] input, boolean puzzleOneNFound, int i, int j) {
        if (input[i] + input[j] == 2020) {
            logger.info("Day 1: Report Repair : Puzzle 1 : {}", (input[i] * input[j]));
            puzzleOneNFound = true;
        }
        return puzzleOneNFound;
    }
}
