package com.github.magneticflux.aoc.year2016.day5;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by Mitchell on 12/5/2016.
 */
public class Problem2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day5.txt"));
        String doorID = scanner.nextLine();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        StringBuilder sb = new StringBuilder(8);
        sb.append("________");
        for (int i = 0; ; i++) {
            String toHash = doorID + i;
            messageDigest.update(toHash.getBytes());
            String result = DatatypeConverter.printHexBinary(messageDigest.digest());
            if (result.startsWith("00000")) {
                if (Character.isDigit(result.charAt(5))) {
                    int location = Integer.parseInt("" + result.charAt(5));
                    if (location < 8 && sb.charAt(location) == '_') {
                        sb.setCharAt(location, result.charAt(6));
                        //System.out.println("I: " + i + " Hash: " + result + " Code: " + sb);
                    }
                }
            }
            if (!sb.toString().contains("_"))
                break;
        }
        System.out.println("Day 4 Problem 2: " + sb);
    }
}
