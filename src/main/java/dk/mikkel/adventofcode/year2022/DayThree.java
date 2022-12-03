package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayThree {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_three.txt");
        // Path path = Paths.get("src/main/resources/year2022/day_three_sample.txt");

        List<String> data = Files.readAllLines(path);
        Integer sumPartOne = data.stream().map(DayThree::getContentPartOne).map(DayThree::findbadgePartOne)
                .map(DayThree::convertCharToIntChar).reduce(0, Integer::sum);
        System.out.println(sumPartOne);

        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());
        Integer sumPartTwo = data.stream().reduce(initial, (subtotal, element) -> {
            List<String> list = subtotal.get(subtotal.size() - 1);
            if (list.size() == 3) {
                list = new ArrayList<>();
                subtotal.add(list);
            }
            list.add(element);
            return subtotal;

        }, (list1, list2) -> new ArrayList<>()).stream().map(DayThree::findbadgePartTwo)
                .map(DayThree::convertCharToIntChar).reduce(0, Integer::sum);

        System.out.println(sumPartTwo);

    }

    private static Integer findbadgePartOne(String[] c) {
        char[] charArray = c[0].toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (c[1].contains(String.valueOf(charArray[i]))) {
                return (int) charArray[i];
            }
        }
        return 0;
    }

    private static Integer findbadgePartTwo(List<String> c) {
        c.sort((arg0, arg1) -> arg0.length() - arg1.length());
        char[] chars = c.get(0).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (c.get(1).contains(String.valueOf(chars[i])) && c.get(2).contains(String.valueOf(chars[i]))) {
                return (int) chars[i];
            }
        }
        return 0;
    }

    private static Integer convertCharToIntChar(Integer c) {
        if (c >= 65 && c <= 90) {
            return c - 38;
        } else {
            return c - 96;
        }
    }

    private static String[] getContentPartOne(String rucksack) {
        String[] content = { rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2) };
        return content;
    }
}
