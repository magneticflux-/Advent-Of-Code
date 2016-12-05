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
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(Problem1.class.getResourceAsStream("day5.txt"));
        List<String> strings = new LinkedList<>();
        input.forEachRemaining(strings::add);

        Predicate<String> predicate = s -> {
            boolean hasDoubleLetter = false;
            int numVowels = 0;

            for (int i = 0; i < s.length() - 1; i++) { // Check forward
                if (!hasDoubleLetter && s.charAt(i) == s.charAt(i + 1))
                    hasDoubleLetter = true;
                if (numVowels < 3 && "aeiou".contains("" + s.charAt(i))) {
                    numVowels++;
                }
                if ((s.charAt(i) == 'a' && s.charAt(i + 1) == 'b') || (s.charAt(i) == 'c' && s.charAt(i + 1) == 'd') || (s.charAt(i) == 'p' && s.charAt(i + 1) == 'q') || (s.charAt(i) == 'x' && s.charAt(i + 1) == 'y'))
                    return false;
            }
            if (numVowels < 3 && "aeiou".contains("" + s.charAt(s.length() - 1))) {
                numVowels++;
            }
            return hasDoubleLetter && numVowels >= 3;
        };
        long num = strings.parallelStream().filter(predicate).count();
        System.out.println(num);
    }
}
