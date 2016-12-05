package _2015.day6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by Mitchell on 12/18/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(Problem2.class.getResourceAsStream("day6.txt"));
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

        int[][] lights = new int[1000][1000]; // row,col

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
                            lights[row][col] = lights[row][col] + 2;
                            break;
                        case 1:
                            lights[row][col] = lights[row][col] - 1;
                            if (lights[row][col] < 0)
                                lights[row][col] = 0;
                            break;
                        case 2:
                            lights[row][col] = lights[row][col] + 1;
                            break;
                    }
                }
            }
        }
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        long totalBrightness = 0;
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                totalBrightness += lights[row][col];
                int b = 5 * lights[row][col];
                bi.setRGB(col, row, new Color(b, b, b).getRGB());
            }
        }
        System.out.println(totalBrightness);

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
