package com.company;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander7337 on 4/20/2016.
 */
public class Problem3SoftUniNumerals {

/*    
    String of letters, of which digits must be extracted in order to form a number.
    The number received must be converted from quinary numeral system to decimal numeral system.
*/

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String line = scn.nextLine();

        Pattern pat = Pattern.compile("(aa|aba|bcc|cc|cdc)");
        Matcher mat = pat.matcher(line);

        HashMap<String, Integer> replaceValue = new HashMap<String, Integer>() {{
            put("aa", 0);
            put("aba", 1);
            put("bcc", 2);
            put("cc", 3);
            put("cdc", 4);
        }};

        StringBuilder sb = new StringBuilder();
        while (mat.find()) {
            sb.append(replaceValue.get(mat.group(0)));
        }

        BigInteger newNumber = new BigInteger(sb.toString(), 5);

        System.out.println(newNumber);
    }
}
