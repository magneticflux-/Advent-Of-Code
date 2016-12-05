package _2016.day2;

import java.util.Scanner;

/**
 * Created by Mitchell on 12/4/2016.
 */
public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem2.class.getResourceAsStream("day2.txt"));

        char[][] keypad = new char[][]{
                {' ', ' ', '1', ' ', ' '},
                {' ', '2', '3', '4', ' '},
                {'5', '6', '7', '8', '9'},
                {' ', 'A', 'B', 'C', ' '},
                {' ', ' ', 'D', ' ', ' '},
        };

        int xCoordinate = 0;
        int yCoordinate = 2;

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String instructions = scanner.nextLine();
            for (char c : instructions.toCharArray()) {
                switch (c) {
                    case 'U':
                        yCoordinate++;
                        if (yCoordinate > keypad.length - 1)
                            yCoordinate = keypad.length - 1;
                        if (keypad[keypad.length - 1 - yCoordinate][xCoordinate] == ' ')
                            yCoordinate--;
                        break;
                    case 'D':
                        yCoordinate--;
                        if (yCoordinate < 0)
                            yCoordinate = 0;
                        if (keypad[keypad.length - 1 - yCoordinate][xCoordinate] == ' ')
                            yCoordinate++;
                        break;
                    case 'R':
                        xCoordinate++;
                        if (xCoordinate > keypad[0].length - 1)
                            xCoordinate = keypad[0].length - 1;
                        if (keypad[keypad.length - 1 - yCoordinate][xCoordinate] == ' ')
                            xCoordinate--;
                        break;
                    case 'L':
                        xCoordinate--;
                        if (xCoordinate < 0)
                            xCoordinate = 0;
                        if (keypad[keypad.length - 1 - yCoordinate][xCoordinate] == ' ')
                            xCoordinate++;
                        break;
                }
                System.out.println(xCoordinate + ", " + yCoordinate + " = " + keypad[keypad.length - 1 - yCoordinate][xCoordinate]);
            }
            sb.append(keypad[keypad.length - 1 - yCoordinate][xCoordinate]);
        }
        System.out.println(sb);
    }
}
