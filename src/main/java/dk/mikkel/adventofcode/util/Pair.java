package dk.mikkel.adventofcode.util;

public class Pair<T, U> {
    public final T a;
    public final U b;

    public Pair(T a, U b) {
        this.a = a;
        this.b = b;
    }

    public T getFirst() {
        return a;
    }

    public U getSecond() {
        return b;
    }

}
