package com.github.magneticflux.aoc.year2016.day1;

import org.apache.commons.math3.util.FastMath;

import java.util.Scanner;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day1.txt"));
        String[] instructions = scanner.nextLine().split(", ");

        int xCoordinate = 0;
        int yCoordinate = 0;
        Direction currentDirection = Direction.NORTH;

        for (String instruction : instructions) {
            currentDirection = turn(instruction.charAt(0) == 'L', currentDirection);
            int distance = Integer.parseInt(instruction.substring(1));
            switch (currentDirection) {
                case NORTH:
                    yCoordinate += distance;
                    break;
                case SOUTH:
                    yCoordinate -= distance;
                    break;
                case EAST:
                    xCoordinate += distance;
                    break;
                case WEST:
                    xCoordinate -= distance;
                    break;
            }
        }

        //noinspection SuspiciousNameCombination
        System.out.println("Day 1 Problem 1: " + (FastMath.abs(xCoordinate) + FastMath.abs(yCoordinate)));
    }

    public static Direction turn(boolean isLeft, Direction initial) {
        switch (initial) {
            case NORTH:
                if (isLeft)
                    return Direction.WEST;
                else
                    return Direction.EAST;
            case SOUTH:
                if (isLeft)
                    return Direction.EAST;
                else
                    return Direction.WEST;
            case EAST:
                if (isLeft)
                    return Direction.NORTH;
                else
                    return Direction.SOUTH;
            case WEST:
                if (isLeft)
                    return Direction.SOUTH;
                else
                    return Direction.NORTH;
            default:
                throw new IllegalArgumentException();
        }
    }

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
