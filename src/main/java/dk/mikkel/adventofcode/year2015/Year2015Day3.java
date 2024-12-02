package dk.mikkel.adventofcode.year2015;

import java.util.HashSet;
import java.util.Set;

import dk.mikkel.adventofcode.util.Base;

public class Year2015Day3 extends Base<Integer, Integer> {

    @Override
    public Integer solvePart1(String input) {
        Vector santa = Vector.of(0, 0);
        Set<Vector> visited = new HashSet<>();
        visited.add(santa);
        for (int i = 0; i < input.length(); i++) {
            char direction = input.charAt(i);
            move(direction, santa, visited);
        }

        return visited.size();

    }

    @Override
    public Integer solvePart2(String input) {
        Vector santa = Vector.of(0, 0);
        Vector roboSanta = Vector.of(0, 0);
        Set<Vector> visited = new HashSet<>();
        visited.add(Vector.of(0, 0));
        for (int i = 0; i < input.length(); i++) {
            char direction = input.charAt(i);
            if (i % 2 == 0) {
                move(direction, santa, visited);
            } else {
                move(direction, roboSanta, visited);
            }
        }

        return visited.size();
    }

    private void move(char direction, Vector object, Set<Vector> visited) {
        switch (direction) {
            case '^':
                object.y++;
                break;
            case 'v':
                object.y--;
                break;
            case '>':
                object.x++;
                break;
            case '<':
                object.x--;
                break;
        }
        visited.add(Vector.of(object));
    }

    public static class Vector {
        int x;
        int y;

        private Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Vector of(int x, int y) {
            return new Vector(x, y);
        }

        public static Vector of(Vector v) {
            return new Vector(v.x, v.y);
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
            Vector other = (Vector) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }
}
