package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem1DragonSharp {

/*
    if (5==5) loop 3 out “gosho”;
    else out "pesho";
    if (5==5) out “gosho”;
    else loop 3 out "pesho";
    If you encounter invalid input, you should print Compile time error @ line {line number} and nothing else.
*/

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = Integer.parseInt(scn.nextLine());

        int row = 0;                                //keeps the row where an error is found
        List<String> list = new ArrayList<>();      //keeps the output in case there is not error
        String num1 = "";                           //keeps first number from statement
        String sign = "";                           //keeps math sign from statement
        String num2 = "";                           //keeps second number from statement
        boolean foundError = false;                 //overall condition for the output
        boolean bool2 = false;                      //condition for keeping the 'else' data

        for (int i = 0; i < n; i++) {

            String line = scn.nextLine();
            Pattern pat = Pattern.compile("^(if|else) (.+?) \"(.+?)\";$");
            Matcher mat = pat.matcher(line);

            if (mat.find()) {

                String condition = mat.group(1);
                String statement = mat.group(2);
                String output = mat.group(3);

                if (condition.equals("if")) {

                    if (statement.contains("loop")) {
                        Pattern pat2 = Pattern.compile("\\((\\d+)([><=]+)(\\d+)\\)");
                        Matcher mat2 = pat2.matcher(statement);

                        while (mat2.find()) {
                            num1 = mat2.group(1);
                            sign = mat2.group(2);
                            num2 = mat2.group(3);
                        }

                        int loops = Integer.parseInt(statement.split("\\s")[2]);

                        if (sign.contains("=")) {
                            if (Integer.parseInt(num1) == Integer.parseInt(num2)) {
                                list.addAll(printValue(loops, output));
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        } else if (sign.equals(">")) {
                            if (Integer.parseInt(num1) > Integer.parseInt(num2)) {
                                list.addAll(printValue(loops, output));
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        } else {
                            if (Integer.parseInt(num1) < Integer.parseInt(num2)) {
                                list.addAll(printValue(loops, output));
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        }

                    } else {
                        Pattern pat2 = Pattern.compile("\\((\\d+)([><=]+)(\\d+)\\)");
                        Matcher mat2 = pat2.matcher(statement);

                        while (mat2.find()) {
                            num1 = mat2.group(1);
                            sign = mat2.group(2);
                            num2 = mat2.group(3);
                        }

                        if (sign.contains("=")) {
                            if (Integer.parseInt(num1) == Integer.parseInt(num2)) {
                                list.add(output);
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        } else if (sign.equals(">")) {
                            if (Integer.parseInt(num1) > Integer.parseInt(num2)) {
                                list.add(output);
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        } else {
                            if (Integer.parseInt(num1) < Integer.parseInt(num2)) {
                                list.add(output);
                                bool2 = true;
                            } else {
                                bool2 = false;
                            }
                        }

                    }

                }

                if (condition.equals("else") && !bool2){

                    if (statement.contains("loop")) {
                        int loops = Integer.parseInt(statement.split("\\s")[1]);
                        list.addAll(printValue(loops, output));
                        bool2 = true;
                    } else {
                        list.add(output);
                    }
                }

            } else {
                row = i + 1;
                foundError = true;
            }
        }

        //region Print output
        if (foundError) {
            System.out.printf("Compile time error @ line %d", row);
        } else {
            for (String s: list) {
                System.out.println(s);
            }
        }
        //endregion
    }
        //region Method
    public static List<String> printValue(int times, String str) {
        List<String> n = new ArrayList<>();
        for (int j = 0; j < times; j++) {
            n.add( str);
        }
        return n;
    }
        //endregion
}
