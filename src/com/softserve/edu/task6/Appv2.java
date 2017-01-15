/*
 * Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №6
 *
 * Happy tickets.
 * There are 2 ways of counting lucky tickets:
 * 1. Moscow - if the bus ticket is printed six-digit number, and the sum
 * of the first three digits equals the sum of the last three, then the ticket
 * is considered lucky.
 * 2. Leningrad, St. Petersburg, or - if the sum of the even digits of the
 * ticket is equal to the sum of the odd numbers of the ticket, the ticket
 * is considered lucky.
 * A task:
 * Ticket number - a six-digit number. It is necessary to write a console
 * application that can calculate the amount of lucky tickets. To select the
 * calculation algorithm to read a text file. The path to the text file is
 * specified in the console after starting the program. Indicators algorithms:
 * 1 - the word 'Moskow'
 * 2 - the word 'Piter'
 * After setting all the necessary parameters, the program in the console should
 * display the amount of lucky tickets for this method of calculation.
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Prameters are passed in the order given in the task description.
 */
package com.softserve.edu.task6;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * Class App takes from console data directory entry to which is a text file
 * with an indication of type of lucky tickets calculation. Depending on
 * the type of read-out is performed the number of lucky tickets in the
 * calculation of an array of 000000 - 999999.
 * Created by Shvets_A. on 05.01.2017.
 *
 * @author Shvets Aleksandr
 * @version 1.00 05 Jan 2017
 */

final class Appv2 {
    /**
     * Default constructor.
     */
    private Appv2() {
    }

    /**
     * @param args arguments entered from the command line
     * @throws Exception catch FileNotFoundException - incorrect input,
     *                   NullPointerException - incorrect input directory
     */
    public static void main(final String[] args) throws Exception {
        System.out.println("Enter path to the ticket file in file system");
        try {
            BufferedReader cmdReader =
                    new BufferedReader(new InputStreamReader(in));
            String filePath = cmdReader.readLine();

            StringBuilder sb = new StringBuilder();
            BufferedReader in =
                    new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            String ticketType = sb.toString();

            if (ticketType.equals("Moskow") || ticketType.equals("Piter")) {
                System.out.println("Ticket type: " + ticketType + "\n"
                        + "Founded lucky tickets: "
                        + ticketChecker(ticketType));
            } else {
                System.out.println("Incorrect Ticket type: " + ticketType);
            }
        } catch (IOException | NullPointerException e) {
            System.out.print("Please enter correct"
                    + " directory with ticket type file");
        } finally {
            in.close();
        }
    }

    /**
     * method which calculated winning ticket by the type ("Moskow" or "Piter").
     * @param typeTicket type of ticket read from the file
     * @return sum number of lucky tickets in array
     */
    private static int ticketChecker(final String typeTicket) {
        int[] ticketNum = new int[6];
        ticketNum[0] = 0;
        ticketNum[1] = 0;
        ticketNum[2] = 0;
        ticketNum[3] = 0;
        ticketNum[4] = 0;
        ticketNum[5] = 0;
        int happyM = 0;
        int happyP = 0;
        while (true) {
            switch (typeTicket) {
                case "Moskow":
                    if ((ticketNum[0] + ticketNum[1] + ticketNum[2])
                            == (ticketNum[3] + ticketNum[4] + ticketNum[5])) {
                        happyM++;
                    }
                case "Piter":
                    if ((ticketNum[0] + ticketNum[2] + ticketNum[4])
                            == (ticketNum[1] + ticketNum[3] + ticketNum[5])) {
                        happyP++;
                    }
                default:
                    break;
            }
            for (int i = 5; i >= 0; i--) {
                ticketNum[i]++;
                if (ticketNum[i] > 9) {
                    if (i == 0) {
                        if (typeTicket.equals("Piter")) {
                            return happyP;
                        } else {
                            return happyM;
                        }
                    }
                    ticketNum[i] = 0;
                } else {
                    break;
                }
            }
        }
    }
}

