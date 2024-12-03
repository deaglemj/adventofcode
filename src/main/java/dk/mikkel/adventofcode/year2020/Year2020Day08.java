package dk.mikkel.adventofcode.year2020;

import java.util.ArrayList;
import java.util.List;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day08 {
    public static void main(String[] args) {
        new Year2020Day08().runner();
    }

    public void runner() {

        List<String> instructions = FileReader.readFileToList("day_8.txt");
        puzzleOne(instructions);
        puzzleTwo(instructions);
    }

    private void puzzleOne(List<String> instructions) {
        List<Integer> executed = new ArrayList<>();

        int index = 0;
        int result = 0;

        while (true) {
            if (executed.contains(index)) {
                break;
            }
            String[] instruction = instructions.get(index).split(" ");
            executed.add(index);
            if (instruction[0].equals("acc")) {
                result += Integer.parseInt(instruction[1]);
                index++;
            } else if (instruction[0].equals("jmp")) {
                index += Integer.parseInt(instruction[1]);
            } else {
                index++;
            }
        }

        System.out.println(result);
    }

    private void puzzleTwo(List<String> instructions) {
        List<Integer> executed = new ArrayList<>();

        int index = 0;
        int result = 0;

        List<Integer> flipped = new ArrayList<>();
        boolean hasFlipped = false;

        while (true) {
            if (index >= instructions.size()) {
                break;
            }
            if (executed.contains(index)) {
                index = 0;
                result = 0;
                hasFlipped = false;
                executed.clear();
            }
            String[] instruction = instructions.get(index).split(" ");
            executed.add(index);
            if ((instruction[0].equals("jmp") || instruction[0].equals("nop")) && !flipped.contains(index)
                    && !hasFlipped) {
                if (instruction[0].equals("jmp")) {
                    instruction[0] = "nop";
                } else if (instruction[0].equals("nop")) {
                    instruction[0] = "jmp";

                }
                flipped.add(index);
                hasFlipped = true;
            }
            if (instruction[0].equals("acc")) {
                result += Integer.parseInt(instruction[1]);
                index++;
            } else if (instruction[0].equals("jmp")) {
                index += Integer.parseInt(instruction[1]);
            } else {
                index++;
            }
        }

        System.out.println(result);
    }
}
