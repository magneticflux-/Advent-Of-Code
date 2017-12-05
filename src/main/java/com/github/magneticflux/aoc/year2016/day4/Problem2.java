package com.github.magneticflux.aoc.year2016.day4;

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
public class Problem2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(Problem2.class.getResourceAsStream("day4.txt"));

        List<String> lines = new ArrayList<>();
        while (s.hasNext())
            lines.add(s.next());

        List<String> correctRooms = new ArrayList<>();
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
                correctRooms.add(l);
            }
        }

        for (String r : correctRooms) {
            String[] split = r.split("\\[")[0].split("-");
            int shift = Integer.parseInt(split[split.length - 1]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length - 1; i++) {
                for (char c : split[i].toCharArray()) {
                    sb.append((char) (((c - 97 + shift) % 26) + 97));
                }
                sb.append(' ');
            }
            if (sb.toString().contains("north"))
                System.out.println("Day 4 Problem 2: " + sb + " Shift: " + shift);
        }
    }
}
