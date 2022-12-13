package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

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
        int sumPartOne = 0;
        Stack<String> partTwo = new Stack<>();
        partTwo.add("");
        for (String line : lines) {
            if(NOOP.equals(line)){
                sumPartOne = cycleControl(++cycle, signalStrength, sumPartOne, partTwo);
            }else{
                String[] split = line.split(" ");
                sumPartOne = cycleControl(++cycle, signalStrength, sumPartOne, partTwo);
                sumPartOne = cycleControl(++cycle, signalStrength, sumPartOne, partTwo);
                signalStrength += Integer.parseInt(split[1]);
            }
        }
        logger.info(sumPartOne);
        partTwo.forEach(logger::info);
    }



    private static int cycleControl(Integer cycle, int signalStrength, int sumPartOne, Stack<String> partTwo) {
        if(List.of(20, 60, 100, 140, 180, 220).contains(cycle)){
            sumPartOne += cycle * signalStrength;
        }
        String pop = partTwo.pop();
        pop += extracted(cycle%40, signalStrength) ? "#" : ".";
        partTwo.add(pop);
        if(cycle%40==0){
            partTwo.add("");
        }
        return sumPartOne;
    }



    private static boolean extracted(Integer cycle, int signalStrength) {
        boolean check_one = cycle -1 == signalStrength + 1;
        boolean check_two = cycle   == signalStrength + 1;
        boolean check_three = cycle + 1 == signalStrength + 1;
        return check_one || check_two  || check_three ;
    }



}
