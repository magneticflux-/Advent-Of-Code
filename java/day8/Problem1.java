package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mitchell on 12/19/2015.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day8.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());

        int codeSize = strings.parallelStream().mapToInt(String::length).sum();
        int memSize = strings.parallelStream().mapToInt(value -> value.replaceAll("(\\\\\"|\\\\x\\w\\w|\\\\\\\\)", "_").length() - 2).sum();

        System.out.println(codeSize - memSize);
    }
}
