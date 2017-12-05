package com.github.magneticflux.aoc.year2016.day5;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by Mitchell Skaggs on 12/5/16.
 */

public class Problem1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(Problem1.class.getResourceAsStream("day5.txt"));
        String doorID = scanner.nextLine();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; ; i++) {
            String toHash = doorID + i;
            messageDigest.update(toHash.getBytes());
            String result = DatatypeConverter.printHexBinary(messageDigest.digest());
            if (result.startsWith("00000")) {
                sb.append(result.charAt(5));
                //System.out.println("Hash: " + result + " Code: " + sb);
            }
            if (sb.length() >= 8)
                break;
        }
        System.out.println("Day 4 Problem 2: " + sb);
    }
}
