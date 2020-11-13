package com.syntax.wordprocessor.features.statistics;

import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to print a directory listing.
 * <p>
 * Author: Ken McSkimming
 */
public class DirectoryLister {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String DIRECTORY_FILE = OUTPUT_PATH + "Directory.txt";

    // Member variables.
    private List<String> mOutputLines;

    /**
     * Print directory listing.
     */
    public void printDirectoryListing() {
        mOutputLines = new ArrayList<>();
        mOutputLines.add("Directory Listing");
        mOutputLines.add("-------------------------------------");
        List<String> pathNames = FileHelper.readDirectoryListing(INPUT_PATH);
        for (String pathName : pathNames) {
            mOutputLines.add(pathName);
        }
        FileHelper.writeAllLines(DIRECTORY_FILE, mOutputLines);
        view(DIRECTORY_FILE);
    }

    private void view(String fileSpec) {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileSpec);
            pb.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
