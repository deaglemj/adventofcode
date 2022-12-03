package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Integer sum = data.stream().map(DayThree::getContent).map(c -> {
            char[] charArray = c[0].toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if(c[1].contains(String.valueOf(charArray[i]))){
                    return (int)charArray[i];
                }
            }
            return 0;
        }).map( c -> {
            System.out.println(c);
            if(c >= 65 && c <= 90){
                return c - 38;
            }else{
                return c - 96;

            }
        }).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static String[] getContent(String rucksack) {
        String[] content = { rucksack.substring(0, rucksack.length() / 2), rucksack.substring(rucksack.length() / 2) };
        System.out.println(content[0]);
        System.out.println(content[1]);
        return content;
    }
}
