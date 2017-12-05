package com.github.magneticflux.aoc.year2015.day3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Created by Mitchell Skaggs on 12/18/15.
 */
public class Problem2 {
    public static void main(String[] args) {
        Iterator<String> input = new Scanner(Problem2.class.getResourceAsStream("day3.txt"));
        String s = input.next();

        Map<Point, Integer> houses = new HashMap<>();

        BiFunction<Integer, Integer, Integer> bi = (integer, integer2) -> integer + integer2;

        Point currentCoordinatesRealSanta = new Point(0, 0); // x, y
        Point currentCoordinatesRoboSanta = new Point(0, 0);

        houses.merge(currentCoordinatesRealSanta, 1, bi);
        houses.merge(currentCoordinatesRoboSanta, 1, bi);

        for (int i = 0; i < s.length(); i++) {
            char value = s.charAt(i);

            if (i % 2 == 0) { // Real
                switch (value) {
                    case '^':
                        currentCoordinatesRealSanta = new Point(currentCoordinatesRealSanta.x, currentCoordinatesRealSanta.y + 1);
                        houses.merge(currentCoordinatesRealSanta, 1, bi);
                        break;
                    case 'v':
                        currentCoordinatesRealSanta = new Point(currentCoordinatesRealSanta.x, currentCoordinatesRealSanta.y - 1);
                        houses.merge(currentCoordinatesRealSanta, 1, bi);
                        break;
                    case '>':
                        currentCoordinatesRealSanta = new Point(currentCoordinatesRealSanta.x + 1, currentCoordinatesRealSanta.y);
                        houses.merge(currentCoordinatesRealSanta, 1, bi);
                        break;
                    case '<':
                        currentCoordinatesRealSanta = new Point(currentCoordinatesRealSanta.x - 1, currentCoordinatesRealSanta.y);
                        houses.merge(currentCoordinatesRealSanta, 1, bi);
                        break;
                    default:
                        throw new RuntimeException("u wot m8");
                }
            } else { // Robo
                switch (value) {
                    case '^':
                        currentCoordinatesRoboSanta = new Point(currentCoordinatesRoboSanta.x, currentCoordinatesRoboSanta.y + 1);
                        houses.merge(currentCoordinatesRoboSanta, 1, bi);
                        break;
                    case 'v':
                        currentCoordinatesRoboSanta = new Point(currentCoordinatesRoboSanta.x, currentCoordinatesRoboSanta.y - 1);
                        houses.merge(currentCoordinatesRoboSanta, 1, bi);
                        break;
                    case '>':
                        currentCoordinatesRoboSanta = new Point(currentCoordinatesRoboSanta.x + 1, currentCoordinatesRoboSanta.y);
                        houses.merge(currentCoordinatesRoboSanta, 1, bi);
                        break;
                    case '<':
                        currentCoordinatesRoboSanta = new Point(currentCoordinatesRoboSanta.x - 1, currentCoordinatesRoboSanta.y);
                        houses.merge(currentCoordinatesRoboSanta, 1, bi);
                        break;
                    default:
                        throw new RuntimeException("u wot m8");
                }
            }
        }

        System.out.println(houses.size());
    }
}
