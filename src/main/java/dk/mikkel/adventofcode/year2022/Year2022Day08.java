package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Year2022Day08 {

    private static Logger logger = LogManager.getLogger(Year2022Day09.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // List<String> dataMap =
        // Files.readAllLines(Paths.get("src/main/resources/year2022/day_nine_sample.txt"));
        List<String> dataMap = Files.readAllLines(Paths.get("src/main/resources/year2022/day_ninet.txt"));
        Integer size = dataMap.size();
        int[][] heightMap = mapToArray(dataMap, size);
        int[][] visibleTreeMap = new int[size][size];
        int scenicScore = 0;
        int count = 0;
        int length = heightMap.length - 1;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                int currentTree = heightMap[i][j];
                int up = 0;
                int down = 0;
                int left = 0;
                int right = 0;
                for (int x = i - 1; x >= 0; x--) {
                    int tree = heightMap[x][j];
                    up++;
                    if (currentTree <= tree) {
                        if ((visibleTreeMap[i][j] & 1) != 1) {
                            visibleTreeMap[i][j] |= 1;
                            break;
                        }
                    }
                }

                for (int x = i + 1; x < size; x++) {
                    int tree = heightMap[x][j];
                    down++;
                    if (currentTree <= tree) {
                        if ((visibleTreeMap[i][j] & 2) != 2) {
                            visibleTreeMap[i][j] |= 2;
                            break;
                        }
                    }

                }

                for (int y = j - 1; y >= 0; y--) {
                    int tree = heightMap[i][y];
                    left++;
                    if (currentTree <= tree) {
                        if ((visibleTreeMap[i][j] & 4) != 4) {
                            visibleTreeMap[i][j] |= 4;
                            break;
                        }
                    }
                }

                for (int y = j + 1; y < size; y++) {
                    int tree = heightMap[i][y];
                    right++;
                    if (currentTree <= tree) {
                        if ((visibleTreeMap[i][j] & 8) != 8) {
                            visibleTreeMap[i][j] |= 8;
                            break;
                        }
                    }
                }
                if (visibleTreeMap[i][j] == 15) {
                    count++;
                }
                int newScenicScore = up * left * down * right;
                if (scenicScore < newScenicScore) {
                    scenicScore = newScenicScore;
                }
            }
        }
        logger.info((size * size) - count);
        logger.info(scenicScore);
    }

    private static int[][] mapToArray(List<String> map, Integer size) {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = Integer.parseInt(map.get(i), j, j + 1, 10);
            }
        }
        return tmp;
    }

}
