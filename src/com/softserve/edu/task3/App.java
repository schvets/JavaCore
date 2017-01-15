/*
 * Copyright (c) Shvets A.
 * Homework Elementary Tasks Java task №2
 *
 * Sort  triangles
 * Develop a console program that performs output triangles in descending
 * order of their area. After addition of each new triangle program asks
 * whether user wants to add one more. If user answers "y" or "yes"
 *(case insensitive), program will ask you to enter data for another
 * triangle, otherwise - outputs result to console.
 * • Calculation area of ​triangle should be based on a formula of Heron.
 * • Each triangle is defined by name and length of its sides.
 * Input format (separator - comma):
 * <Name>, <side length>, <side length>, <side length>
 *  • Application must handle input floating-point numbers.
 *  • Commissioning must be case-insensitive, space, tabam.
 *  Data should be following example:
 *  ============= Triangles list: ===============
 *  1. [Triangle first]: 17.23 cm
 *  2. [Triangle 22]: 13 cm
 *  3. [Triangle 1]: 1.5 cm
 *
 * General requirements
 * 1. When user send incorrect parameters to execute
 * the application does not shut down failure.
 * 2. Run with no parameters displays the instructions for using the program.
 * 3. Prameters are passed in the order given in the task description.
 */

package com.softserve.edu.task3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class App takes as input data on triangle(name and length of three sides).
 * It is possible to add information on several triangles at once, supported
 * work with decimal digits. After user data entry is done the calculation areas
 * of triangles and their output in the order sorted in descending area.
 * Created by Shvets_A. on 09.01.2017.
 *
 * @author Shvets Aleksandr
 * @version 1.00 09 Jan 2017
 */

final class App {
    /**
     * Default constructor.
     */
    private App() {
    }

    /**
     * @param args arguments entered from command line
     * @throws Exception catch IOException
     * | ArrayIndexOutOfBoundsException - incorrect input
     */

    public static void main(final String[] args) throws Exception {
        System.out.println("Please enter triangle data in format: \n"
                + "<Name>, <side length>, <side length>, <side length> \n"
                + "(separator - comma)");

        // structure for storing data entered by  user
        HashMap<String, Float> hashmapTriangle = new HashMap<String, Float>();
        while (true) {
            try {
                // reading user input
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(System.in));
                String triangle = reader.readLine();

                // entered by user data separation
                String[] triangleArray = triangle.split(",");
                float a = Float.parseFloat(triangleArray[1]);
                float b = Float.parseFloat(triangleArray[2]);
                float c = Float.parseFloat(triangleArray[3]);

                // calculation area of a triangle
                float calculatedArea = squareTriangle(a, b, c);

                // verification of the existence of a triangle
                if (a + b < c || a + c < b || b + c < a) {
                    System.out.println("Triangle with sides"
                            + " entered does not exist");
                } else {
                    hashmapTriangle.put(triangleArray[0], calculatedArea);
                }
                System.out.println("If you want to continue,"
                        + " type 'Y', else press 'Enter'");

                // proposal to continue program of work
                String q = reader.readLine();
                if ((q.toLowerCase().equals("yes"))
                        || (q.toLowerCase().equals("y"))) {
                    System.out.println("Continued calculation\n"
                            + "Please enter data of next triangle");
                } else {
                    // output sorted array of user data
                    HashMap sorthashmapTriangle
                            = sortByValues(hashmapTriangle);
                    Set set = sorthashmapTriangle.entrySet();
                    Iterator iterator = set.iterator();
                    int numberofTriangle = 1;
                    System.out.println("============="
                            + " Triangles list: ===============");
                    while (iterator.hasNext()) {
                        Map.Entry output = (Map.Entry) iterator.next();
                        System.out.print(numberofTriangle
                                + ". [Triangle " + output.getKey() + "]: ");
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        System.out.println(formatter.format(output.getValue())
                                + " cm");
                        numberofTriangle++;
                    }
                    break;
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Input data is invalid,"
                        + " please enter data in correct format "
                        + "<Name>, <side length>, <side length>, "
                        + "<side length> \n");
            }
        }
    }

    /**
     * method squareTriangle takes as input data on length of
     * triangle three sides of triangle,and calculates area.
     *  @param a 1 triangle side length
     *  @param b 2 triangle side length
     *  @param c 3 triangle side length
     *  @return method return area of a triangle in float
     */
    private static float squareTriangle(final float a,
                                        final float b,
                                        final float c) {
        float perim = (a + b + c) / 2;
        return (float) Math.sqrt(perim * (perim - a)
                * (perim - b) * (perim - c));
    }

    /**
     * method sortByValues takes takes a unsorted array (key - value), processes
     * it, and returns an array sorted by value.
     *  @param map unsorted array (key - value)
     *  @return method return array sorted by value
     */
    private static HashMap sortByValues(final HashMap map) {
        List list = new LinkedList(map.entrySet());
        // define Comparator
        Collections.sort(list, new Comparator() {
            public int compare(final Object o1, final Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
