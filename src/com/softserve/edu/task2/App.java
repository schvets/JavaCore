/*
 * Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №2
 *
 * There are two envelopes with sides (a, b) and (c, d) to determine
 * whether it is possible to put one envelope to another.
 * The program should process the input floating-point numbers.
 * The program asks the user for the dimensions of the envelope one
 * parameter at a time. After each calculation program asks the user
 * whether he wants to continue. If the user answers "y" or "yes"
 * (without accounting register), first program continues,
 * otherwise - terminates execution.
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Prameters are passed in the order given in the task description.
 */
package com.softserve.edu.task2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 * Class App analyzes size of two envelopes and informs user about
 * possibility of put envelopes in each other
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
     * @param args arguments entered from the command line
     * @throws Exception catch InputMismatchException, incorrect input
     */
    public static void main(final String[] args) throws Exception {
        while (true) {
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                // serial user data input
                System.out.println("Enter 1 envelope length");
                String aString = reader.readLine();
                double a = Double.parseDouble(aString);
                System.out.println("Enter 1 envelope width");
                String bString = reader.readLine();
                double b = Double.parseDouble(bString);
                System.out.println("Enter 2 envelope length");
                String cString = reader.readLine();
                double c = Double.parseDouble(cString);
                System.out.println("Enter 2 envelope width");
                String dString = reader.readLine();
                double d = Double.parseDouble(dString);
                //call checkletter method
                checkletter(a, b, c, d);

                System.out.println("If you want to continue, press 'Y'");
                String q = reader.readLine();

                // proposal to continue program of work
                if ((q.toLowerCase().equals("yes"))
                        || (q.toLowerCase().equals("y"))) {
                    System.out.println("Continued calculation\n");
                } else {
                    System.out.println("The calculation is over\n");
                    break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Enter the correct data, length / width must "
                        + "be a number and should not be less than 0 \n");
            }
        }
    }

    /**
     * method takes the input data of the two envelopes (length and width),
     * checks and gives the answer on the possibility of insert one to another.
     * @param a the length of the envelope 1
     * @param b the width of the envelope 1
     * @param c the length of the envelope 2
     * @param d the width of the envelope 1
     */

    private static void checkletter(final double a, final double b,
                                    final double c, final double d) {
        if (a <= 0 || b <= 0 || c <= 0 || d <= 0) {
            System.out.println("Length / width of the envelope "
                    + "can not be equal to 0 or less, enter the correct data");
        } else if ((a > c && a > d) || (a < c && a < d)) {
            System.out.println("Envelopes can be placed on each other");
        } else {
            System.out.println("Envelopes can not be placed on each other");
        }
    }
}
