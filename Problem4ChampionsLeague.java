package com.company;

import java.util.*;

/**
 * Created by Alexander7337 on 4/19/2016.
 */
public class Problem4ChampionsLeague {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        TreeMap<String, Integer> wins = new TreeMap<>();
        HashMap<String, TreeSet<String>> opponents = new HashMap<>();

        String line = scn.nextLine();

        //Keeps records of total goals scored from away and home plays
        int team1Goals = 0;
        int team2Goals = 0;

        //input follows until the "stop" command
        while (!line.equals("stop")) {
            String[] teamsAndScores = line.split(" \\| ");
            String team1 = teamsAndScores[0];
            String team2 = teamsAndScores[1];
            String score1 = teamsAndScores[2];
            String score2 = teamsAndScores[3];

            team1Goals += takeScore(score1);
            team2Goals += takeScore2(score1);
            team2Goals += takeScore(score2);
            team1Goals += takeScore2(score2);

            if (!wins.containsKey(team1) && !wins.containsKey(team2)) {
                wins.put(team1, 0);
                wins.put(team2, 0);
                opponents.put(team1, new TreeSet<>());
                opponents.put(team2, new TreeSet<>());
            } else if (!wins.containsKey(team2)) {
                wins.put(team2, 0);
                opponents.put(team2, new TreeSet<>());
            } else if (!wins.containsKey(team1)) {
                wins.put(team1, 0);
                opponents.put(team1, new TreeSet<>());
            }

            opponents.get(team1).add(team2);
            opponents.get(team2).add(team1);

            if (team1Goals == team2Goals) {
                int away1 = Integer.parseInt(score1.substring(score1.length() - 1, score1.length()));
                int away2 = Integer.parseInt(score2.substring(score2.length() - 1, score2.length()));
                if (away1 > away2) {
                    wins.put(team2, wins.get(team2) + 1);
                } else {
                    wins.put(team1, wins.get(team1) + 1);
                }
            } else if (team1Goals > team2Goals){
                wins.put(team1, wins.get(team1) + 1);
            } else {
                wins.put(team2, wins.get(team2) + 1);
            }

            team1Goals = 0;
            team2Goals = 0;
            line = scn.nextLine();
        }

        SortedSet<Map.Entry<String, Integer>> output = entriesSortedByValues(wins);

        //region Print ranking list by total wins and in alphabetical order
        for (Map.Entry<String, Integer> entry: output) {

            System.out.print(String.format("%s%n- Wins: %d%n", entry.getKey(), entry.getValue()));

            for (Map.Entry<String, TreeSet<String>> pair: opponents.entrySet()) {
                if (pair.getKey().equals(entry.getKey())) {
                    System.out.print("- Opponents: ");

                    TreeSet<String> ts = new TreeSet<>(pair.getValue());
                    int size = ts.size();
                    int count = 1;
                    for (String s: ts) {
                        if (count != size && size != 1) {
                            System.out.print(String.format("%s, ", s));
                            count++;
                        } else if (size == 1){
                            System.out.println(s);
                        } else {
                            System.out.println(s);
                        }
                    }

                }
            }

        }
        //endregion


    }
        //region Methods
    public static int takeScore(String score) {
        int number = 0;
        number = Integer.parseInt(score.substring(0, 1));
        return number;
    }

    public static int takeScore2(String score) {
        int number = 0;
        number = Integer.parseInt(score.substring(score.length() - 1, score.length()));
        return number;
    }

    static <String,Integer extends Comparable<? super Integer>> SortedSet<Map.Entry<String,Integer>> entriesSortedByValues(Map<String,Integer> map) {
        SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(
                new Comparator<Map.Entry<String,Integer>>() {
                    @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
    //endregion
}
