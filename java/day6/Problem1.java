package day6;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mitchell on 12/18/2015.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day6.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());

        Pattern p = Pattern.compile("(\\d+),(\\d+) through (\\d+),(\\d+)");
        /*
        Matcher m = p.matcher("toggle 461,550 through 564,900");
        boolean found = m.find();
        if (found) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
            System.out.println(m.group(4));
        }
        */

        boolean[][] lights = new boolean[1000][1000]; // row,col

        for (String s : strings) {

            Matcher m = p.matcher(s);
            boolean found = m.find();
            int col1 = Integer.parseInt(m.group(1));
            int row1 = Integer.parseInt(m.group(2));
            int col2 = Integer.parseInt(m.group(3));
            int row2 = Integer.parseInt(m.group(4));

            int mode = -1;
            if (s.startsWith("toggle")) {
                mode = 0;
            } else if (s.startsWith("turn off")) {
                mode = 1;
            } else if (s.startsWith("turn on")) {
                mode = 2;
            }

            for (int row = row1; row <= row2; row++) {
                for (int col = col1; col <= col2; col++) {
                    switch (mode) {
                        case 0:
                            lights[row][col] = !lights[row][col];
                            break;
                        case 1:
                            lights[row][col] = false;
                            break;
                        case 2:
                            lights[row][col] = true;
                            break;
                    }
                }
            }
        }
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        int numOn = 0;
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                if (lights[row][col]) {
                    numOn++;
                    //System.out.print("0");
                    bi.setRGB(col, row, Color.YELLOW.getRGB());
                } else {
                    //System.out.print(" ");
                    bi.setRGB(col, row, Color.BLACK.getRGB());
                }
            }
            //System.out.println();
        }
        System.out.println(numOn);

        JFrame frame = new JFrame("Image");
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.add(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(bi, 0, 0, this);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
