package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
        //System.out.println(Short.toUnsignedInt((short) ~123));
        Scanner input = new Scanner(new File("day7.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());

        Pattern p = Pattern.compile("([a-z\\d]+)? ?([A-Z]+) ([a-z\\d]+)");

        HashMap<String, Operation> wires = new HashMap<>();

        for (String s : strings) {
            String[] sides = s.split(" -> ");
            String operation = sides[0];
            String assignment = sides[1];

            Matcher m = p.matcher(operation);
            String operator;
            String operand1 = null;
            String operand2;
            if (m.find()) {
                operand1 = m.group(1);
                operator = m.group(2);
                operand2 = m.group(3);
            } else {
                operator = "PUT";
                operand2 = operation; // 123 -> x becomes PUT 123 -> x, similar to the NOT statement
            }

            wires.put(assignment, new Operation(operand1, operand2, operator));

            //System.out.println("operand1 = " + operand1);
            //System.out.println("operator = " + operator);
            //System.out.println("operand2 = " + operand2);
            //System.out.println("assignment = " + assignment);
            //System.out.println("---------------");
        }

        wires.get("b").operand2 = "3176";

        System.out.println(Short.toUnsignedInt(Operation.getResult("a", wires)));
        //for (char c : "defghixy".toCharArray())
        //    System.out.println(c + ": " + Short.toUnsignedInt(Operation.getResult(c + "", wires)));
    }
}
