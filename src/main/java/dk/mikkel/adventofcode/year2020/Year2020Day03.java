package dk.mikkel.adventofcode.year2020;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day03 {

    private static Logger logger = LogManager.getLogger(Year2020Day03.class);

    private boolean printMap = false;
    private List<String> list = FileReader.readFileToList("year2020/day_03.input");

    public static void main(String[] args) {
        new Year2020Day03().runner();
    }

    public void runner() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        runMap(List.of(new int[] { 3, 1 }));
    }

    private void puzzleTwo() {
        runMap(List.of(new int[] { 1, 1 }, new int[] { 3, 1 }, new int[] { 5, 1 }, new int[] { 7, 1 },
                new int[] { 1, 2 }));
    }

    private void runMap(List<int[]> list) {
        Long result = 1L;
        for (int[] ints : list) {
            int i = calcResult(ints[0], ints[1]);
            result *= i;
        }
        logger.info(result);
    }

    private int calcResult(int right, int down) {
        Integer position = 0;
        int treeCount = 0;
        if (printMap) {
            String message = list.get(0);
            logger.info(message);
        }
        for (int i = down, listSize = list.size(); i < listSize; i += down) {
            String submap = list.get(i);
            StringBuilder map = new StringBuilder(submap);

            position += right;

            while (map.length() - 1 < position) {
                map.append(submap);
            }

            char isTree = 'O';
            if (map.charAt(position) == '#') {
                treeCount++;
                isTree = 'X';
            }
            if (printMap) {
                String message = map.substring(0, position) + isTree + map.substring(position + 1);
                logger.info(message);
            }
        }
        return treeCount;
    }
}
