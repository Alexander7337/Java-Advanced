package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander7337 on 4/22/2016.
 */
public class Problem1CollectResources {

    //keeps resources
    public static int collectedRes;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        //keeps the biggest number of res
        int max = 0;

        //keeps the valid resources
        List<String> res = new ArrayList<String>(){{
            add("stone");
            add("gold");
            add("wood");
            add("food");
        }};

        String[] line = scn.nextLine().split("\\s");

        int N = Integer.parseInt(scn.nextLine());

        for (int i = 0; i < N; i++) {

            collectedRes = 0;

            //Keeps the step over the array
            int number = 0;
            String commands = scn.nextLine();
            int step = Integer.parseInt(commands.split("\\s")[0]);
            number = Integer.parseInt(commands.split("\\s")[1]);

            //keeps cells from array which are already collected;
            HashSet<Integer> indices = new HashSet<>();

            while (true) {
                if (!indices.contains(step)) {
                    if (line[step].contains("_")) {
                        String resource = line[step].split("_")[0];
                        if (res.contains(resource)) {
                            collectedRes += Integer.parseInt(line[step].split("_")[1]);
                            indices.add(step);
                        }
                    } else if (res.contains(line[step])) {
                        collectedRes += 1;
                        indices.add(step);
                    }
                    step = (step + number) % line.length;
                } else {
                    max = Math.max(max, collectedRes);
                    break;
                }
            }

        }

        System.out.println(max);
    }
}
