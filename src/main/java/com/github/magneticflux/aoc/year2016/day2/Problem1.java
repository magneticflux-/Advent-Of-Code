package _2016.day2;

import java.util.Scanner;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day2.txt"));

        char[][] keypad = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        int xCoordinate = 1;
        int yCoordinate = 1;

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String instructions = scanner.nextLine();
            for (char c : instructions.toCharArray()) {
                switch (c) {
                    case 'U':
                        yCoordinate++;
                        if (yCoordinate > 2)
                            yCoordinate = 2;
                        break;
                    case 'D':
                        yCoordinate--;
                        if (yCoordinate < 0)
                            yCoordinate = 0;
                        break;
                    case 'R':
                        xCoordinate++;
                        if (xCoordinate > 2)
                            xCoordinate = 2;
                        break;
                    case 'L':
                        xCoordinate--;
                        if (xCoordinate < 0)
                            xCoordinate = 0;
                        break;
                }
                //System.out.println(xCoordinate + ", " + yCoordinate + " = " + keypad[yCoordinate][xCoordinate]);
            }
            sb.append(keypad[2 - yCoordinate][xCoordinate]);
        }
        System.out.println("Day 1 Problem 1: " + sb);
    }
}
