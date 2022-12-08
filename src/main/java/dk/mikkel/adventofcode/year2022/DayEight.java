package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayEight {

    private static Logger logger = LogManager.getLogger(DayEight.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Path path = Paths.get("src/main/resources/year2022/day_eight.txt");
        List<String> dataMap = Files.readAllLines(Paths.get("src/main/resources/year2022/day_eight.txt"));
        Integer size = dataMap.size();
        int[][] heightMap = mapToArray(dataMap, size);
        int[][] visibleTreeMap = new int[size][size];
        int count = 0;
        int length = heightMap.length - 1;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                int currentTree = heightMap[i][j];
                
                for (int x = i - 1; x >= 0; x--) {
                    if(currentTree <= heightMap[x][j]){
                        visibleTreeMap[i][j]++;
                        break;
                    }
                }

                for (int x = i + 1; x < size; x++) {
                    if(currentTree <= heightMap[x][j]){
                        visibleTreeMap[i][j]++;
                        break;
                    }
                }
                
                for (int y = j - 1; y >= 0; y--) {
                    if(currentTree <= heightMap[i][y]){
                        visibleTreeMap[i][j]++;
                        break;
                    }
                }

                for (int y = j + 1; y < size; y++) {
                    if(currentTree <= heightMap[i][y]){
                        visibleTreeMap[i][j]++;
                        break;
                    }
                }
                if(visibleTreeMap[i][j] == 4){
                    count++;
                }
            }
        }
        logger.info((size*size) - count);
        

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
