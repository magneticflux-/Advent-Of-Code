package _2016.day4;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(Problem1.class.getResourceAsStream("day4.txt"));

        List<String> lines = new ArrayList<>();
        while (s.hasNext())
            lines.add(s.next());

        List<String> correctRooms = new ArrayList<>();
        int sum = 0;
        for (String l : lines) {
            Multiset<Character> letters = TreeMultiset.create();
            char[] chars = l.toCharArray();
            for (Character c : chars) {
                if (c != '-') {
                    if (c == '[' || Character.isDigit(c))
                        break;
                    //System.out.println(c);
                    letters.add(c);
                }
            }
            String[] split = l.split("\\[");
            Set<Character> targets = split[1].substring(0, split[1].length() - 1).chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
            Set<Character> mostCommon = letters.entrySet().stream().sorted((o1, o2) -> -Integer.compare(o1.getCount(), o2.getCount())).map(Multiset.Entry::getElement).limit(5).collect(Collectors.toSet());

            //System.out.println(targets);
            //System.out.println(mostCommon);
            if (targets.equals(mostCommon)) {
                String[] split2 = split[0].split("-");
                sum += Integer.valueOf(split2[split2.length - 1]);
                correctRooms.add(l);
            }
        }
        System.out.println("Day 4 Problem 1: " + sum);
    }
}
