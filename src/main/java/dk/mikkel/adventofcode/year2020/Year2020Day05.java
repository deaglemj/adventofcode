package dk.mikkel.adventofcode.year2020;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day05 {

    private static Logger logger = LogManager.getLogger(Year2020Day05.class);

    public static void main(String[] args) {
        new Year2020Day05().run();
    }

    public void run() {

        // puzzleOne();
        puzzleTwo();

    }

    private void puzzleTwo() {

        FileReader.readFileToList(2020, 5).stream().forEach(line -> {
            logger.info("Day 5: Binary Boarding : Puzzle 2 : {} ", line);
        });

        throw new UnsupportedOperationException("Unimplemented method 'puzzleTwo'");
    }

    private void puzzleOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'puzzleOne'");
    }

}
