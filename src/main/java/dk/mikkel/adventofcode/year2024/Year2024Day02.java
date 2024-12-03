package dk.mikkel.adventofcode.year2024;


import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day02 {

    private static Logger logger = LogManager.getLogger(Year2024Day02.class);

    public static void main(String[] args) {
        new Year2024Day02().runner();
    }

    private void runner() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        logger.info("Puzzle 1");

        List<String> lines = FileReader.readFileToList("year2024/day_02.input");

        int count = 0;
        for (String s : lines) {
            String[] split = s.split("\s+");
            if (isSafe(split)) {
                count++;
            }
        }
        logger.info(count);

    }

    private void puzzleTwo() {
        logger.info("Puzzle 2");

        List<String> lines = FileReader.readFileToList("year2024/day_02.input");

        int count = 0;
        for (String s : lines) {
            String[] split = s.split("\s+");
            if (isSafe(split)) {
                count++;
            } else {
                for (int i = 0; i < split.length; i++) {
                    String[] newSplit = ArrayUtils.addAll(Arrays.copyOfRange(split, 0, i) , Arrays.copyOfRange(split, i + 1, split.length));
                    
                    if (isSafe(newSplit)) {
                        count++;
                        break;
                    }
                }  
            }
        }
        logger.info(count);
    }

    private boolean isSafe(String[] split) {
        int last = 0;
        for (int i = 0; i < split.length - 1; i++) {
            int a = Integer.parseInt(split[i]);
            int b = Integer.parseInt(split[i + 1]);
            int current = a - b;
            if (isSafe(last, current)) {
                return false;
            }
            last = current;
        }

        return true;
    }

    private boolean isSafe(int last, int current) {
        return current == 0 || last > 0 && current < 0 || last < 0 && current > 0 || Math.abs(current) > 3;
    }



}
