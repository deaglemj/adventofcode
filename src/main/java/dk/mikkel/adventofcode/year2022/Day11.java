package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Util;

public class Day11 {

    private static final String PATH = "src/main/resources/year2022/day_11.txt";
    // private static final String PATH = "src/main/resources/year2022/day_11_sample.txt";
    private static Logger logger = LogManager.getLogger(Day11.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Util.getDataAsString(PATH);
        
        runGames(lines, 20, 3);
        runGames(lines, 10000, 1);

    }

    private static void runGames(List<String> lines, int games, int w) {
        Map<Long, Monkey> monkeys = mapData(lines);
        Long reduce = monkeys.entrySet().stream().map(es -> es.getValue().testValue).reduce(1L, Math::multiplyExact);
        for (int i = 0; i < games; i++) {
            monkeys.entrySet().stream().map(Entry::getValue).forEach(m -> {
                for (Long item : m.items) {
                    m.itemsInspected++;
                    Long worryLevel = m.runOperation(item, arg0 -> w != 1 ? arg0 / 3 : arg0 % reduce);
                    Long receiver = m.runTest(worryLevel) ? m.receiverOnSuccess : m.receiverOnFailure;
                    monkeys.get(receiver).receiveItem(worryLevel);
                }
                m.items.clear();
            });
        }
        logger.info(monkeys.entrySet().stream().peek(e -> {
            logger.info(String.format("Monkey %d inspected items %d times.", e.getKey(), e.getValue().itemsInspected));
        }).map(arg0 -> arg0.getValue().itemsInspected).sorted((arg0, arg1) -> arg1.compareTo(arg0)).limit(2).reduce(1L,
                Math::multiplyExact));
    }

    private static Map<Long, Monkey> mapData(List<String> lines) {
        Map<Long, Monkey> monkeys = new HashMap<>();
        for (int i = 0; i < lines.size(); i += 7) {
            Long id = extractedInteger(lines.get(i));
            Monkey monkey = monkeys.getOrDefault(monkeys, new Monkey(id));
            monkey.setItems(lines.get(i + 1));
            monkey.setOperations(lines.get(i + 2));
            monkey.setTestValue(lines.get(i + 3));
            monkey.setTestSuccess(lines.get(i + 4));
            monkey.setTestFailed(lines.get(i + 5));
            monkeys.put(id, monkey);
        }
        return monkeys;
    }

    private static Long extractedInteger(String line) {
        final Matcher matcher = Pattern.compile("\\d+").matcher(line);
        return matcher.find() ? Long.parseLong(matcher.group(0)) : 0L;
    }

    private static String extractedOperation(String line) {
        final Matcher matcher = Pattern.compile("[\\+\\-\\*\\/]").matcher(line);
        return matcher.find() ? matcher.group(0) : "+";
    }

    private static List<Long> extractedItems(String line) {
        final Matcher matcher = Pattern.compile("(\\d+(, )?)+").matcher(line);
        if (matcher.find()) {
            String match = matcher.group(0);
            return List.of(match.split(", ")).stream().map(Long::valueOf).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    enum Operator {
        ADD(Math::addExact), SUBTRACT(Math::subtractExact), MULTIPLY(Math::multiplyExact), DIVIDE(Math::divideExact);

        final BiFunction<Long, Long, Long> operation;

        Operator(BiFunction<Long, Long, Long> operation) {
            this.operation = operation;
        }
    }

    static class Monkey {
        Long id;
        Deque<Long> items = new ArrayDeque<>();
        Long inputTwo;
        Operator operator;
        Long testValue;
        Long receiverOnSuccess; // if true throw to t
        Long receiverOnFailure; // if false throw to f
        Long itemsInspected = 0L;

        public Monkey(Long id) {
            this.id = id;
        }

        public void receiveItem(Long worryLevel) {
            items.add(worryLevel);
        }

        public void setItems(String line) {
            items.addAll(extractedItems(line));
        }

        public void setOperations(String line) {
            inputTwo = extractedInteger(line);
            switch (extractedOperation(line)) {
                case "+":
                    this.operator = Operator.ADD;
                    break;
                case "-":
                    this.operator = Operator.SUBTRACT;
                    break;
                case "*":
                    this.operator = Operator.MULTIPLY;
                    break;
                case "/":
                    this.operator = Operator.DIVIDE;
                    break;
            }
        }

        public void setTestValue(String line) {
            testValue = extractedInteger(line);
        }

        public void setTestSuccess(String line) {
            receiverOnSuccess = extractedInteger(line);
        }

        public void setTestFailed(String line) {
            receiverOnFailure = extractedInteger(line);
        }

        public Long runOperation(Long item, Function<Long, Long> handleWorryLevel)  {
            return handleWorryLevel.apply(operator.operation.apply(item, inputTwo == 0 ? item : inputTwo));
        }

        public boolean runTest(Long worryLevel) {
            return worryLevel % testValue == 0;
        }
    }
}
