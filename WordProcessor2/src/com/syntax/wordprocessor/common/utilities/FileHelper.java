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

    public static List<DirectoryEntry> readDirectoryListing(String filePath) {
        List<DirectoryEntry> directoryEntries = new ArrayList<>();
        File root = new File(filePath);
        // Creates an array in which we will store the names of files and directories (folders).
        // The list contains files and folders so we use the generic name "nodes".
        // As we loop through the nodes we can determine if each one if a file or a folder.
        File[] nodes = root.listFiles();
        // For each node in the nodes array.
        for (File node : nodes) {
            DirectoryEntry entry = new DirectoryEntry();
            entry.mName = node.getAbsolutePath();
            if (node.isDirectory()) {
                entry.mType = DirectoryEntry.TypeEnum.DIR;
            } else {
                entry.mType = DirectoryEntry.TypeEnum.FILE;
            }
            entry.mSize = node.length();
            directoryEntries.add(entry);
        }
        return directoryEntries;
    }

    public static List<DirectoryEntry> readFullDirectoryTreeListing(String filePath) {
        List<DirectoryEntry> directoryEntries = new ArrayList<>();
        listDirectory(filePath, directoryEntries);
        return directoryEntries;
    }

    public static void listDirectory(String filePath, List<DirectoryEntry> directoryEntries) {
        File root = new File(filePath);
        File[] entries = root.listFiles();
        if (entries == null) return;
        for (File entry : entries) {
            if (!entry.isDirectory()) {
                directoryEntries.add(new DirectoryEntry(entry.getAbsolutePath(), DirectoryEntry.TypeEnum.FILE, entry.length()));
            }
        }
        for (File entry : entries) {
            if (entry.isDirectory()) {
                directoryEntries.add(new DirectoryEntry(entry.getAbsolutePath(), DirectoryEntry.TypeEnum.DIR, entry.length()));
                listDirectory(entry.getAbsolutePath(), directoryEntries);
            }
        }
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
