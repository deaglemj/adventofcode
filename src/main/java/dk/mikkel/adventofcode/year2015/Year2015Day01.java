package dk.mikkel.adventofcode.year2015;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Base;
import dk.mikkel.adventofcode.util.FileReader;

public class Year2015Day01 extends Base<Integer, Integer> {

    private final static String TITLE = "Day 1: Not Quite Lisp";
    private static Logger logger = LogManager.getLogger(Year2015Day01.class);

    public static void main(String[] args) {
        Year2015Day01 year2015Day01 = new Year2015Day01();
        List<String> input = FileReader.readFileToList(2015, 1);
        Integer resultPart1 = year2015Day01.solvePart1(input.get(0));
        Integer resultPart2 = year2015Day01.solvePart2(input.get(0));

        logger.info("{} - part 1 {}", TITLE, resultPart1);
        logger.info("{} - part 2 {}", TITLE, resultPart2);

    }

    @Override
    protected Integer solvePart1(String input) {
        int level = 0;
        for (int i = 0; i < input.length(); i++) {
            level += adjustLevel(input.charAt(i));
        }
        return level;
    }

    @Override
    protected Integer solvePart2(String input) {
        int currentLevel = 0;
        int position = 0;
        for (int i = position; i < input.length(); i++) {
            currentLevel += adjustLevel(input.charAt(i));
            if (currentLevel == -1) {
                position = i + 1;
                break;
            }
        }
        return position;
    }

    private int adjustLevel(char direction) {
        return direction == '(' ? 1 : -1;
    }
}
