package com.github.magneticflux.aoc.year2016.day1;

import com.github.magneticflux.aoc.year2016.day1.Problem1.Direction;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.FastMath;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.github.magneticflux.aoc.year2016.day1.Problem1.turn;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem2.class.getResourceAsStream("day1.txt"));
        String[] instructions = scanner.nextLine().split(", ");

        int xCoordinate = 0;
        int yCoordinate = 0;
        Direction currentDirection = Direction.NORTH;
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        main:
        for (String instruction : instructions) {
            currentDirection = turn(instruction.charAt(0) == 'L', currentDirection);
            int distance = Integer.parseInt(instruction.substring(1));
            for (int i = 0; i < distance; i++) {
                switch (currentDirection) {
                    case NORTH:
                        yCoordinate += 1;
                        break;
                    case SOUTH:
                        yCoordinate -= 1;
                        break;
                    case EAST:
                        xCoordinate += 1;
                        break;
                    case WEST:
                        xCoordinate -= 1;
                        break;
                }
                //noinspection SuspiciousNameCombination
                Pair<Integer, Integer> pair = new ImmutablePair<>(xCoordinate, yCoordinate);
                if (visited.contains(pair))
                    break main;
                else
                    visited.add(pair);
                //System.out.println(visited);
            }
        }
        //noinspection SuspiciousNameCombination
        System.out.println("Day 1 Problem 2: " + (FastMath.abs(xCoordinate) + FastMath.abs(yCoordinate)));
    }
}
