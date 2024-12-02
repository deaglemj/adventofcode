package dk.mikkel.adventofcode.year2020;

import java.util.List;

import dk.mikkel.adventofcode.util.FileReader;

public class Day18 {

    public static void main(String[] args) {
        new Day18().runner();
    }

    public void runner() {

        List<String> instructions = FileReader.readFileToList("day_18.txt");
        puzzleOne(instructions);
        // puzzleTwo(instructions);
    }

    private void puzzleOne(List<String> expressions) {
        long sum = 0;
        for (String s : expressions) {
            sum += calculator(s);
        }
        System.out.println(sum);
    }

    private void puzzleTwo(List<String> expressions) {

    }

    private long calculator(String exp) {
        if (exp.contains("(")) {
            int indexBegin = exp.indexOf("(");
            int indexEnd = findEnd(exp);
            String subExp = exp.substring(indexBegin + 1, indexEnd);
            exp = exp.replace("(" + subExp + ")", "" + calculator(subExp));
            return calculator(exp);
        } else {
            return simpleExpHandler(exp);
        }
    }

    private int findEnd(String exp) {
        char[] characters = exp.toCharArray();
        int counter = 0;
        for (int i = exp.indexOf("("); i < characters.length; i++) {
            if ('(' == characters[i]) {
                counter++;
            }
            if (')' == characters[i]) {
                counter--;
            }
            if (counter == 0) {
                return i;
            }
        }
        return characters.length - 1;
    }

    private long simpleExpHandler(String exp) {
        String[] array = exp.split(" ");
        long res = 0;
        String operant = null;
        for (String s : array) {
            long tmp = 0;
            if (s.matches("[+*]")) {
                operant = s;
            } else {
                tmp = Integer.parseInt(s);
            }
            if (tmp != 0) {
                if ("+".equals(operant)) {
                    res += tmp;
                } else if ("*".equals(operant)) {
                    res *= tmp;
                } else {
                    res = tmp;
                }
            }
        }
        return res;
    }
}
