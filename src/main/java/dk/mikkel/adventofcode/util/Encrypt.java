package dk.mikkel.adventofcode.util;

public class Encrypt {

    private Encrypt() {
    }

    public static String stringToMD5Hex(String s) {
        StringBuilder result = new StringBuilder();

        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            byte[] inputBytes = (s).getBytes();
            byte[] hashBytes = digest.digest(inputBytes);
            for (byte b : hashBytes) {
                result.append(String.format("%02x", b));
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
