package dk.mikkel.adventofcode.year2024;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day03 {

    private static Logger logger = LogManager.getLogger(Year2024Day03.class);

    public static void main(String[] args) {
        new Year2024Day03().runner();
    }

    public void runner() {

        List<String> instructions = FileReader.readFileToList("year2024/day_03.input");
        puzzleOne(instructions);
        puzzleTwo(instructions);
    }

    public void puzzleOne(List<String> instructions) {
        logger.info("Puzzle 1");

        Status status = new Status();
        final String regex = "mul\\((\\d+),(\\d+)\\)";
        instructions.stream().forEach(t -> {
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(t);

            while (matcher.find()) {
                status.sum += Long.valueOf(matcher.group(1)) * Long.valueOf(matcher.group(2));
            }

        });

        logger.info(status.sum);

    }

    public void puzzleTwo(List<String> instructions) {
        logger.info("Puzzle 2");

        Status status = new Status();

        final String regex = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)";
        instructions.stream().forEach(t -> {
            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(t);

            while (matcher.find()) {

                if (matcher.group().equals("do()")) {
                    status.enable = true;
                } else if (matcher.group().equals("don't()")) {
                    status.enable = false;
                } else {
                    if (status.enable) {
                        status.sum += Long.valueOf(matcher.group(1)) * Long.valueOf(matcher.group(2));
                    }
                }
            }
        });

        logger.info(status.sum);
    }

    private class Status {
        boolean enable = true;
        long sum = 0;
    }

}
