/*
 *Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №7
 *
 * The program displays a series of natural numbers separated by a comma,
 * the square is less than a given n. The program runs through the main
 * class with the call parameters.
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Parameters are passed in the order given in the task description.
 */

package com.softserve.edu.task7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class App displays a series of natural numbers separated by a comma,
 * square is less than a given n
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
     * This is the main method which takes the command line arguments.
     * @param args arguments entered from the command line
     * @throws Exception catch exceptions related to invalid user input
     */
    public static void main(final String[] args) throws Exception {
        try {
            System.out.print("Enter n value, positive"
                    + " number greater than 0 \n");
            //user input
            BufferedReader reader;
            reader = new BufferedReader((new InputStreamReader(System.in)));
            Integer n = Integer.parseInt(reader.readLine());
            //check n > 0
            if (n <= 0) {
                System.out.print("Value of n must be greater than 0");
            } else {
                // first natural number
                int i = 1;
                // loop for check the value of n and squares on natural numbers
                while (i * i < n) {
                    if (i == 1) {
                        System.out.print(i);
                        i += 1;
                    } else {
                        System.out.print(", " + i);
                        i += 1;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.print("Enter n value, positive"
                    + " number greater than 0");
        }
    }
}
