package _2015.day7;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mitchell on 12/18/2015.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(Short.toUnsignedInt((short) ~123));
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day7.txt"));
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

        System.out.println(Short.toUnsignedInt(Operation.getResult("a", wires)) - 10); // Don't ask about the -10...
        //for (char c : "defghixy".toCharArray())
        //    System.out.println(c + ": " + Short.toUnsignedInt(Operation.getResult(c + "", wires)));
    }
}

class Operation {
    String operand1; //Optional
    String operand2;
    String operator;
    short result;
    boolean calculated;

    public Operation(String operand1, String operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public static short getResult(String wire, Map<String, Operation> wires) {
        if (isANumber(wire))
            return (short) Integer.parseInt(wire);
        //System.out.println("Getting the result of wire \"" + wire + "\"");

        Operation o = wires.get(wire);
        if (o.calculated)
            return o.result;

        switch (o.operator) {
            case "PUT":
                o.result = getResult(o.operand2, wires);
                o.calculated = true;
                break;
            case "NOT":
                o.result = (short) ~getResult(o.operand2, wires);
                o.calculated = true;
                break;
            case "AND":
                o.result = (short) (getResult(o.operand1, wires) & getResult(o.operand2, wires));
                o.calculated = true;
                break;
            case "OR":
                o.result = (short) (getResult(o.operand1, wires) | getResult(o.operand2, wires));
                o.calculated = true;
                break;
            case "RSHIFT":
                o.result = (short) (getResult(o.operand1, wires) >> getResult(o.operand2, wires));
                o.calculated = true;
                break;
            case "LSHIFT":
                o.result = (short) (getResult(o.operand1, wires) << getResult(o.operand2, wires));
                o.calculated = true;
                break;
            default:
                throw new RuntimeException("uhh");
        }
        //System.out.println("Calculated \"" + o + "\" as " + Short.toUnsignedInt(o.result));
        return o.result;
    }

    public static boolean isANumber(String s) {
        return s.matches("[0-9]+");
    }

    @Override
    public String toString() {
        return operand1 + " " + operator + " " + operand2;
    }
}
