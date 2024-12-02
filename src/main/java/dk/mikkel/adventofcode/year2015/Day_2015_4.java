package advent_of_code;

import util.Base;

public class Day_2015_4 extends Base<Integer, Integer> {

    @Override
    public Integer solve_part_1(String input) {
        String prefix = "00000";
        return searchForMatchingPrefix(input, prefix);
    }



    @Override
    public Integer solve_part_2(String input) {
        String prefix = "000000";
        return searchForMatchingPrefix(input, prefix);
    }

    private int searchForMatchingPrefix(String input, String prefix) {
        int counter = 0;
        while (true) {
            String s = input + counter;
            String result = util.Encrypt.stringToMD5Hex(s);
            if (result.startsWith(prefix)) {
                break;
            }
            counter++;
        }
        return counter;
    }

}