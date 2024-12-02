package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayThree {

    private static Logger logger = LogManager.getLogger(DayThree.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_three.txt");

        List<String> data = Files.readAllLines(path);
        Integer sumPartOne = data.stream().map(DayThree::prepareContentPartOne).map(DayThree::findbadge)
                .map(DayThree::convertCharToIntChar).reduce(0, Integer::sum);

        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());
        Integer sumPartTwo = data.stream()
                .reduce(initial, DayThree::prepareContentPartTwo, (list1, list2) -> new ArrayList<>()).stream()
                .map(DayThree::findbadge)
                .map(DayThree::convertCharToIntChar).reduce(0, Integer::sum);

        logger.info(sumPartOne);
        logger.info(sumPartTwo);

    }

    private static List<List<String>> prepareContentPartTwo(List<List<String>> subtotal, String element) {
        List<String> list = subtotal.get(subtotal.size() - 1);
        if (list.size() == 3) {
            list = new ArrayList<>();
            subtotal.add(list);
        }
        list.add(element);
        return subtotal;
    }

    private static Integer findbadge(List<String> c) {
        c.sort((arg0, arg1) -> arg0.length() - arg1.length());
        char[] chars = c.get(0).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean found = true;
            for (int j = 1; j < c.size(); j++) {
                found &= c.get(j).contains(String.valueOf(chars[i]));
            }
            if (found) {
                return (int) chars[i];
            }
        }
        return 0;
    }

    private static Integer convertCharToIntChar(Integer c) {
        return c >= 65 && c <= 90 ? c - 38 : c - 96;
    }

    private static List<String> prepareContentPartOne(String rucksack) {
        List<String> content = new ArrayList<>();
        content.add(rucksack.substring(0, rucksack.length() / 2));
        content.add(rucksack.substring(rucksack.length() / 2));
        return content;
    }
}
