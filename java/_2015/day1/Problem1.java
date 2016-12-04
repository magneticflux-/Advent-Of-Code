package _2015.day1;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(Problem1.class.getResourceAsStream("day1.txt"));
        String s = input.next();
        long numOpen = s.chars().filter(character -> character == (int) '(').count();
        long numClosed = s.length() - numOpen;
        System.out.println(numOpen - numClosed);
    }
}
