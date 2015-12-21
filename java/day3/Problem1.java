package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by skaggsm on 12/18/15.
 */
public class Problem1 {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<String> input = new Scanner(new File("day3.txt"));
        String s = input.next();

        Map<Point, Integer> houses = new HashMap<>();

        final Point[] currentCoordinates = {new Point(0, 0)}; // x, y

        houses.merge(currentCoordinates[0], 1, (i1, i2) -> i1 + i2);

        s.chars().forEachOrdered(value -> {
            switch (value) {
                case '^':
                    currentCoordinates[0] = new Point(currentCoordinates[0].x, currentCoordinates[0].y + 1);
                    houses.merge(currentCoordinates[0], 1, (i1, i2) -> i1 + i2);
                    break;
                case 'v':
                    currentCoordinates[0] = new Point(currentCoordinates[0].x, currentCoordinates[0].y - 1);
                    houses.merge(currentCoordinates[0], 1, (i1, i2) -> i1 + i2);
                    break;
                case '>':
                    currentCoordinates[0] = new Point(currentCoordinates[0].x + 1, currentCoordinates[0].y);
                    houses.merge(currentCoordinates[0], 1, (i1, i2) -> i1 + i2);
                    break;
                case '<':
                    currentCoordinates[0] = new Point(currentCoordinates[0].x - 1, currentCoordinates[0].y);
                    houses.merge(currentCoordinates[0], 1, (i1, i2) -> i1 + i2);
                    break;
                default:
                    throw new RuntimeException("u wot m8");
            }
        });

        System.out.println(houses.size());
    }
}

class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{x, y});
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && x == ((Point) obj).x && y == ((Point) obj).y;
    }
}
