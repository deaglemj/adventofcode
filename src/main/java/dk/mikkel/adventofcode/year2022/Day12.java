package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Util;

public class Day12 {

    // private static final String PATH = "src/main/resources/year2022/day_12.txt";
    private static final String PATH = "src/main/resources/year2022/day_12_sample.txt";
    private static Logger logger = LogManager.getLogger(Day12.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Util.getDataAsString(PATH);

        lines.forEach(logger::info);
    }
}
