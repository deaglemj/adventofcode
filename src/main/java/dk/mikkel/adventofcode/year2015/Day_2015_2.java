package advent_of_code;

import util.Base;

public class Day_2015_2 extends Base<Integer, Integer> {

    @Override
    public Integer solve_part_1(String input) {
        int totalSquareFeetOfWrappingPaper = 0;
        String[] presents = input.split("\n");
        for (String present : presents) {
            totalSquareFeetOfWrappingPaper += calculateWrappingPaper(present);
        }
        return totalSquareFeetOfWrappingPaper;
    }

    private Integer calculateWrappingPaper(String input) {
        Present present = Present.of(input);
        return present.getArea() + present.getSlack();
    }

    @Override
    public Integer solve_part_2(String input) {
        int totalSquareFeetOfRibbon = 0;
        String[] presents = input.split("\n");
        for (String present : presents) {
            totalSquareFeetOfRibbon += calculateRibbon(present);
        }
        return totalSquareFeetOfRibbon;
    }

    private Integer calculateRibbon(String input) {
        Present present = Present.of(input);
        return present.getVolume() + present.getPerimeter();
    }

    static class Present {
        int l;
        int w;
        int h;

        private Present(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        public static Present of(String dimension) {
            String[] dim = dimension.split("x");
            return new Present(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]), Integer.parseInt(dim[2]));
        }

        public int getArea() {
            return 2 * l * w + 2 * w * h + 2 * h * l;
        }

        public int getSlack() {
            return Math.min(Math.min(l * w, w * h), h * l);
        }

        public int getVolume() {
            return l * w * h;
        }

        public int getPerimeter() {
            return Math.min(Math.min(2 * l + 2 * w, 2 * w + 2 * h), 2 * h + 2 * l);
        }
    }
}
