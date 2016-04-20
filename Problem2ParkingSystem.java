package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander7337 on 4/20/2016.
 */
public class Problem2ParkingSystem {

/*
    The parking lot can be a rectangular matrix where the first column is always free and all other cells are parking spaces.
    Cars can enter from any cell of the first column and then decide where to go.
    If the space is not free, the car drives to the closest free lot on the same row.
    If all cells on this specific row are occupied, the car cannot park and leaves.
    If two free cells are located at the same distance from the initial parking place, the cell which is closer to the entrance is preferred.
*/

    public static int left;
    public static int right;
    public static int index;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String line = scn.nextLine();
        int r = Integer.parseInt(line.split("\\s")[0]);
        int c = Integer.parseInt(line.split("\\s")[1]);
        int travel = 0;

        HashMap<Integer, List<Boolean>> p = new HashMap<>();

        List<Boolean> ls = new ArrayList<>();

        line = scn.nextLine();
        while (!line.equals("stop")) {
            int entrance = Integer.parseInt(line.split("\\s")[0]);
            int key = Integer.parseInt(line.split("\\s")[1]);
            int lot = Integer.parseInt(line.split("\\s")[2]);

            travel = 0;
            left = 0;
            right = 0;

            //My parking lot
            if (!p.containsKey(key)) {
                ls = new ArrayList<Boolean>() {{
                    for (int i = 0; i < c; i++) {
                        add(false);
                    }
                }};
                p.put(key, ls);
            }

            //Check if the place is free
            if (p.get(key).get(lot) == false) {
                p.get(key).set(lot, true);
                travel = 1 + (Math.abs(entrance - key) + lot);
                System.out.println(travel);
            } else {
                for (int i = lot - 1; i >= 1; i--) {
                    if (p.get(key).get(i) == false) {
                        left = lot - i;
                        index = i;
                        break;
                    }
                }

                for (int j = lot + 1; j < p.get(key).size(); j++) {
                    if (p.get(key).get(j) == false) {
                        right = j - lot;
                        break;
                    }
                }

                //Choose the place or leave
                if (right < left && right != 0) {
                    p.get(key).set(right + lot, true);
                    travel = 1 + (Math.abs(entrance - key) + right + lot);
                    System.out.println(travel);
                } else if (left < right && left != 0) {
                    p.get(key).set(index, true);
                    travel = 1 + (Math.abs(entrance - key) + index);
                    System.out.println(travel);
                } else if (left == right && left != 0) {
                    p.get(key).set(index, true);
                    travel = 1 + (Math.abs(entrance - key) + index);
                    System.out.println(travel);
                } else if (right == 0 && left == 0) {
                    System.out.printf("Row %d full%n", key);
                } else if (right > left && left == 0) {
                    p.get(key).set(right + lot, true);
                    travel = 1 + (Math.abs(entrance - key) + right+ lot);
                    System.out.println(travel);
                } else if (left > right && right == 0){
                    p.get(key).set(index, true);
                    travel = 1 + (Math.abs(entrance - key) + index);
                    System.out.println(travel);

                }
            }

            line = scn.nextLine();
        }
    }
}
