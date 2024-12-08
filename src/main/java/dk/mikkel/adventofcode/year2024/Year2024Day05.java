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

        List<String> input = FileReader.readFileToList(2024, 5);

        List<Pair<String, String>> rules = new ArrayList<>();
        List<String[]> updates = new ArrayList<>();

        for (String line : input) {
            if (line.contains("|")){
                String[] parts = line.split("\\|");
                rules.add(new Pair<>(parts[0], parts[1]));
            }else if (line.contains(",")){
                updates.add(line.split(","));
            }
        }

        List<String[]> invalidUpdates = puzzleOne(rules, updates);
        puzzleTwo(rules, invalidUpdates);
    }

    public List<String[]> puzzleOne(List<Pair<String, String>> rules, List<String[]> updates) {
        logger.info("Puzzle 1");

        List<String[]> invalidUpdates = new ArrayList<>();

        int sum = 0;
        for (String[] update : updates) {
            boolean isRuleBroken = false;
            for (Pair<String, String> rule : rules) {
                int a = -1;
                int b = -1;
                for (int i = 0; i < update.length; i++) {
                    if (update[i].equals(rule.a)) {
                        a = i;
                    }
                    if (update[i].equals(rule.b)) {
                        b = i;
                    }
                }
                if (a != -1 && b != -1 && a > b) {
                    isRuleBroken |= true;
                    invalidUpdates.add(update);
                    break;
                }

                
            }
            if (!isRuleBroken) {
                sum += Integer.valueOf(update[update.length / 2]);
            }
        }

        logger.info("Sum: " + sum);
        return invalidUpdates;

    }

    public void puzzleTwo(List<Pair<String, String>> rules, List<String[]> updates) {
        logger.info("Puzzle 2");

        logger.info("Updates: " + updates.size());

        int i = 0;
        int sum = 0;
        while(i < updates.size()){
            String[] update = updates.get(i);
            boolean isRuleBroken = false;
            
            for (Pair<String, String> rule : rules) {
                int a = -1;
                int b = -1;
                for (int j = 0; j < update.length; j++) {
                    if (update[j].equals(rule.a)) {
                        a = j;
                    }
                    if (update[j].equals(rule.b)) {
                        b = j;
                    }
                }
                // A and B swap places
                if (a != -1 && b != -1 && a > b) {
                    String temp = update[a];
                    update[a] = update[b];
                    update[b] = temp;
                    isRuleBroken = true;
                }
            }

            if (!isRuleBroken) {
                i++;
                sum += Integer.valueOf(update[update.length / 2]);
            }



        }
        logger.info(sum);
    }









}
