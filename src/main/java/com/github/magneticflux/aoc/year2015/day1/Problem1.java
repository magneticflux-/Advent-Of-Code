package com.github.magneticflux.aoc.year2015.day1;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Mitchell Skaggs on 12/18/15.
 */
public class Problem1 {
    public static void main(String[] args) {
        Iterator<String> input = new Scanner(Problem1.class.getResourceAsStream("day1.txt"));
        String s = input.next();
        long numOpen = s.chars().filter(character -> character == (int) '(').count();
        long numClosed = s.length() - numOpen;
        System.out.println(numOpen - numClosed);
    }
}
