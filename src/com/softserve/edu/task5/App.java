/*
 * Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №5
 *
 * The number in the recipe.
 * It is necessary to convert an integer in uppercase version: 12 - twelve.
 * The program runs through the main class with the call parameters.
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Parameters are passed in the order given in the task description.
 */

package com.softserve.edu.task5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class App takes input data from the console, argument long number.
 * Maximum value of the argument - 1e + 12;
 * Minimum value of the argument - -1e + 12;
 * Class converts number entered by user to words.
 * Created by Shvets_A. on 14.01.2017.
 *
 * @version 1.00 14 Jan 2017
 * @author Shvets Aleksandr
 */

final class App {
    /**
     * Default constructor.
     */
    private App() {
    }
    /**
     * This is the main method of class App.
     * @param args arguments entered from the command line
     * @throws Exception catch ArrayIndexOutOfBoundsException - user
     * input errors(number not allowed );
     * NumberFormatException - user input errors(wrong user input)
     */
    public static void main(final String[] args) throws Exception {
        //read user input
        System.out.println("Введите число в диапазоне "
                + "(-1e + 12 ; 1e + 12) и нажмите 'Enter'");
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        String readStr = reader.readLine();
        try {
            long num = Long.parseLong(readStr);
            if (num > 0) {
                System.out.println("Введёно число "
                        + readStr + " : " + numtostr(num));
            } else if (num == 0) {
                System.out.println("Введёно число 0 : ноль");
            } else if (num < 0) {
                num = num * -1;
                System.out.println("Введёно число "
                        + readStr + " : минус " + numtostr(num));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Введено неподдерживаемое"
                    + " значение: " + readStr
                    + " \n Введите число в диапазоне "
                    + "(-1e + 12 ; 1e + 12)");
        }
    }

    /**
     * method takes 1 argument (number in long format,
     * the number adapted to convert into a word).
     * @param num number read from console
     * @return number converted in word (string)
     */
    public static String numtostr(long num) {
        // array for storing literal representation units(1-9)
        String[][] unit = {
                {"", "один", "два", "три", "четыре",
                        "пять", "шесть", "семь",
                        "восемь", "девять"},
                {"", "одна", "две", "три", "четыре",
                        "пять", "шесть", "семь",
                        "восемь", "девять"}};
        // array for storing literal representation hundreds(100-900)
        String[] hundreds = {"", "сто", "двести",
                "триста", "четыреста", "пятьсот",
                "шестьсот", "семьсот", "восемьсот",
                "девятьсот"};
        // array for storing literal representation first dozen(10-19)
        String[] firstDozen = {"", "десять", "одиннадцать",
                "двенадцать", "тринадцать",
                "четырнадцать", "пятнадцать",
                "шестнадцать", "семнадцать",
                "восемнадцать", "девятнадцать",
                "двадцать"};
        // array for storing literal representation dozens(10-90)
        String[] dozens = {"", "десять", "двадцать",
                "тридцать", "сорок", "пятьдесят",
                "шестьдесят", "семьдесят",
                "восемьдесят", "девяносто"};
        // array for storing literal representation
        // otherLevels of number thousand/billion etc.
        String[][] otherLevels = {
                {"", "", "", "1"},
                {"", "", "", "0"},
                {"тысяча", "тысячи", "тысяч", "1"},
                {"миллион", "миллиона",
                        "миллионов", "0"},
                {"миллиард", "миллиарда",
                        "миллиардов", "0"},
                {"триллион", "триллиона",
                        "триллионов", "0"}};
        // array for storing part of long number
        ArrayList partsnumArr = new ArrayList();
        // divide number by 3 characters, add to array
        while (num > 999) {
            long partsnum = num / 1000;
            partsnumArr.add(num - (partsnum * 1000));
            num = partsnum;
        }
        partsnumArr.add(num);
        // flip array
        Collections.reverse(partsnumArr);
        String total = "";

        // size of array
        int scale = partsnumArr.size();
        // loop for convert each part of array to word
        for (int i = 0; i < partsnumArr.size(); i++) {
            // definition kind of int
            int kind;
            kind = (int) Integer.valueOf(otherLevels[scale][3].toString());
            // get part of num from array
            int currentPart;
            currentPart = (int) Integer.valueOf(partsnumArr.get(i).toString());
            // check if current part of number = 0 and array
            // is larger than 2 element, proceed to the next element
            if (currentPart == 0 && scale > 1) {
                scale--;
                continue;
            }
            // convert part of number to string
            String currentPartStr = String.valueOf(currentPart);

            // translate  part of number to format "XXX"
            if (currentPartStr.length() == 1) {
                currentPartStr = "00" + currentPartStr;
            }
            if (currentPartStr.length() == 2) {
                currentPartStr = "0" + currentPartStr;
            }

            // divide number into components, for one digit
            int firstDigit;
            firstDigit = (int) Integer.valueOf(currentPartStr.substring(0, 1));
            int secondDigit;
            secondDigit = (int) Integer.valueOf(currentPartStr.substring(1, 2));
            int thirdDigit;
            thirdDigit = (int) Integer.valueOf(currentPartStr.substring(2, 3));
            int firstsecondDigit;
            firstsecondDigit = (int) Integer.valueOf(
                    currentPartStr.substring(1, 3));

            // analysis of the current part number,
            // adding a text representation of the number
            if (currentPart > 99) {
                total += hundreds[firstDigit] + " ";
            }
            if (firstsecondDigit > 20) {
                total += dozens[secondDigit] + " ";
                total += unit[kind][thirdDigit] + " ";
            } else {
                if (firstsecondDigit > 9) {
                    total += firstDozen[firstsecondDigit - 9] + " ";
                } else {
                    total += unit[kind][thirdDigit] + " ";
                }
            }
            total += declension(currentPart, otherLevels[scale][0],
                    otherLevels[scale][1], otherLevels[scale][2]) + " ";
            scale--;
        }
        return total;
    }

    /**
     * @param n Long processed the number for which the declination check
     * @param type1 String variants inclined words
     *              depending on the number (one object)
     * @param type2 String variants declination words
     *              depending on the number (two objects)
     * @param type3 String declension word options
     *              depending on the number (five projects)
     * @return String correct version of word for specified number of objects
     */
    public static String declension(long n, final String type1,
                                    final String type2, final String type3) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) {
            return type3;
        }
        if (n1 > 1 && n1 < 5) {
            return type2;
        }
        if (n1 == 1) {
            return type1;
        }
        return type3;
    }
}
