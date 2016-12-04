package _2015.day4;

import javax.xml.bind.DatatypeConverter;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, InterruptedException {
        Iterator<String> input = new Scanner(Problem1.class.getResourceAsStream("day4.txt"));
        String s = input.next();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        for (int i = 0; ; i++) {
            String toHash = s + i;
            messageDigest.update(toHash.getBytes());
            String result = DatatypeConverter.printHexBinary(messageDigest.digest());
            if (result.startsWith("00000")) {
                System.out.println(toHash + " " + result);
                break;
            }
        }
    }
}
