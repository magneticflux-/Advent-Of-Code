package com.github.magneticflux.aoc.year2015.day4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by Mitchell Skaggs on 12/18/15.
 */
public class Problem2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Iterator<String> input = new Scanner(Problem2.class.getResourceAsStream("day4.txt"));
        String s = input.next();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        for (int i = 0; ; i++) {
            String toHash = s + i;
            messageDigest.update(toHash.getBytes());
            String result = DatatypeConverter.printHexBinary(messageDigest.digest());
            if (result.startsWith("000000")) {
                System.out.println(toHash + " " + result);
                break;
            }
        }
    }
}
