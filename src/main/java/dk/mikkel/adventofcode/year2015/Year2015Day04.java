package dk.mikkel.adventofcode.year2015;

import dk.mikkel.adventofcode.util.Base;
import dk.mikkel.adventofcode.util.Encrypt;

public class Year2015Day4 extends Base<Integer, Integer> {

    @Override
    public Integer solvePart1(String input) {
        String prefix = "00000";
        return searchForMatchingPrefix(input, prefix);
    }

    @Override
    public Integer solvePart2(String input) {
        String prefix = "000000";
        return searchForMatchingPrefix(input, prefix);
    }

    private int searchForMatchingPrefix(String input, String prefix) {
        int counter = 0;
        while (true) {
            String s = input + counter;
            String result = Encrypt.stringToMD5Hex(s);
            if (result.startsWith(prefix)) {
                break;
            }
            counter++;
        }
        return counter;
    }

}