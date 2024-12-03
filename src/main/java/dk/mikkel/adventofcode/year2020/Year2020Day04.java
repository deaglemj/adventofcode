package dk.mikkel.adventofcode.year2020;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dk.mikkel.adventofcode.util.FileReader;

public class Year2020Day04 {

    private static Logger logger = LogManager.getLogger(Year2020Day04.class);

    public static void main(String[] args) {
        new Year2020Day04().run();
    }

    public void run() {
        validPassports(true);
        validPassports(false);
    }

    private void validPassports(boolean isPuzzleOne) {
        List<String> passports = FileReader.readFileToList("year2020/day_04.input");
        Passport passport = new Passport();
        int count = 0;
        for (String pass : passports) {
            if (pass.equals("")) {
                count = setCount(passport, count, isPuzzleOne);
                passport = new Passport();

                continue;
            }

            String[] properties = pass.split(" ");
            for (String property : properties) {
                passport.setProperty(property);
            }
        }
        count = setCount(passport, count, isPuzzleOne);
        logger.info(count);
    }

    private int setCount(Passport passport, int count, boolean isPuzzleOne) {
        passport.isPuzzleOne = isPuzzleOne;
        boolean valid = passport.isValid();
        if (valid) {
            count++;
        }
        return count;
    }

    class Passport {

        String byr;
        String iyr;
        String eyr;
        String hgt;
        String hcl;
        String ecl;
        String pid;
        String cid;

        boolean isPuzzleOne = true;

        public boolean isValid() {
            if (isPuzzleOne) {
                return isPuzzleOneValid();
            } else {
                return isPuzzleOneValid() && isPuzzleTwoValid();
            }
        }

        public boolean isPuzzleOneValid() {
            return checkProperty(byr) &&
                    checkProperty(iyr) &&
                    checkProperty(eyr) &&
                    checkProperty(hgt) &&
                    checkProperty(hcl) &&
                    checkProperty(ecl) &&
                    checkProperty(pid);
        }

        private boolean checkProperty(String byr) {
            return byr != null && !byr.equals("");
        }

        public boolean isPuzzleTwoValid() {
            return checkByr(byr) &&
                    checkIyr(iyr) &&
                    checkEyr(eyr) &&
                    checkHgt(hgt) &&
                    checkHcl(hcl) &&
                    checkEcl(ecl) &&
                    checkPid(pid);
        }

        private boolean checkPid(String pid) {
            return (pid.length() == 9);
        }

        private boolean checkEcl(String ecl) {
            List<String> strings = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
            return strings.contains(ecl);
        }

        private boolean checkHcl(String hcl) {
            Pattern pattern = Pattern.compile("\\#([a-f0-9]{6})", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(hcl);
            return matcher.find();
        }

        private boolean checkHgt(String hgt) {
            int height;
            String measurement;
            if (hgt.length() == 5) {
                height = Integer.parseInt(hgt.substring(0, 3));
                measurement = hgt.substring(3, 5);
                return "cm".equals(measurement) && height >= 150 && height <= 193;
            } else if (hgt.length() == 4) {
                height = Integer.parseInt(hgt.substring(0, 2));
                measurement = hgt.substring(2, 4);
                return "in".equals(measurement) && height >= 59 && height <= 76;
            }
            return false;
        }

        private boolean checkEyr(String eyr) {
            int i = Integer.parseInt(eyr);
            return i >= 2020 && i <= 2030;
        }

        private boolean checkIyr(String iyr) {
            int i = Integer.parseInt(iyr);
            return i >= 2010 && i <= 2020;
        }

        private boolean checkByr(String byr) {
            int i = Integer.parseInt(byr);
            return i >= 1920 && i <= 2002;
        }

        public void setProperty(String property) {
            String[] split = property.split(":");
            switch (PassportField.valueOf(split[0].toUpperCase())) {
                case BYR -> byr = split[1];
                case IYR -> iyr = split[1];
                case EYR -> eyr = split[1];
                case HGT -> hgt = split[1];
                case HCL -> hcl = split[1];
                case ECL -> ecl = split[1];
                case PID -> pid = split[1];
                case CID -> cid = split[1];
            }
        }

        public enum PassportField {
            BYR, IYR, EYR, HGT, HCL, ECL, PID, CID
        }
    }
}
