package dk.mikkel.adventofcode.year2024;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day05 {

    private static Logger logger = LogManager.getLogger(Year2024Day05.class);

    public static void main(String[] args) {
        new Year2024Day04().runner();
    }

    public void runner() {

        String[] input = FileReader.readFileToArray(2024, 4);
        puzzleOne(input);
        puzzleTwo(input);
    }

    public void puzzleOne(String[] input) {
        logger.info("Puzzle 1");

    }

    public void puzzleTwo(String[] input) {
        logger.info("Puzzle 2");

        
    }

}
