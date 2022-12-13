package dk.mikkel.adventofcode.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Util {

    public static List<String> getDataAsString(String path) throws IOException{
        return Files.readAllLines(Paths.get(path));
    }

}
