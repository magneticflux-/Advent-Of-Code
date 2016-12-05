package _2015.day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mitchell on 12/23/2015.
 */
public class Problem1 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(Problem1.class.getResourceAsStream("day14.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        final int timeGiven = 2503;
        Pattern p = Pattern.compile("(\\w+) can fly (\\d+) km\\/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\.");
        List<Reindeer> reindeer = new ArrayList<>();
        for (String s : strings) {
            Matcher m = p.matcher(s);
            if (!m.find())
                throw new RuntimeException("is kill");
            reindeer.add(new Reindeer(m.group(1), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4))));
        }
        Reindeer max = reindeer.parallelStream().max((o1, o2) -> o1.getDistance(timeGiven).compareTo(o2.getDistance(timeGiven))).get();
        System.out.println(max + ", " + max.getDistance(timeGiven) + " km");
    }
}

class Reindeer {
    final String name;
    final int speed;
    final int goTime;
    final int restTime;

    public Reindeer(String name, int speed, int goTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.goTime = goTime;
        this.restTime = restTime;
    }

    public Integer getDistance(int time) {
        int totalDistance = 0;
        while (time - (goTime + restTime) > 0) {
            time -= goTime + restTime;
            totalDistance += goTime * speed;
        }
        if (time - goTime >= 0) {
            totalDistance += goTime * speed;
        } else {
            totalDistance += time * speed;
        }
        return totalDistance;
    }

    @Override
    public String toString() {
        return name + ", " + speed + " km/s";
    }
}
