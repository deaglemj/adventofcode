package dk.mikkel.adventofcode.year2020;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day02 {

    private static Logger logger = LogManager.getLogger(Year2020Day02.class);

    public static void main(String[] args) {
        new Year2020Day02().runner();
    }

    public void runner() {
        List<PasswordInfo> puzzleOne = FileReader.readFileToList("year2020/day_02.input").stream()
                .flatMap(s -> Stream.of(new PasswordInfo(s))).filter(PasswordInfo::puzzleOne)
                .collect(Collectors.toList());
        List<PasswordInfo> puzzleTwo = FileReader.readFileToList("year2020/day_02.input").stream()
                .flatMap(s -> Stream.of(new PasswordInfo(s))).filter(PasswordInfo::puzzleTwo)
                .collect(Collectors.toList());

        logger.info("Day 2: Password Philosophy : Puzzle 1 : {}", puzzleOne.size());
        logger.info("Day 2: Password Philosophy : Puzzle 2 : {}", puzzleTwo.size());
    }

    class PasswordInfo {

        int min;
        int max;
        char required;
        String password;

        public PasswordInfo(String info) {
            processInfo(info);
        }

        private void processInfo(String info) {
            String[] parts = info.split("-|: | ");
            this.min = Integer.parseInt(parts[0]);
            this.max = Integer.parseInt(parts[1]);
            this.required = parts[2].charAt(0);
            this.password = parts[3];
        }

        public boolean puzzleOne() {
            int charCount = 0;
            for (char c : password.toCharArray()) {
                if (c == required && ++charCount > max) {
                    return false;
                }
            }
            return charCount >= min;
        }

        public boolean puzzleTwo() {
            return password.charAt(min - 1) == required ^ password.charAt(max - 1) == required;
        }
    }
}
