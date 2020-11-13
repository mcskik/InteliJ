package com.syntax.wordprocessor.features.statistics;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to sort and de-dup lists.
 * <p>
 * Author: Ken McSkimming
 */
public class Sorter {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_LIST_FILE = INPUT_PATH + "InputList.txt";
    private static final String OUTPUT_LIST_FILE = OUTPUT_PATH + "OutputList.txt";

    // Member variables.
    private List<String> mInputLines;
    private List<String> mOutputLines;

    /**
     * Sort.
     */
    public void sort() {
        mInputLines = FileHelper.readAllLines(INPUT_LIST_FILE);
        String[] inputArray = new String[mInputLines.size()];
        inputArray = mInputLines.toArray(inputArray);
        bubbleSort(inputArray);
        List<String> sortedLines = Arrays.asList(inputArray);
        // Use this to keep duplicates.
        //List<String> outputLines = sortedLines;
        // Remove duplicates from the list.
        List<String> outputLines = new ArrayList<>();
        for (String line : sortedLines) {
            if (!outputLines.contains(line)) {
                outputLines.add(line);
            }
        }
        mOutputLines = new ArrayList<>();
        mOutputLines.add("Sorted List");
        mOutputLines.add("-------------------------------------");
        for (String line : outputLines) {
            mOutputLines.add(line);
        }
        FileHelper.writeAllLines(OUTPUT_LIST_FILE, mOutputLines);
        view(OUTPUT_LIST_FILE);
    }

    /**
     * Bubble sort.
     */
    private void bubbleSort(String[] array) {
        int len = array.length;
        String temp = Constants.EMPTY_STRING;
        for (int a = 0; a < len; a++) {
            for (int b = 1; b < (len - a); b++) {
                if (array[b - 1].compareTo(array[b]) > 0) {
                    // Swap elements.
                    temp = array[b - 1];
                    array[b - 1] = array[b];
                    array[b] = temp;
                }
            }
        }
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
