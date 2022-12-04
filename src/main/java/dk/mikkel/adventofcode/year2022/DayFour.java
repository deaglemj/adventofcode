package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFour {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/year2022/day_four.txt");
        // Path path = Paths.get("src/main/resources/year2022/day_four_sample.txt");

        List<String> data = Files.readAllLines(path);
        Integer sumPartOne = data.stream().map( s -> s.split(",")).map( arg -> {
            String[] pair_one = arg[0].split("-");
            String[] pair_two = arg[1].split("-");

            if(Integer.valueOf(pair_one[0]) >= Integer.valueOf(pair_two[0]) && Integer.valueOf(pair_one[1]) <= Integer.valueOf(pair_two[1]) ||
            Integer.valueOf(pair_one[0]) <= Integer.valueOf(pair_two[0]) && Integer.valueOf(pair_one[1]) >= Integer.valueOf(pair_two[1])){
                return 1;
            }
            return 0;
        }).reduce(0, Integer::sum);

        System.out.println(sumPartOne);
    }

} 
