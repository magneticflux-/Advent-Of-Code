package _2015.day2;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(Problem1.class.getResourceAsStream("day2.txt"));

        final int[] totalWrappingPaper = {0};

        input.forEachRemaining(s -> {
            String[] d = s.split("x");
            int l = Integer.valueOf(d[0]);
            int w = Integer.valueOf(d[1]);
            int h = Integer.valueOf(d[2]);

            int s1 = l * w;
            int s2 = w * h;
            int s3 = h * l;

            int sSmallest = Math.min(s1, Math.min(s2, s3));

            totalWrappingPaper[0] += 2 * s1 + 2 * s2 + 2 * s3 + sSmallest;
        });

        System.out.println(totalWrappingPaper[0]);
    }
}
