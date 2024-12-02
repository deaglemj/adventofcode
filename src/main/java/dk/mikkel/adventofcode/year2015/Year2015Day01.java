package dk.mikkel.adventofcode.year2015;

import dk.mikkel.adventofcode.util.Base;

public class Year2015Day1 extends Base<Integer, Integer> {

    @Override
    public Integer solvePart1(String input) {
        int level = 0;
        for (int i = 0; i < input.length(); i++) {
            level += adjustLevel(input.charAt(i));
        }
        return level;
    }

    @Override
    public Integer solvePart2(String input) {
        int currentLevel = 0;
        int position = 0;
        for (int i = position; i < input.length(); i++) {
            currentLevel += adjustLevel(input.charAt(i));
            if (currentLevel == -1) {
                position = i + 1;
                break;
            }
        }
        return position;
    }

    private int adjustLevel(char direction) {
        return direction == '(' ? 1 : -1;
    }
}
