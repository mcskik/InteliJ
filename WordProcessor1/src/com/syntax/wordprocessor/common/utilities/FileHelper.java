package com.syntax.wordprocessor.common.utilities;

import com.syntax.wordprocessor.common.constants.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File input/output helper class.
 * <p>
 * Author: Ken McSkimming
 */
public class FileHelper {

    private static final String NEWLINE = "\r\n";

    /**
     * Read all lines from the specified text file into a list
     * so that the list can be processed by multiple other functions.
     */
    public static List<String> readAllLines(String fileSpec) {
        List<String> lines = new ArrayList<>(); //Create an empty list
        try {
            File file = new File(fileSpec); //creates a new file instance
            FileReader fr = new FileReader(file); //reads the file
            BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line); //adds line to list
            }
            fr.close(); //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Read all amounts from the specified text file into a list
     * so that the list can be processed by other functions.
     */
    public static List<String> readAllAmounts(String fileSpec) {
        List<String> lines = new ArrayList<>(); //Create an empty list
        try {
            File file = new File(fileSpec); //creates a new file instance
            FileReader fr = new FileReader(file); //reads the file
            BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(Constants.COMMA);
                if (values.length > 1) {
                    String amountText = values[1];
                    lines.add(amountText); //adds amount to list
                }
            }
            fr.close(); //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> readDirectoryListing(String filePath) {
        List<String> lines = new ArrayList<>(); //Create an empty list
        // Creates an array in which we will store the names of files and directories
        String[] pathNames;
        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File file = new File(filePath);
        // Populates the array with names of files and directories
        pathNames = file.list();
        // For each pathname in the pathnames array
        for (String pathName : pathNames) {
            lines.add(pathName);
        }
        return lines;
    }

    /**
     * Read all lines from the specified text file into a large string
     * so that it can be processed by multiple other functions.
     */
    public static String readAllLinesIntoString(String fileSpec) {
        StringBuffer sb = new StringBuffer(); //constructs a string buffer with no characters
        try {
            File file = new File(fileSpec); //creates a new file instance
            FileReader fr = new FileReader(file); //reads the file
            BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line); //appends line to string buffer
                sb.append("\n"); //line feed
            }
            fr.close(); //closes the stream and release the resources
            System.out.println("Contents of File: ");
            System.out.println(sb.toString());   //returns a string that textually represents the object
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Write all lines to the specified file.
     */
    public static void writeAllLines(String fileSpec, List<String> lines) {
        try {
            if (lines.size() > 0) {
                FileWriter fw = null;
                try {
                    File file = new File(fileSpec);
                    if (file.exists() && !file.isDirectory()) {
                        if (file.delete()) {
                            System.out.println("File deleted successfully");
                        } else {
                            System.out.println("Failed to delete the file");
                        }
                    }
                    fw = new FileWriter(fileSpec);
                    for (String line : lines) {
                        fw.append(line + NEWLINE);
                    }
                } catch (Exception ex1) {
                    String temp = ex1.getMessage();
                } finally {
                    fw.flush();
                    fw.close();
                }
            }
        } catch (Exception ex2) {
            String text = ex2.getMessage();
        } finally {
        }
    }
}
