package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(new File("day1.txt"));
        String s = input.next();

        int currentPos = 0;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    currentPos++;
                    break;
                case ')':
                    currentPos--;
                    break;
                default:
                    throw new RuntimeException("U dun goofed");
            }
            if (currentPos == -1) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}
