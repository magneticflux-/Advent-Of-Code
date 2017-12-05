package _2015.day11;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mitchell on 12/19/2015.
 */
public class Problem1 {
    public static final char afterZ = (char) ('z' + 1);

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day11.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String pass = strings.get(0);

        while (!test(pass))
            pass = increment(pass);

        System.out.println(pass);
    }

    public static boolean test(String s) {
        boolean passed = false;
        for (int i = 0; i < s.length() - 2; i++) {
            String toTest = s.substring(i, i + 3);
            passed = (s.charAt(i) == s.charAt(i + 1) - 1) && (s.charAt(i + 1) == s.charAt(i + 2) - 1);
            if (passed)
                break;
        }
        passed &= !s.matches(".*[iol].*"); // Tests for illegal characters
        passed &= s.matches(".*(\\w)\\1.*(\\w)\\2.*"); // Tests for two full double letters
        return passed;
    }

    public static String increment(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(sb.length() - 1, (char) (sb.charAt(sb.length() - 1) + 1));
        for (int i = sb.length() - 1; i >= 0; i--) { // Right to left
            if (sb.charAt(i) == afterZ) {
                sb.setCharAt(i, 'a');
                if (i != 0)
                    sb.setCharAt(i - 1, (char) (sb.charAt(i - 1) + 1));
                else
                    sb.insert(0, 'a');
            } else
                break;
        }
        return sb.toString();
    }
}
