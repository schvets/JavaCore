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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;


/**
 * Class App takes from console data directory entry to which is a text file
 * with an indication of type of lucky tickets calculation. Depending on
 * the type of read-out is performed the number of lucky tickets in the
 * calculation of an array of 000000 - 999999.
 * Created by Shvets_A. on 05.01.2017.
 *
 * @version 1.00 05 Jan 2017
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
     * @throws Exception catch FileNotFoundException - incorrect input,
     * NullPointerException - incorrect input directory
     */
    public static void main(final String[] args) throws Exception {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // user data input
            System.out.println("Enter directory where ticket "
                    + "type file is located ");
            String dirName = reader.readLine();
            File dir = new File(dirName);
            String[] pathList = dir.list();
            // loop for check all file in path
            for (String aPathList : pathList) {
                File pathfile = new File(dirName + "\\" + aPathList);
                FileInputStream fis = new FileInputStream(pathfile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader inStream = new BufferedReader(isr);
                String str = inStream.readLine();
                // check ticket type
                if (str.equals("Moskow") || str.equals("Piter")) {
                    System.out.println("In directory found ticket "
                            + "type file: " + aPathList);
                    System.out.println("Ticket type: " + str + "\n"
                            + " Founded lucky tickets: " + ticketChecker(str));
                    break;
                }
                inStream.close();
                isr.close();
                fis.close();
            }
        } catch (NullPointerException e) {
            System.out.print("Please enter correct"
                    + " directory with ticket type file");
        } catch (FileNotFoundException e) {
            System.out.print("In select directory did not find"
                    + "any ticket type file");
        }
    }

    /**
     * method which calculated winning ticket by the type ("Moskow" or "Piter").
     * @param typeTicket type of ticket read from the file
     * @return sum number of lucky tickets in array
     */
    public static int ticketChecker(final String typeTicket) {
        int division  = 10;
        int sum = 0;
        int startTic = 000000;
        int endTic = 999999;
        for (int tic = startTic; tic <= endTic; tic++) {
            int number1 = tic / (int) Math.pow(division, 5);
            int number2 = (tic / (int) Math.pow(division, 4)) % division;
            int number3 = (tic / (int) Math.pow(division, 3))
                    % division % division;
            int number4 = (tic / (int) Math.pow(division, 2))
                    % division % division % division;
            int number5 = (tic / division)
                    % division % division % division % division;
            int number6 = tic % division % division % division % division;
            if (typeTicket.equals("Moskow")) {
                if (number1 + number2 + number3
                        == number4 + number5 + number6) {
                    sum++;
                }
            } else if (typeTicket.equals("Piter")) {
                if (number2 + number4 + number6
                        == number1 + number3 + number5) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
