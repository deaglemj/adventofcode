package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayOne {

    private static Logger logger = LogManager.getLogger(DayOne.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_one.txt");

        List<String> data = Files.readAllLines(path);
        final List<Integer> totalCalories = new ArrayList<>();

        int currentCalories = 0;
        for (final String row : data) {
            if (row == null || row.isEmpty()) {
                totalCalories.add(currentCalories);
                currentCalories = 0;
                continue;
            }
            currentCalories += Integer.parseInt(row);
        }
        Collections.sort(totalCalories, Comparator.reverseOrder());
        logger.info(totalCalories.get(0));
        logger.info(totalCalories.stream().limit(3).reduce(0, Integer::sum));
    }
}
