package _2015.day11;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static _2015.day11.Problem1.increment;
import static _2015.day11.Problem1.test;

/**
 * Created by Mitchell on 12/20/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(Problem2.class.getResourceAsStream("day11.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String pass = strings.get(0);

        while (!test(pass))
            pass = increment(pass);
        pass = increment(pass); // Expired password!

        while (!test(pass))
            pass = increment(pass);

        System.out.println(pass);
    }
}
