/*
 *Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №8
 *
 * The program allows to display all Fibonacci
 * numbers that are in the specified range.
 * The range is defined by two main arguments in the call class.
 * Numbers are displayed separated by commas ascending.
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Parameters are passed in the order given in the task description.
 */

package com.softserve.edu.task8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class App takes as input the data from the console (2 argument data separated
 * by spaces). Arguments are boundaries of a number of Fibonacci. User shows all
 * Fibonacci numbers are in the specified range.
 * Created by Shvets_A. on 02.01.2017.
 *
 * @version 1.00 02 Jan 2017
 * @author Shvets Aleksandr
 */

final class App {
    /**
     * Default constructor.
     */

    private App() {
    }
    /**
     * This is the main method which makes use of fib method.
     * @param args arguments entered from the command line
     * @throws Exception catch exceptions related to invalid user input
     */
    public static void main(final String[] args) throws Exception {
        try {
            System.out.print("Enter 2 numbers separated by a comma,"
                    + "in ascending order. \n");
            //read user input
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(System.in));
            String num = reader.readLine();

            //split user input to array,
            // assign values of borders fibonacci numbers
            String[] strNum = num.split(",");
            int maxValue = Integer.parseInt(strNum[1]);
            int minValue = Integer.parseInt(strNum[0]);

            // loop for printing value of sequence within borders
            for (int i = 1; i <= maxValue; i++) {
                if ((i >= minValue) && (i < maxValue)) {
                    System.out.print(fib(i) + ", ");
                } else if (i == maxValue) {
                        System.out.print(fib(i) + " ");
                } else if (minValue > maxValue) {
                    System.out.print("Incorrect input, "
                            + "first argument is greater than second.");
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.print("Incorrect input, please try "
                    + "enter 2 numbers separated by a comma.");
        }

    }

    /**
     * The method takes as input the value of n (index number),
     * and calculates the value of Fibonacci number for him.
     *
     * @param n serial number of calculated Fibonacci number
     * @return n output value of Fibonacci number
     */

    public static long fib(final int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
