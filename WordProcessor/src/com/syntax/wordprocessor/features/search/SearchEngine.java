package com.syntax.wordprocessor.features.search;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Used to perform searches and global changes.
 * <p>
 * Author: Ken McSkimming
 */
public class SearchEngine {

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String OUTPUT_FILE_1 = OUTPUT_PATH + "OutputText1.txt";
    private static final String OUTPUT_FILE_2 = OUTPUT_PATH + "OutputText2.txt";
    private static final char SPACE = ' ';
    //endregion

    //region Member variables.
    private int mLineCount = 0;
    private int mFoundCount = 0;
    private List<String> mInputLines;
    private List<String> mOutputLines;
    //endregion

    /**
     * Search for all forms of the specified find text.
     */
    public void search(String findText) {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        List<String> findList = new ArrayList<>();
        findList.add(findText.toLowerCase());
        findList.add(properCase(findText));
        findList.add(findText.toUpperCase());
        StringBuilder heading = new StringBuilder("Search for: ");
        String delimiter = Constants.EMPTY_STRING;
        for (String find : findList) {
            heading.append(delimiter).append(find);
            delimiter = Constants.COMMA + Constants.SPACE;
        }
        mOutputLines = new ArrayList<>();
        mOutputLines.add(heading.toString());
        mOutputLines.add("-------------------------------------");
        int lineNumber = 0;
        for (String line : mInputLines) {
            lineNumber++;
            boolean found = false;
            for (String find : findList) {
                if (line.contains(find)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                mFoundCount++;
                mOutputLines.add(String.format("(%s) - %s", lineNumber, line));
            }
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Line  Count                = %s", mLineCount));
        mOutputLines.add(String.format("Found Count                = %s", mFoundCount));
        FileHelper.writeAllLines(OUTPUT_FILE_1, mOutputLines);
        view(OUTPUT_FILE_1);
    }

    /**
     * Search for all forms of the specified find text
     * and replace with the matching form of the replacement text.
     */
    public void searchAndReplace(String findText, String replacementText) {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        TreeMap<String, String> findMap = new TreeMap<>();
        findMap.put(findText.toLowerCase(), replacementText.toLowerCase());
        findMap.put(properCase(findText), properCase(replacementText));
        findMap.put(findText.toUpperCase(), replacementText.toUpperCase());
        StringBuilder headingFind = new        StringBuilder("Search for   : ");
        StringBuilder headingReplacement = new StringBuilder("Replace with : ");
        String delimiter = Constants.EMPTY_STRING;
        for (Map.Entry<String, String> entry : findMap.entrySet()) {
            String find = entry.getKey();
            String replacement = entry.getValue();
            headingFind.append(delimiter).append(find);
            headingReplacement.append(delimiter).append(replacement);
            delimiter = Constants.COMMA + Constants.SPACE;
        }
        mOutputLines = new ArrayList<>();
        mOutputLines.add(headingFind.toString());
        mOutputLines.add(headingReplacement.toString());
        mOutputLines.add("-------------------------------------");
        int lineNumber = 0;
        for (String line : mInputLines) {
            lineNumber++;
            boolean found = false;
            String modifiedLine = line;
            for (Map.Entry<String, String> entry : findMap.entrySet()) {
                String find = entry.getKey();
                String replacement = entry.getValue();
                if (line.contains(find)) {
                    found = true;
                    modifiedLine = modifiedLine.replace(find, replacement);
                }
            }
            if (found) {
                mFoundCount++;
                mOutputLines.add(Constants.EMPTY_STRING);
                mOutputLines.add(String.format("(%s) - %s", lineNumber, line));
                mOutputLines.add(String.format("(%s) - %s", lineNumber, modifiedLine));
            }
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Line  Count                = %s", mLineCount));
        mOutputLines.add(String.format("Found Count                = %s", mFoundCount));
        FileHelper.writeAllLines(OUTPUT_FILE_2, mOutputLines);
        view(OUTPUT_FILE_2);
    }

    /**
     * View the specified file in Notepad.
     */
    private void view(String fileSpec) {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileSpec);
            pb.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Change the specified word to Proper Case.
     */
    public String properCase(String word) {
        String proper = Constants.EMPTY_STRING;
        word = word.trim();
        if (word.length() > 0) {
            proper = word.substring(0, 1).toUpperCase();
            if (word.length() > 1) {
                proper += word.substring(1).toLowerCase();
            }
        }
        return proper;
    }
}
