package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Util;

public class Day10 {

    private static final String PATH = "src/main/resources/year2022/day_10.txt";
    // private static final String PATH = "src/main/resources/year2022/day_10_sample.txt";
    private static Logger logger = LogManager.getLogger(Day10.class);



    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Util.getDataAsString(PATH);
        final String NOOP = "noop";

        Integer cycle = 0;
        int signalStrength = 1;
        int sum = 0;
        List<Integer> controlPoints  = List.of(20, 60, 100, 140, 180, 220);

        for (String line : lines) {
            if(NOOP.equals(line)){
                if(controlPoints.contains(++cycle)){
                    sum += cycle * signalStrength;
                }
            }else{
                String[] split = line.split(" ");
                if(controlPoints.contains(++cycle)){
                    sum += cycle * signalStrength;
                }
                if(controlPoints.contains(++cycle)){
                    sum += cycle * signalStrength;
                }
                signalStrength += Integer.parseInt(split[1]);
            }
            logger.info(cycle);
            logger.info(signalStrength);

            System.out.println("");
        }
        logger.info(sum);
    }



}
