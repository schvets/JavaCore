/*
 * Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №1
 *
 * Display a checkerboard with given dimensions of height and width,
 * according to the principle:
 *  *  *  *  *  *  *
 *    *  *  *  *  *  *
 *  *  *  *  *  *  *
 *    *  *  *  *  *  *
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Parameters are passed in the order given in the task description.
 */
package com.softserve.edu.task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class App displays a chess board of a given size
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
     * This is the main method of class App.
     * @param args arguments entered from the command line
     * @throws Exception catch NumberFormatException, incorrect input
     */
    public static void main(final String[] args) throws Exception {
            System.out.println("Enter the width of the "
                    + "chessboard and press 'Enter' ");
        try {
            // init BufferedReader
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(System.in));

            //user input width of chessboard
            String w = reader.readLine();
            long within = Integer.parseInt(w);

            System.out.println("Enter the height of the chessboard"
                    + " and press 'Enter' ");

            //user input height of chessboard
            String h = reader.readLine();
            long height = Integer.parseInt(h);

            //loop for printing line of chessboard
            for (long i = 1; i <= height; i++) {
                for (long j = 1; j <= within; j++) {
                    if (i % 2 == 0) {
                        System.out.print("  " + "*");
                    } else {
                        System.out.print("*" + "  ");
                    }
                }
                System.out.println();
            }
        } catch (NumberFormatException ex) {
            System.out.print("The input parameters must "
                    + "be positive integer \n");
        }
    }
}
