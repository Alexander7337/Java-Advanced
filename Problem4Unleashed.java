package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander7337 on 4/23/2016.
 */
public class Problem4Unleashed {

/*
    On each input line you’ll be given data in format: "singer @venue ticketsPrice ticketsCount".
    There will be no redundant whitespaces anywhere in the input.
    Aggregate the data by venue and by singer.
*/

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        LinkedHashMap<String, LinkedHashMap<String, Long>> statistics = new LinkedHashMap<>();

        String line = scn.nextLine();
        Pattern pat = Pattern.compile("^([A-Za-z\\s]+) @([A-Za-z\\s]+) ([0-9]{1,3}) ([0-9]{1,6})$");

        while (!line.equals("End")) {
            Matcher mat = pat.matcher(line);

            while (mat.find()) {
                String name = mat.group(1);
                String venue = mat.group(2);
                int price = Integer.parseInt(mat.group(3));
                int people = Integer.parseInt(mat.group(4));

                if (!statistics.containsKey(venue)) {
                    statistics.put(venue, new LinkedHashMap<>());
                    statistics.get(venue).put(name, 0L);
                } else if (!statistics.get(venue).containsKey(name)) {
                    statistics.get(venue).put(name, 0L);
                }

                statistics.get(venue).put(name, statistics.get(venue).get(name) + (price * people));

            }

            line = scn.nextLine();
        }

        for (Map.Entry<String, LinkedHashMap<String, Long>> entry: statistics.entrySet()) {
            System.out.println(String.format("%s", entry.getKey()));

            LinkedHashMap<String, Long> lhm = new LinkedHashMap<>(entry.getValue());
            SortedSet<Map.Entry<String, Long>> descendingOrder = entriesSortedByValues(lhm);
            for (Map.Entry<String, Long> pair: descendingOrder) {
                System.out.println(String.format("#  %s -> %d", pair.getKey(), pair.getValue()));
            }

        }

    }
    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
