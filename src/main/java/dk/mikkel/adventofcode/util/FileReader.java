package dk.mikkel.adventofcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileReader {

    private static Logger logger = LogManager.getLogger(FileReader.class);

    private FileReader() {
    }

    public static String readResourceFile(String filePath) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (InputStream inputStream = FileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            logger.info("Error reading file: {}", filePath);
            throw new FileReaderException(e);
        }
        return resultStringBuilder.toString().trim();
    }

    public static List<String> readFileToList(int year, int day){
        return readFileToList(year, day, false);
    }

    public static List<String> readFileToList(int year, int day, boolean sample){
        StringBuilder fileurl = new StringBuilder();
        fileurl.append("year").append(year).append("/day_").append(String.format("%02d", day)).append(sample ? "_sample" : "").append(".input");

        return readFileToList(fileurl.toString());
    }

    public static List<String> readFileToList(String filename) {
        logger.info("Reading file: {}", filename);
        try {
            File file = Paths.get(ClassLoader.getSystemResource(filename).toURI()).toFile();
            return Files.readAllLines(file.toPath());
        } catch (URISyntaxException | IOException e) {
            logger.catching(e);
        }
        return List.of();
    }

    public static class FileReaderException extends RuntimeException {

        public FileReaderException(Throwable cause) {
            super(cause);
        }
    }
}
