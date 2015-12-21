package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mitchell on 12/19/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day10.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String currentString = strings.get(0);

        Pattern p = Pattern.compile("(\\d)\\1*");

        for (int i = 0; i < 50; i++) {
            Matcher m = p.matcher(currentString);
            StringBuilder newString = new StringBuilder();
            while (m.find()) {
                String group = m.group();
                newString.append(group.length()).append(group.charAt(0));
            }
            currentString = newString.toString();
            //System.out.println(currentString);
        }
        System.out.println(currentString.length());
    }
}
