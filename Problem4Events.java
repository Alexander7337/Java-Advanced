package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Alexander7337 on 4/19/2016.
 */
public class Problem4Events {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = Integer.parseInt(scn.nextLine());

        //Only a valid event can be registered
        String regex = "^#([A-Za-z]+):\\s*@([A-Za-z]+)\\s*(([0-1]?[0-9]|[2][0-3]):([0-5][0-9]))$";
        Pattern pat = Pattern.compile(regex);

        //On the next N lines, you receive information about a single event in the above described format.
        TreeMap<String, TreeMap<String, List<String>>> events = new TreeMap<>();
        String line = "";
        for (int i = 0; i < n; i++) {
            line = scn.nextLine();
            Matcher match = pat.matcher(line);

            while (match.find()) {
                String name = match.group(1);
                String destination = match.group(2);
                String time = match.group(3);

                if (!events.containsKey(destination)) {
                    events.put(destination, new TreeMap<>());
                    events.get(destination).put(name, new ArrayList<>());
                    events.get(destination).get(name).add(time);
                } else if (!events.get(destination).containsKey(name)) {
                    events.get(destination).put(name, new ArrayList<>());
                    events.get(destination).get(name).add(time);
                } else {
                    events.get(destination).get(name).add(time);
                }

            }

        }

        //On the last line, you receive several locations
        line = scn.nextLine();
        List<String> locations = new ArrayList<>(Arrays.stream(line.split(",")).collect(Collectors.toList()));

        //Print events
        //region Print events
        for (Map.Entry<String, TreeMap<String, List<String>>> entry: events.entrySet()) {
            if (locations.contains(entry.getKey())) {
                System.out.println(String.format("%s:", entry.getKey()));
                TreeMap<String, List<String>> attendees = new TreeMap<>(entry.getValue());
                int counter = 0;                                                                        //attendees numbering
                for (Map.Entry<String, List<String>> pair: attendees.entrySet()) {
                    counter++;
                    System.out.print(String.format("%d. %s -> ", counter, pair.getKey()));
                    List<String> requests = new ArrayList<>(pair.getValue());
                    Collections.sort(requests);
                    int size = requests.size();                                                         //time sequence indexer
                    int counter2 = 0;
                    for (String s: requests) {
                        if (counter2 != size - 1 && size != 1) {
                            System.out.print(String.format("%s, ", s));
                            counter2++;
                        } else {
                            System.out.println(s);
                        }
                    }

                }
            }
        }
        //endregion
    }
}
