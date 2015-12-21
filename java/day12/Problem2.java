package day12;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Mitchell on 12/20/2015.
 */
public class Problem2 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("day12.json"));
        List<String> strings = new LinkedList<>();
        while (input.hasNext())
            strings.add(input.nextLine());
        String doc = strings.stream().collect(Collectors.joining());

        JsonElement o = new JsonParser().parse(doc);

        System.out.println(sumOfNumbers(o));
        // STILL BROKEN WHYYYYYY
    }

    public static int sumOfNumbers(JsonElement object) {
        int sum = 0;
        boolean ignoreRed = object.isJsonArray();
        assert object.isJsonArray() == !object.isJsonObject();

        Iterable<JsonElement> elements = null;
        if (object.isJsonObject())
            elements = ((JsonObject) object).entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
        else if (object.isJsonArray())
            elements = ((JsonArray) object);
        else
            throw new AssertionError("why tho");

        if (!ignoreRed)
            for (JsonElement element : elements) {
                if (element.isJsonPrimitive()) {
                    if (element.getAsString().equals("red")) {
                        System.out.println("Fast failed on " + element + " in " + object);
                        return 0;
                    }
                }
            }

        for (JsonElement element : elements) {
            if (element.isJsonArray()) {
                sum += sumOfNumbers(element);
            } else if (element.isJsonObject()) {
                sum += sumOfNumbers(element);
            } else if (element.isJsonPrimitive()) {
                try {
                    sum += Integer.parseInt(element.getAsString());
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return sum;
    }
}
