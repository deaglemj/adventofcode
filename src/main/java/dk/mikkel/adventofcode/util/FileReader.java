package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

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
            System.out.println("Error reading file: " + filePath);
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString().trim();
    }

    public static List<String> readFileToList(String filename) {
        try {
            File file = Paths.get(ClassLoader.getSystemResource(filename).toURI()).toFile();
            return Files.readAllLines(file.toPath());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
