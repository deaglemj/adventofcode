package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Year2022Day07 {

    private static final int LIMIT = 100000;
    private static final int FILESYSTEMSIZE = 70000000;
    private static final int MINIMUMFREESIZE = 30000000;
    private static Logger logger = LogManager.getLogger(Year2022Day07.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/year2022/day_seven.txt"));

        List<Integer> sizes = new ArrayList<>();
        File currentDirectory = new File(new String[] { "dir", "/" });
        for (String line : lines) {
            String[] split = line.split(" ");
            if ("$".equals(split[0])) {
                if ("cd".equals(split[1])) {
                    if (currentDirectory.files.containsKey(split[2])) {
                        currentDirectory.files.get(split[2]).parent = currentDirectory;
                        currentDirectory = currentDirectory.files.get(split[2]);
                    } else {
                        appendSize(sizes, currentDirectory);
                        currentDirectory = currentDirectory.parent;
                    }
                }
            } else {
                File file = new File(split);
                currentDirectory.files.put(file.name, file);
            }
        }
        while (currentDirectory.parent != null) {
            appendSize(sizes, currentDirectory);
            currentDirectory = currentDirectory.parent;
        }
        appendSize(sizes, currentDirectory);

        logger.info(sizes.stream().filter(i -> i < LIMIT).reduce(0, Integer::sum));

        Collections.reverse(sizes);
        int freeSpace = FILESYSTEMSIZE - currentDirectory.size;
        logger.info(sizes.stream().filter(i -> (freeSpace + i) > MINIMUMFREESIZE).sorted().limit(1).reduce(0,
                Integer::sum));
    }

    private static void appendSize(List<Integer> sizes, File currentDirectory) {
        currentDirectory.size = currentDirectory.getSize();
        sizes.add(currentDirectory.size);
    }

    private static class File {

        String name;
        int size = 0;
        Map<String, File> files = new HashMap<>();
        File parent = null;

        public File(String[] meta) {
            this.name = meta[1];
            if (!"dir".equals(meta[0])) {
                size = Integer.parseInt(meta[0]);
            }
        }

        public int getSize() {
            return files.entrySet().stream().map(es -> es.getValue().size).reduce(0, Integer::sum);
        }
    }
}