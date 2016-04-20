package com.company;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by Alexander7337 on 4/19/2016.
 */
public class Problem4DragonArmy {

    public static int dmg = 45;
    public static int hlf = 250;
    public static int arm = 10;

    public static double arg;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        LinkedHashMap<String, LinkedHashMap<String, List<Integer>>> types = new LinkedHashMap<>();
        HashMap<String, List<String>> repeatedDrag = new HashMap<>();
        HashMap<String, TreeMap<String, List<Integer>>> namesByTypes = new HashMap<>();
        List<Integer> currentStats = new ArrayList<>();

        int n = Integer.parseInt(scn.nextLine());
        for (int i = 0; i < n; i++) {
            String line = scn.nextLine();
            String[] dragon = line.split("\\s");
            String type = dragon[0];
            String name = dragon[1];
            String damageStr = dragon[2];
            String healthStr = dragon[3];
            String armorStr = dragon[4];

            int damage = getValue(damageStr, dmg);
            int health = getValue(healthStr, hlf);
            int armor = getValue(armorStr, arm);

            if (!types.containsKey(type)) {
                types.put(type, new LinkedHashMap<>());
                types.get(type).put("damage", new ArrayList<>());
                types.get(type).put("health", new ArrayList<>());
                types.get(type).put("armor", new ArrayList<>());
                repeatedDrag.put(type, new ArrayList<>());

                namesByTypes.put(type, new TreeMap<>());
                namesByTypes.get(type).put(name, new ArrayList<>());
            }


            if (!repeatedDrag.get(type).contains(name)) {
                repeatedDrag.get(type).add(name);
                types.get(type).get("damage").add(damage);
                types.get(type).get("health").add(health);
                types.get(type).get("armor").add(armor);
            } else {
                types.get(type).get("damage").remove(namesByTypes.get(type).get(name).get(0));
                types.get(type).get("damage").add(damage);
                types.get(type).get("health").remove(namesByTypes.get(type).get(name).get(1));
                types.get(type).get("health").add(health);
                types.get(type).get("armor").remove(namesByTypes.get(type).get(name).get(2));
                types.get(type).get("armor").add(armor);

            }


            if (!namesByTypes.get(type).containsKey(name)) {
                namesByTypes.get(type).put(name, new ArrayList<>());
                namesByTypes.get(type).get(name).add(damage);
                namesByTypes.get(type).get(name).add(health);
                namesByTypes.get(type).get(name).add(armor);
            } else if (namesByTypes.get(type).containsKey(name)){
                currentStats.add(damage);
                currentStats.add(health);
                currentStats.add(armor);
                namesByTypes.get(type).put(name, new ArrayList<>());
                namesByTypes.get(type).put(name, currentStats);
                currentStats = new ArrayList<>();
            }

        }

        //region Print
        for (Map.Entry<String, LinkedHashMap<String, List<Integer>>> entry: types.entrySet()) {
            System.out.print(String.format("%s::(", entry.getKey()));
            LinkedHashMap<String, List<Integer>> lhm = new LinkedHashMap<>(entry.getValue());
            int size = lhm.size();
            int counter = 1;
            for (Map.Entry<String, List<Integer>> pair1: lhm.entrySet()) {
                List<Integer> list = new ArrayList<>(pair1.getValue());
                arg = 0d;
                for (int i = 0; i < list.size(); i++) {
                    arg += list.get(i);
                }
                arg /= list.size();
                if (counter != size) {
                    System.out.print(String.format("%.2f/", arg));
                    counter++;
                } else {
                    System.out.println(String.format("%.2f)", arg));
                }

            }

            for (Map.Entry<String, TreeMap<String, List<Integer>>> entry2: namesByTypes.entrySet()) {
                if (entry2.getKey().equals(entry.getKey())) {
                    TreeMap<String, List<Integer>> tm = new TreeMap<>(entry2.getValue());
                    for (Map.Entry<String, List<Integer>> pair2: tm.entrySet()) {
                        System.out.print(String.format("-%s -> damage: ", pair2.getKey()));
                        List<Integer> list2 = new ArrayList<>(pair2.getValue());
                        for (int j = 0; j < list2.size(); j++) {
                            if (j == 0) {
                                System.out.print(String.format("%d, health: ", list2.get(j)));
                            } else if (j != list2.size() - 1) {
                                System.out.print(String.format("%d, armor: ", list2.get(j)));
                            } else {
                                System.out.print(String.format("%d%n", list2.get(j)));
                            }
                        }
                    }
                }
            }

        }
        //endregion
    }

    public static int getValue(String s, int i) {
        int value = 0;
        if (s.equals("null")) {
            value = i;
        } else {
            value = Integer.parseInt(s);
        }
        return value;
    }
}
