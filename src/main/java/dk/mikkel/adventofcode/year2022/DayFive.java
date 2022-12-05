package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayFive {

    private static Logger logger = LogManager.getLogger(DayFive.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_five.txt");

        List<String> lines = Files.readAllLines(path);
        List<String> initState = new ArrayList<>();
        List<String> data = new ArrayList<>();
        int i;
        int size = lines.size();
        for (i = 0; i < size; i++) {
            if (lines.get(i).isEmpty()) {
                initState = lines.subList(0, i - 1);
                data = lines.subList(i + 1, lines.size());
            }
        }
        Collections.reverse(initState);
        final Map<Integer, Stack<String>> partOne = getInitMap(initState);
        final Map<Integer, Stack<String>> partTwo = getInitMap(initState);

        calcPartOne(data, partOne);
        calcPartTwo(data, partTwo);

        String answerPartOne = partOne.entrySet().stream().map(es -> es.getValue().pop()).collect(Collectors.joining(""));
        logger.info(answerPartOne);

        String answerPartTwo = partTwo.entrySet().stream().map(es -> es.getValue().pop()).collect(Collectors.joining(""));
        logger.info(answerPartTwo);
    }


    private static void calcPartOne(List<String> data, final Map<Integer, Stack<String>> partOne) {
        data.forEach(c -> {

            String[] split = c.split(" ");
            int moves = Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);

            for (int j = 0; j < moves; j++) {
                String pop = partOne.get(from).pop();
                partOne.get(to).add(pop);
            }
        });
    }

    
    private static void calcPartTwo(List<String> data, final Map<Integer, Stack<String>> partTwo) {
        data.forEach(c -> {
            String[] split = c.split(" ");
            int moves = Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);

            String[] temp = new String[moves];

            for (int j = 0; j < moves; j++) {
                Stack<String> stack = partTwo.get(from);
                String pop = stack.pop();
                temp[moves - j - 1] = pop;
            }
            partTwo.get(to).addAll(List.of(temp));
        });
    }

    private static Map<Integer, Stack<String>> getInitMap(List<String> initState) {
        final Map<Integer, Stack<String>> map = new HashMap<>();

        initState.stream().forEach(
                s -> {
                    int index = 1;
                    do {
                        char c = s.charAt(index);
                        if (c != 32) {
                            Integer column = (index / 4) + 1;
                            map.putIfAbsent(column, new Stack<>());
                            map.get(column).add(String.valueOf(c));
                        }
                        index += 4;
                    } while (index < s.length());
                });
        return map;
    }

}
