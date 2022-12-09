package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.text.Position;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DayNine {

    private static Logger logger = LogManager.getLogger(DayNine.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // List<String> dataMap = Files.readAllLines(Paths.get("src/main/resources/year2022/day_nine_sample.txt"));
        List<String> dataMap = Files.readAllLines(Paths.get("src/main/resources/year2022/day_nine.txt"));

        int size = 1000;
        int startPosition = size / 2;
        // int startPosition = 0;
        int[][] visited = new int[size][size];
        Position head = new Position(startPosition);
        Position tail = new Position(startPosition);
        visited[tail.y][tail.x] = 1;

        dataMap.forEach(i -> {
            String[] split = i.split(" ");
            switch (Direction.valueOf(split[0])) {
                case U:
                    move(Integer.valueOf(split[1]), () -> {
                        head.y++;
                        if (Math.abs(tail.y - head.y) > 1) {
                            tail.y = head.y - 1;
                            tail.x = head.x;
                            visited[tail.y][tail.x] = 1;
                        }
                    });
                    break;
                case D:
                    move(Integer.valueOf(split[1]), () -> {
                        head.y--;
                        if (Math.abs(tail.y - head.y) > 1) {
                            tail.y = head.y + 1;
                            tail.x = head.x;
                            visited[tail.y][tail.x] = 1;
                        }
                    });
                    break;
                case L:
                    move(Integer.valueOf(split[1]), () -> {
                        head.x--;
                        if (Math.abs(tail.x - head.x) > 1) {
                            tail.y = head.y;
                            tail.x = head.x + 1;
                            visited[tail.y][tail.x] = 1;
                        }
                    });
                    break;
                case R:
                    move(Integer.valueOf(split[1]), () -> {
                        head.x++;
                        if (Math.abs(tail.x - head.x) > 1) {
                            tail.y = head.y;
                            tail.x = head.x - 1;
                            visited[tail.y][tail.x] = 1;
                        }
                    });
                    break;
            }
        });
        int count = 0;
        for (int index = 0; index < size; index++) {
            for(int j = 0; j < size; j++){
                count += visited[index][j];
            }
        }
        logger.info(count);
    }

    private static void move(int amount, Runnable move) {
        for (int i = 0; i < amount; i++) {
            move.run();
        }
    }
    
    enum Direction {
        U, D, L, R
    }

    static class Position implements Cloneable {
        int x;
        int y;

        public Position(int center) {
            this.x = center;
            this.y = center;
        }

        public String toString() {
            return "Position [x=" + x + ", y=" + y + "]";
        }
    }

}
