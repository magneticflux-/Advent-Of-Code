package _2016.day3;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day3.txt"));
        List<Triple<Integer, Integer, Integer>> specs = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] split1 = scanner.nextLine().trim().split("  *");
            String[] split2 = scanner.nextLine().trim().split("  *");
            String[] split3 = scanner.nextLine().trim().split("  *");
            specs.add(new ImmutableTriple<>(Integer.parseInt(split1[0]), Integer.parseInt(split2[0]), Integer.parseInt(split3[0])));
            specs.add(new ImmutableTriple<>(Integer.parseInt(split1[1]), Integer.parseInt(split2[1]), Integer.parseInt(split3[1])));
            specs.add(new ImmutableTriple<>(Integer.parseInt(split1[2]), Integer.parseInt(split2[2]), Integer.parseInt(split3[2])));
        }

        int count = 0;
        for (Triple<Integer, Integer, Integer> t : specs) {
            if ((t.getLeft() + t.getMiddle() > t.getRight())
                    && (t.getLeft() + t.getRight() > t.getMiddle())
                    && (t.getMiddle() + t.getRight() > t.getLeft())) {
                System.out.println("Possible Triangle: " + t);
                count++;
            } else
                System.out.println("Impossible Triangle: " + t);
        }
        System.out.println("Day 3 Problem 2: " + count);
    }
}
