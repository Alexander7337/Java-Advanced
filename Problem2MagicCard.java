package com.company;

import java.util.Scanner;

/**
 * Created by Alexander7337 on 4/22/2016.
 */
public class Problem2MagicCard {

/*
    The game uses a standard deck of 52 cards.
    The player is given a hand of cards, a string (“odd” or “even”), and a magic card.
    You need to count the sum of all cards at odd or even positions (positions start from 0).
*/


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String hand = scn.nextLine();
        hand = hand.replaceAll("A", "15");
        hand = hand.replaceAll("J", "12");
        hand = hand.replaceAll("Q", "13");
        hand = hand.replaceAll("K", "14");
        String[] splitHand = hand.split("\\s");

        String position = scn.nextLine();

        String magicCard = scn.nextLine();
        if (magicCard.contains("A")) {
            magicCard = magicCard.replace("A", "15");
        } else if (magicCard.contains("J")) {
            magicCard = magicCard.replace("J", "12");
        } else if (magicCard.contains("Q")) {
            magicCard = magicCard.replace("Q", "13");
        } else if (magicCard.contains("K")) {
            magicCard = magicCard.replace("K", "14");
        }
        
        String face = "" + magicCard.split("\\D")[0];
        String suit = "" + magicCard.split("[\\d]+")[1];
        int magicCardFace = Integer.parseInt(face);
        
        int sum = 0;

        for (int i = 0; i < splitHand.length; i++) {
            if (position.equals("even") && i % 2 == 0) {
                String card = splitHand[i];
                String f = "" + card.split("\\D")[0];
                int num = Integer.parseInt(f);
                String s = card.split("[\\d]+")[1] + "";

                num *= 10;

                if ((num/10) == magicCardFace) {
                    num *= 3;
                } else if (s.equals(suit)) {
                    num *= 2;
                }

                sum += num;

            } else if (!position.equals("even") && i % 2 != 0){
                String card = splitHand[i];
                String f = "" + card.split("\\D")[0];
                int num = Integer.parseInt(f);
                String s = card.split("[\\d]+")[1] + "";

                num *= 10;

                if ((num/10) == magicCardFace) {
                    num *= 3;
                } else if (s.equals(suit)) {
                    num *= 2;
                }

                sum += num;
            }
        }

        System.out.println(sum);
    }
}
