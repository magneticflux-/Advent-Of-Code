package _2015.day25;

import java.util.Scanner;

/**
 * Created by Mitchell on 12/5/2016.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day25.txt"));
        input.nextLine();
        String[] in = input.nextLine().split(" ");
        int row = Integer.parseInt(in[0]);
        int column = Integer.parseInt(in[1]);

        System.out.println(getValue(row, column, 0));
    }

    private static long getValue(int row, int column, int depth) {
        if (row == 1 && column == 1)
            return 20151125;
        int newRow = row + 1;
        int newColumn = column - 1;

        if (newColumn < 1) {
            newColumn = row - 1;
            newRow = 1;
        }

        if (depth % 1000000 == 0)
            System.out.println("At r,c " + row + ", " + column + " Depth: " + depth);
        return (getValue(newRow, newColumn, depth + 1) * 252533) % 33554393;
    }
}
