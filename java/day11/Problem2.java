package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static day11.Problem1.increment;
import static day11.Problem1.test;

/**
 * Created by Mitchell on 12/20/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day11.txt"));
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
