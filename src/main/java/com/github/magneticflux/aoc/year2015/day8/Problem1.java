package com.github.magneticflux.aoc.year2015.day8;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mitchell on 12/19/2015.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day8.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());

        int codeSize = strings.parallelStream().mapToInt(String::length).sum();
        int memSize = strings.parallelStream().mapToInt(value -> value.replaceAll("(\\\\\"|\\\\x\\w\\w|\\\\\\\\)", "_").length() - 2).sum();

        System.out.println(codeSize - memSize);
    }
}
