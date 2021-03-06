package com.github.magneticflux.aoc.year2015.day12;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Mitchell on 12/20/2015.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day12.json"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String doc = strings.stream().collect(Collectors.joining());

        int sum = 0;
        Matcher m = Pattern.compile("(-?\\d+)").matcher(doc);

        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        System.out.println(sum);
    }
}
