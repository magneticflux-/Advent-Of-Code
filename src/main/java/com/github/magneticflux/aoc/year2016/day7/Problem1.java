package _2016.day7;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Mitchell on 12/6/2016.
 */
public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day7.txt"));

        int supported = 0;
        Pattern p = Pattern.compile(".*(.)(?!\\1)(.)\\2\\1.*");
        while (scanner.hasNextLine()) {
            String ip = scanner.nextLine();
            String[] split = ip.split("[\\[\\]]");
            //System.out.println(Arrays.toString(split));
            int numContainsOutside = 0;
            int numContainsInside = 0;
            for (int i = 0; i < split.length; i++) {
                boolean matches = p.matcher(split[i]).matches();
                //System.out.println(split[i]);
                if (matches) {
                    //System.out.println(split[i] + " matches");
                    if (i % 2 == 0) //0,2,4
                    {
                        numContainsOutside++;
                    } else {
                        numContainsInside++;
                    }
                }
                //System.out.println(numContainsOutside + " " + numContainsInside);
            }
            if (numContainsOutside > 0 && numContainsInside == 0)
                supported++;
        }
        System.out.println("Day 7 Problem 1: " + supported);
    }
}
