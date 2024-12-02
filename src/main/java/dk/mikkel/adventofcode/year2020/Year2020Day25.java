package dk.mikkel.adventofcode.year2020;

public class Year2020Day25 {
    public static void main(String[] args) {
        new Year2020Day25().runner();
    }

    private void runner() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        long cardKey = 9093927;
        long doorKey = 11001876;

        long cardLoopSize = 1;
        long tmpKey = 7;
        while (tmpKey != cardKey) {
            tmpKey = (tmpKey * 7) % 20201227;
            cardLoopSize++;
        }

        long encryptionKey = doorKey;
        for (int i = 1; i < cardLoopSize; i++) {
            encryptionKey = (encryptionKey * doorKey) % 20201227;
        }

        System.out.println(encryptionKey);
    }

    private void puzzleTwo() {

    }

}
