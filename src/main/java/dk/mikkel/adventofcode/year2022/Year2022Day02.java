package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Year2022Day02 {

    private static Logger logger = LogManager.getLogger(Year2022Day02.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_two.txt");

        List<String> data = Files.readAllLines(path);
        Integer resultPartOne = calculate(data, game -> calcPartOne(game[0], game[1]));
        Integer resultPartTwo = calculate(data, game -> calcPartTwo(game[0], game[1]));

        logger.info(resultPartOne);
        logger.info(resultPartTwo);
    }

    private static Integer calculate(List<String> data, Function<Integer[], Integer> calcFuntion) {
        return data.stream().map(Year2022Day02::makeRound).map(calcFuntion).reduce(0, Integer::sum);
    }

    private static Integer[] makeRound(String s) {
        String[] split = s.split(" ");
        return new Integer[] { split[0].charAt(0) - 64, split[1].charAt(0) - 87 };
    }

    private static Integer calcPartOne(int elf, int player) {
        if (elf == player) {
            return player + 3;
        } else if (elf + 1 == player || elf == 3 && player == 1) {
            return player + 6;
        } else {
            return player;
        }
    }

    private static Integer calcPartTwo(int elf, int player) {
        if (player == 1) {
            return elf == 1 ? 3 : elf - 1;
        } else if (player == 2) {
            return 3 + elf;
        } else {
            return 6 + (elf == 3 ? 1 : elf + 1);
        }
    }
}
