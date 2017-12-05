package _2015.day5;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Created by Mitchell on 12/18/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(Problem2.class.getResourceAsStream("day5.txt"));
        List<String> strings = new LinkedList<>();
        input.forEachRemaining(strings::add);

        final String reg1 = ".*(\\w\\w)\\w*\\1.*";
        final String reg2 = ".*(\\w).\\1.*";

        Predicate<String> predicate = s -> {
            boolean hasPair = s.matches(reg1);
            boolean hasTrio = s.matches(reg2);
            return hasPair && hasTrio;
        };
        long num = strings.parallelStream().filter(predicate).count();
        System.out.println(num);
    }
}
