package day13;

import utils.AdventUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static day13.Problem1.getScore;

/**
 * Created by Mitchell on 12/23/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("day13.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        Pattern p = Pattern.compile("(\\w+) would (lose|gain) (\\d+) happiness units by sitting next to (\\w+)\\.");

        Map<String, Integer> changes = new LinkedHashMap<>();
        Set<String> names = new LinkedHashSet<>();

        for (String s : strings) {
            Matcher m = p.matcher(s);
            if (!m.find())
                throw new RuntimeException("plz");
            String[] parts = new String[]{m.group(1), m.group(2), m.group(3), m.group(4)};
            changes.put(parts[0] + "->" + parts[3], parts[1].equals("gain") ? Integer.parseInt(parts[2]) : -Integer.parseInt(parts[2]));
            names.add(parts[0]);
        }

        for (String name : names) {
            changes.put("Me->" + name, 0);
            changes.put(name + "->Me", 0);
        }
        names.add("Me");

        List<List<String>> permutations = AdventUtils.permute(new ArrayList<>(names));
        List<String> max = permutations.parallelStream().max(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return getScore(o1, changes).compareTo(getScore(o2, changes));
            }
        }).get();
        System.out.println(max + " " + getScore(max, changes));
    }
}
