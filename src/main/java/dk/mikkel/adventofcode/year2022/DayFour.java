package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayFour {

    private static Logger logger = LogManager.getLogger(DayFour.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_four.txt");

        List<String> data = Files.readAllLines(path);
        Integer sumPartOne = data.stream().map(DayFour::splitPair).map(DayFour::calcPartOne).reduce(0, Integer::sum);
        Integer sumPartTwo = data.stream().map(DayFour::splitPair).map(DayFour::calcPartTwo).reduce(0, Integer::sum);

        logger.info(sumPartOne);
        logger.info(sumPartTwo);
    }

    private static Integer calcPartOne(Pair[] pairs) {
        return pairs[0].min >= pairs[1].min && pairs[0].max <= pairs[1].max ||
                pairs[0].min <= pairs[1].min && pairs[0].max >= pairs[1].max ? 1 : 0;
    }

    private static Integer calcPartTwo(Pair[] pairs) {
        return pairs[0].max >= pairs[1].min && pairs[0].max <= pairs[1].max ||
                pairs[1].max >= pairs[0].min && pairs[1].max <= pairs[0].max ? 1 : 0;
    }

    private static Pair[] splitPair(String s) {
        String[] parts = s.split(",");
        return new Pair[] { Pair.from(parts[0]), Pair.from(parts[1]) };
    }

    static class Pair {
        Integer min, max;

        public static Pair from(String part) {
            Pair p = new Pair();
            String[] split_part = part.split("-");
            p.min = Integer.parseInt(split_part[0]);
            p.max = Integer.parseInt(split_part[1]);
            return p;
        }
    }

}
