package dk.mikkel.adventofcode.year2024;

import java.util.List;
import java.util.stream.Collectors;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2024Day02 {

    public static void main(String[] args) {
        new Year2024Day02().runner();
    }

    private void runner() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        System.out.println("Puzzle 1");

        List<String> lines = FileReader.readFileToList("year2024/day_2.txt");

        int count = 0;
        for (String s : lines) {
            if (isSafe(s, 0)){
                count++;
            }            
        }
        System.out.println(count);

    }

    private boolean isSafe(String s, int tolerate) {
        String[] split = s.split("\s+");

        int last = 0;
        for (int i = 0; i < split.length-1; i++) {
            int a = Integer.parseInt(split[i]);
            int b = Integer.parseInt(split[i+1]);
            int current = a - b;
            if (current == 0 || last > 0 && current < 0 || last < 0 && current > 0 || Math.abs(current) > 3) {
                return false;
            }

            last = current;
        }


        return true;
    }

    private void puzzleTwo() {
        System.out.println("Puzzle 2");

        List<String> lines = FileReader.readFileToList("year2024/day_2_sample.txt");

        int count = 0;
        for (String s : lines) {
            if (isSafe(s, 1)){
                count++;
            }            
        }
        System.out.println(count);
    }
    
}
