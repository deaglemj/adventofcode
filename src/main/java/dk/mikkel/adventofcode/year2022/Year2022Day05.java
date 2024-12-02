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

public class Year2022Day05 {

    private static Logger logger = LogManager.getLogger(Year2022Day05.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_five.txt");

        List<String> lines = Files.readAllLines(path);
        List<String> initState = new ArrayList<>();
        List<String> data = splitInput(lines, initState);

        final Map<Integer, Stack<String>> partOne = getInitMap(initState);
        final Map<Integer, Stack<String>> partTwo = getInitMap(initState);

        calcPartOne(data, partOne);
        calcPartTwo(data, partTwo);

        logger.info(partOne.entrySet().stream().map(es -> es.getValue().pop()).collect(Collectors.joining("")));
        logger.info(partTwo.entrySet().stream().map(es -> es.getValue().pop()).collect(Collectors.joining("")));
    }

    private static List<String> splitInput(List<String> lines, List<String> initState) {
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            if (lines.get(i).isEmpty()) {
                initState = lines.subList(0, i - 1);
                Collections.reverse(initState);
                return lines.subList(i + 1, lines.size());
            }
        }
        return Collections.emptyList();
    }

    private static void calcPartOne(List<String> data, final Map<Integer, Stack<String>> partOne) {
        data.forEach(c -> {
            String[] split = c.split(" ");
            int moves = Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);

            for (int j = 0; j < moves; j++) {
                partOne.get(to).add(partOne.get(from).pop());
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
                temp[moves - j - 1] = partTwo.get(from).pop();
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
