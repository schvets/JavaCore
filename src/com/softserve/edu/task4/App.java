
package com.softserve.edu.task4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Class App takes input data from the console, arguments are separated by
 * spaces(path to file, string for counting/ path to file, search string,
 * string to replace), depending on number of arguments entered
 * by user processes.
 *   Mode 1 (2 arguments) - count the number of words in a given file.
 *   Mode 2 (3 argument) - the replacement of all defined words in a
 *   file in accordance with an example.
 * Created by Shvets_A. on 08.01.2017.
 *
 * @version 1.00 08 Jan 2017
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
     * @throws Exception catch IOException, input / output errors
     */
    public static void main(final String[] args) throws Exception {
        System.out.println("Enter data in console, depending on program mode.\n"
                + "1)  <path to file> <string for counting>\n"
                + "2)  <path to file> <search string> <string to replace>\n"
                + "The data should be separated by spaces.");
        // reading user input
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        String argument = reader.readLine();

        // split user input for arguments
        String[] argsArray = argument.split(" ");

        //try to read file from file system
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(new File(argsArray[0])))) {
            String lineText;
            String allText = "";
            //read all line of text file
            while ((lineText = fileReader.readLine()) != null) {
                allText += lineText;
                allText += "\n";
            }

            // check quantity of input arguments
            if (argsArray.length == 2) {
                counter(allText, argsArray[1]);
            } else if (argsArray.length == 3) {
                String tempString = replacer(
                        allText, argsArray[1], argsArray[2]);
                //try to write file
                try (BufferedWriter bw =
                             new BufferedWriter(new FileWriter(argsArray[0]))) {
                    bw.write(tempString);
                    System.out.println("Replacing text in file"
                            + " successfully completed\n");
                } catch (IOException ex) {
                    System.out.println("Failed to write data to a file."
                            + " Check file availability.");
                }
            } else {
                System.out.println("Permission wrong number of arguments."
                        + " Please check the input data, data must be"
                        + " separated by spaces, number 2 or 3 arguments.");
            }
        } catch (IOException ex) {
            System.out.println("Error file reading from the console."
                    + " Please check the input data, data must be"
                    + " separated by spaces, number 2 or 3 arguments.");
        }
    }

    /**
     * method takes 3 arguments (text from a file/search string
     * /string to replace), processing it and return text with replaced word.
     * @param readFile text read from the file
     * @param sample replacement text
     * @param replace text of which is replaced
     * @return  text read from the file with replaced word
     */
    private static String replacer(final String readFile,
                                   final String sample, final String replace) {
        return readFile.replace(sample, replace);
    }

    /**
     * method takes 2 arguments (text from a file/search string),
     * processing it and print number of repetitions of the words.
     * @param readFile text read from the file
     * @param sample search text
     */
    private static void counter(final String readFile, final String sample) {
        String readFilenew = readFile.replaceAll("[,|.|!|&|?|:|;|'|(|)]", "");
        String[] argsArray = readFilenew.split(" ");
        int count = 0;
        for (int i = 0; i < argsArray.length; i++) {
            if (argsArray[i].equals(sample)) {
                count++;
            }
        }
        System.out.println("Number of words '"
                + sample + "' in text - " + count);
    }
}
