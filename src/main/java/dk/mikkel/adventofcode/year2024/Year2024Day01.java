package dk.mikkel.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day01 {

    private static Logger logger = LogManager.getLogger(Year2024Day01.class);

    public static void main(String[] args) {
        new Year2024Day01().runner();
    }

    private void runner() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        logger.info("Puzzle 1");
        List<String> lines = FileReader.readFileToList("year2024/day_1.input");

        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        lines.stream().forEach(s -> {
            String[] split = s.split("\s+");
            leftSide.add(Integer.parseInt(split[0]));
            rightSide.add(Integer.parseInt(split[1]));
        });
        leftSide.sort(Integer::compareTo);
        rightSide.sort(Integer::compareTo);

        long distance = 0;
        for (int i = 0; i < leftSide.size(); i++) {

            distance += Math.abs(leftSide.get(i) - rightSide.get(i));

        }

        logger.info(distance);
    }

    private void puzzleTwo() {
        logger.info("Puzzle 2");

        List<String> lines = FileReader.readFileToList("year2024/day_1.input");

        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        lines.stream().forEach(s -> {
            String[] split = s.split("\s+");
            leftSide.add(Integer.parseInt(split[0]));
            rightSide.add(Integer.parseInt(split[1]));
        });
        leftSide.sort(Integer::compareTo);
        rightSide.sort(Integer::compareTo);

        long distance = 0;
        for (int i = 0; i < leftSide.size(); i++) {
            Integer value = leftSide.get(i);

            long count = rightSide.stream().filter(r -> Objects.equals(r, value)).count();

            distance += count * value;

        }

        logger.info(distance);
    }

}
