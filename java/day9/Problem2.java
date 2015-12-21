package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static day9.Problem1.permute;

/**
 * Created by Mitchell on 12/19/2015.
 */
public class Problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day9.txt"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());

        Map<Route, Integer> routes = new LinkedHashMap<>(strings.size());
        for (String s : strings) {
            String[] temp = s.split("( to | = )");
            routes.put(new Route(temp[0], temp[1]), Integer.parseInt(temp[2]));
        }
        Set<String> locations = new LinkedHashSet<>();
        for (Route route : routes.keySet()) {
            locations.add(route.from);
            locations.add(route.to);
        }

        //System.out.println(routes);
        //System.out.println(locations);

        List<List<String>> possiblePaths = permute(new ArrayList<>(locations), 0);
        //System.out.println(possiblePaths.size());

        Stream<TotalRoute> totalRoutes = possiblePaths.parallelStream().map(strings1 -> {
            int length = 0;
            for (int i = 0; i < strings1.size() - 1; i++) {
                try {
                    length += routes.get(new Route(strings1.get(i), strings1.get(i + 1)));
                } catch (NullPointerException e) {
                    System.out.println("Illegal path from " + strings1.get(i) + " to " + strings1.get(i + 1));
                    length += 1337 * 42; // Invalid path is heavily penalized
                }
            }
            return new TotalRoute(strings1, length);
        });
        TotalRoute max = totalRoutes.collect(Collectors.maxBy(new Comparator<TotalRoute>() {
            @Override
            public int compare(TotalRoute o1, TotalRoute o2) {
                return Integer.compare(o1.length, o2.length);
            }
        })).get();
        StringBuilder out = new StringBuilder();
        for (String s : max.stops) {
            out.append(s);
            out.append(" -> ");
        }
        out.delete(out.length() - 4, out.length());
        out.append(" takes ").append(max.length).append(" units.");
        System.out.println(out);
    }
}
