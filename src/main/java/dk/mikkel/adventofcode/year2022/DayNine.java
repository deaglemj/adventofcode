package dk.mikkel.adventofcode.year2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.Util;

public class DayNine {

    private static final String PATH = "src/main/resources/year2022/day_nine.txt";
    // private static final String PATH = "src/main/resources/year2022/day_nine_sample.txt";
    // private static final String PATH = "src/main/resources/year2022/day_nine_sample_2.txt";
    private static Logger logger = LogManager.getLogger(DayNine.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<String> dataMap = Util.getDataAsString(PATH);

        calculate(dataMap, 1);
        calculate(dataMap, 9);
    }

    private static void calculate(List<String> dataMap, int knotAmount) {
        int startPosition = 0;
        Position head = new Position(startPosition, startPosition);
        List<Position> visited = new ArrayList<>();
        LinkedList<Position> knots = new LinkedList<>();
        for (int i = 0; i < knotAmount; i++) {
            knots.add(new Position(head));
        }
        visited.add(new Position(head));

        dataMap.forEach(instruction -> {
            move(instruction, head, knots, visited);
        });

        logger.info(visited.stream().distinct().count());
    }

    private static void move(String instruction, Position head, LinkedList<Position> knots, List<Position> visited) {
        String[] split = instruction.split(" ");
        moveRope(split[0], Integer.valueOf(split[1]), head, knots, visited);
    }

    private static void moveRope(String direction, Integer amount, Position head,
            LinkedList<Position> knots, List<Position> visited) {
        for (int i = 0; i < amount; i++) {
            Position tmp = moveHead(head, direction);
            for (Position knot : knots) {
                tmp = followHead(knot, tmp);
            }
            visited.add(new Position(tmp));
        }
    }

    private static Position followHead(Position knot, Position tmp) {
        int x = knot.x - tmp.x;
        int y = knot.y - tmp.y;

        if (x < -1 || x > 1) {
            if (x < -1) {
                knot.x++;
            } else if (x > 1) {
                knot.x--;
            }
            if (Math.abs(y) == 1) {
                knot.y = tmp.y;
            }
        }

        if (y < -1 || y > 1) {
            if (y < -1) {
                knot.y++;
            } else if (y > 1) {
                knot.y--;
            }
            if (Math.abs(x) == 1) {
                knot.x = tmp.x;
            }
        }
        return knot;
    }

    private static Position moveHead(Position head, String direction) {
        switch (direction) {
            case "U":
                head.y++;
                break;
            case "D":
                head.y--;
                break;
            case "R":
                head.x++;
                break;
            case "L":
                head.x--;
                break;
        }
        return head;
    }

    static class Position {
        int x;
        int y;

        public Position(Position p) {
            this.x = p.x;
            this.y = p.y;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "Position [x=" + x + ", y=" + y + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Position other = (Position) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

    }
}
