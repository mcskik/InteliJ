package com.syntax.wordprocessor.features.statistics;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.DirectoryEntry;
import com.syntax.wordprocessor.common.utilities.FileHelper;
import com.syntax.wordprocessor.common.utilities.WordHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to print a directory listing.
 * <p>
 * Author: Ken McSkimming
 */
public class DirectoryLister {

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor2\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String DIRECTORY_FILE = OUTPUT_PATH + "Directory.txt";
    private static final String DIRECTORY_TREE_FILE = OUTPUT_PATH + "DirectoryTree.txt";
    private static final char HYPHEN = '-';
    //endregion

    //region Member variables.
    private List<String> mOutputLines;
    //endregion

    /**
     * Print directory listing.
     */
    public void printDirectoryListing() {
        long totalBytes = 0;
        mOutputLines = new ArrayList<>();
        mOutputLines.add("Directory Listing");
        mOutputLines.add("-----------------");
        List<DirectoryEntry> directoryEntries = FileHelper.readFullDirectoryTreeListing(INPUT_PATH);
        for (DirectoryEntry entry : directoryEntries) {
            mOutputLines.add(String.format("Entry : %s, Bytes : %s", entry.mName, entry.mSize));
            totalBytes += entry.mSize;
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Total Bytes = %s", totalBytes));
        FileHelper.writeAllLines(DIRECTORY_FILE, mOutputLines);
        view(DIRECTORY_FILE);
    }

    /**
     * Print full directory tree listing.
     */
    public void printFullDirectoryTreeListing() {
        long totalBytes = 0;
        mOutputLines = new ArrayList<>();
        String line = "Full Directory Tree Listing";
        String underline = WordHelper.makeString(HYPHEN, line.length());
        mOutputLines.add(line);
        mOutputLines.add(underline);
        List<DirectoryEntry> directoryEntries = FileHelper.readFullDirectoryTreeListing(INPUT_PATH);
        for (DirectoryEntry entry : directoryEntries) {
            switch (entry.mType) {
                case DIR:
                    mOutputLines.add(Constants.EMPTY_STRING);
                    line = String.format("DIR  : %s", entry.mName);
                    mOutputLines.add(line);
                    underline = WordHelper.makeString(HYPHEN, line.length());
                    mOutputLines.add(underline);
                    break;
                case FILE:
                    mOutputLines.add(String.format("FILE : %s, Bytes : %s", entry.mName, entry.mSize));
                    totalBytes += entry.mSize;
                    break;
                default:
                    break;
            }
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Total Bytes = %s", totalBytes));
        FileHelper.writeAllLines(DIRECTORY_TREE_FILE, mOutputLines);
        view(DIRECTORY_TREE_FILE);
    }

    /**
     * View the specified file in notepad.
     */
    private void view(String fileSpec) {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileSpec);
            pb.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
