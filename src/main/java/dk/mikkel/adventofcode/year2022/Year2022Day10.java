package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Util;

public class Year2022Day10 {

    private static final String PATH = "src/main/resources/year2022/day_10.txt";
    private static Logger logger = LogManager.getLogger(Year2022Day10.class);

    public static void main(String[] args) throws IOException {
        List<String> lines = Util.getDataAsString(PATH);

        Integer cycle = 0;
        int signalStrength = 1;
        int resultPartOne = 0;
        Deque<String> resultPartTwo = new ArrayDeque<>(List.of(""));
        for (String line : lines) {
            resultPartOne = cycleControl(++cycle, signalStrength, resultPartOne, resultPartTwo);
            if (!"noop".equals(line)) {
                resultPartOne = cycleControl(++cycle, signalStrength, resultPartOne, resultPartTwo);
                signalStrength += Integer.parseInt(line.split(" ")[1]);
            }
        }

        logger.info(resultPartOne);
        resultPartTwo.forEach(logger::info);
    }

    private static int cycleControl(Integer cycle, int signalStrength, int sumPartOne, Deque<String> partTwo) {
        if (List.of(20, 60, 100, 140, 180, 220).contains(cycle)) {
            sumPartOne += cycle * signalStrength;
        }
        String pop = partTwo.pop();
        pop += checkCycleAndSignalStrength((cycle % 40) + 1, signalStrength);
        partTwo.add(pop);
        if (cycle % 40 == 0) {
            partTwo.add("");
        }
        return sumPartOne;
    }

    private static String checkCycleAndSignalStrength(Integer cycle, int signalStrength) {
        for (int i = 1; i <= 3; i++) {
            if (cycle == signalStrength + i) {
                return "#";
            }
        }
        return ".";
    }
}
