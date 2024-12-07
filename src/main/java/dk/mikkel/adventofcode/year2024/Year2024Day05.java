package dk.mikkel.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;
import dk.mikkel.adventofcode.util.Pair;

public class Year2024Day05 {

    private static Logger logger = LogManager.getLogger(Year2024Day05.class);

    public static void main(String[] args) {
        new Year2024Day05().runner();
    }

    public void runner() {

        List<String> input = FileReader.readFileToList(2024, 5, true);

        List<Pair<String, String>> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        for (String line : input) {
            if (line.contains("|")){
                String[] parts = line.split("\\|");
                rules.add(new Pair<>(parts[0], parts[1]));
            }else if (line.contains(",")){
                updates.add(line);
            }
        }

        puzzleOne(rules, updates);
        puzzleTwo(rules, updates);
    }

    public void puzzleOne(List<Pair<String, String>> rules, List<String> updates) {
        logger.info("Puzzle 1");

    }

    public void puzzleTwo(List<Pair<String, String>> rules, List<String> updates) {
        logger.info("Puzzle 2");

        
    }







}
