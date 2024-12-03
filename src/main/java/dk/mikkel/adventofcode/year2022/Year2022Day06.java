package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Year2022Day06 {

    private static Logger logger = LogManager.getLogger(Year2022Day06.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/year2022/day_six.txt"));

        lines.forEach(t -> calcLine(t, 4));
        lines.forEach(t -> calcLine(t, 14));
    }

    private static void calcLine(String s, Integer unique) {
        LinkedList<Integer> startMaker = new LinkedList<>();
        char[] tmp = s.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            if (startMaker.stream().distinct().count() == unique) {
                logger.info(i);
                break;
            }
            if (startMaker.size() >= unique) {
                startMaker.removeFirst();
            }
            startMaker.add((int) tmp[i]);
        }
    }

}
