package _2015.day2;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(Problem2.class.getResourceAsStream("day2.txt"));

        final int[] totalRibbon = {0};

        input.forEachRemaining(s -> {
            String[] d = s.split("x");
            int l = Integer.valueOf(d[0]);
            int w = Integer.valueOf(d[1]);
            int h = Integer.valueOf(d[2]);

            int p1 = 2 * l + 2 * w;
            int p2 = 2 * w + 2 * h;
            int p3 = 2 * h + 2 * l;

            int pSmallest = Math.min(p1, Math.min(p2, p3));

            totalRibbon[0] += l * w * h + pSmallest;
        });

        System.out.println(totalRibbon[0]);
    }
}
