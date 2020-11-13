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

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor2\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_LIST_FILE = INPUT_PATH + "InputList.txt";
    private static final String OUTPUT_LIST_FILE = OUTPUT_PATH + "OutputList.txt";
    //endregion

    //region Member variables.
    private List<String> mInputLines;
    private List<String> mOutputLines;
    //endregion

    /**
     * Sort.
     */
    public void sort() {
        mInputLines = FileHelper.readAllLines(INPUT_LIST_FILE);
        String[] inputArray = new String[mInputLines.size()];
        inputArray = mInputLines.toArray(inputArray);
        //TODO: Step 1 is to get the sort to work by completing the code for the bubbleSort method.
        bubbleSort(inputArray);
        List<String> sortedLines = Arrays.asList(inputArray);
        // Use this initially to keep duplicates.
        List<String> outputLines = sortedLines;
        //TODO: Step 2 is once the sort is working improve it by removing duplicates from the list.
        //List<String> outputLines = new ArrayList<>();
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
        //TODO: Write code to sort the array using a bubble sort algorithm.
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
