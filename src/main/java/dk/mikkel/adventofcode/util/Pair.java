package util;

public class Pair<T, U> {
    public T a;
    public U b;

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
