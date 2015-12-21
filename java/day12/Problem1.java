package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Mitchell on 12/20/2015.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day12.json"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String doc = strings.stream().collect(Collectors.joining());

        int sum = 0;
        Matcher m = Pattern.compile("(-?\\d+)").matcher(doc);

        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        System.out.println(sum);
    }
}
